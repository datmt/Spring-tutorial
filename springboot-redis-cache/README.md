# Spring Boot Redis Cache — 5 BFSI Scenarios

Production-ready demo covering read-heavy caching patterns in Spring Boot + Redis. All scenarios target financial services (BFSI) workloads: account lookups, FX rates, leaderboards, paginated queries, session state.

## Setup

### Prerequisites

- Java 17+
- Redis 6.0+ running locally on `localhost:6379`
- Redis password set to `hihi` (configured in `application.properties`)

### Start Redis

```bash
redis-server --requirepass hihi
```

### Run the app

```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments=--server.port=8082
```

Runs on `http://localhost:8082`.

---

## Scenario 1: Cache-Aside with `@Cacheable`

**Pattern**: Expensive DB read → cache on first access → serve from Redis on subsequent reads.

**Key Design Points**:

- Per-cache TTL config (accounts: 10 min, demo data: 30 min)
- Three annotation strategies: `@Cacheable` (read-through), `@CacheEvict` (invalidate), `@CachePut` (update inline)
- First hit ~800ms (simulated slow query), cached hits <5ms

**Demo Flow**:

```bash
# First call — DB hit, cache miss
curl -w "\ntime:%{time_total}s\n" http://localhost:8082/accounts/ACC001

# Second call — Redis hit
curl -w "\ntime:%{time_total}s\n" http://localhost:8082/accounts/ACC001

# Update with cache eviction — next read repopulates from DB
curl -X PUT http://localhost:8082/accounts/ACC001/evict \
  -H "Content-Type: application/json" \
  -d '{"ownerName":"Alice Updated","balance":75000,"type":"SAVINGS"}'

# This will be slow again (cache evicted)
curl -w "\ntime:%{time_total}s\n" http://localhost:8082/accounts/ACC001

# Update with CachePut — updates cache inline, no stale window
curl -X PUT http://localhost:8082/accounts/ACC001/put \
  -H "Content-Type: application/json" \
  -d '{"ownerName":"Alice V2","balance":80000,"type":"SAVINGS"}'

# Still fast (cache updated)
curl http://localhost:8082/accounts/ACC001
```

**Files**:

- `account/AccountService.java` — annotation showcase
- `account/AccountController.java` — endpoints
- `account/AccountRepository.java` — simulates slow DB (800ms sleep)

**Discussion Points**:

- When to use `@CacheEvict` vs `@CachePut`? Evict: safe if miss-on-stale is acceptable. Put: lower latency, higher consistency risk.
- TTL tuning: shorter TTL for volatile data (pending transactions) vs longer for reference data (account master).

---

## Scenario 2: Read-Through + Scheduled Warm-Up

**Pattern**: Reference data (FX rates, feature flags) cached with pull-on-miss fallback + periodic bulk refresh.

**Key Design Points**:

- Warm-up job runs every 60s (`@Scheduled(fixedRate=60_000)`)
- Pipeline used for bulk refresh — 5 `SET` commands in 1 round trip
- Avoids "thundering herd" on deploy/restart — cache warm before traffic arrives
- 5-minute TTL gives refresh window of 60s scheduled refresh + 240s buffer

**Demo Flow**:

```bash
# First call — Redis hit (pre-warmed by scheduler)
curl http://localhost:8082/exchange-rates/USD-EUR

# Check multiple pairs
curl http://localhost:8082/exchange-rates/USD-GBP
curl http://localhost:8082/exchange-rates/USD-JPY

# Manual refresh trigger (normally scheduler does this)
curl -X POST http://localhost:8082/exchange-rates/refresh
```

**Files**:

- `exchangerate/ExchangeRateCache.java` — pipeline refresh, scheduled job
- `exchangerate/ExchangeRateController.java` — endpoints

**Discussion Points**:

- Why pipeline? Reduces round trips. 5 SETs → 1 pipeline call vs 5 individual calls.
- Scheduled warm-up prevents cache stampede when single hot key expires and 1000 requests hit DB simultaneously.
- Real-world: integrate with FX provider (Bloomberg, ECB API) instead of `fetchAllRatesFromUpstream()`.

---

## Scenario 3: Leaderboard with Sorted Sets

**Pattern**: Top-N rankings, user rank lookups. O(log N) reads vs O(N log N) SQL `ORDER BY` on full table.

**Key Design Points**:

- Redis ZSET (`ZINCRBY`, `ZREVRANGE`, `ZREVRANK`) for atomic, sorted score operations
- Daily key rotation (`leaderboard:2026-06-13`) — auto-expires, no cleanup job
- Can compose weekly/monthly rollups via `ZUNIONSTORE` (union of daily ZSETs)

**Demo Flow**:

```bash
# Record trades (increments cumulative volume per user)
curl -X POST "http://localhost:8082/leaderboard/trade?userId=alice&volume=5000"
curl -X POST "http://localhost:8082/leaderboard/trade?userId=bob&volume=8200"
curl -X POST "http://localhost:8082/leaderboard/trade?userId=alice&volume=3100"
curl -X POST "http://localhost:8082/leaderboard/trade?userId=charlie&volume=12000"

# Get top 3 traders
curl http://localhost:8082/leaderboard/top?count=3

# Check alice's rank
curl http://localhost:8082/leaderboard/rank/alice

# Record more
curl -X POST "http://localhost:8082/leaderboard/trade?userId=alice&volume=6500"
curl http://localhost:8082/leaderboard/rank/alice  # Rank updated instantly
```

**Files**:

- `leaderboard/TradingActivityService.java` — ZSET operations
- `leaderboard/LeaderboardController.java` — endpoints

**Discussion Points**:

- Why ZSET not HASH? HASH is unordered; ZSET gives rank for free via `ZREVRANK`.
- Daily rotation: `leaderboard:2026-06-14` created tomorrow automatically, yesterday's expires via TTL.
- Extension: `ZUNIONSTORE` to merge daily leaderboards into weekly/monthly without DB scan.

---

## Scenario 4: Paginated Query Result Caching

**Pattern**: Filter + pagination caching for volatile data (e.g., pending transactions). Cache keys are deterministic MD5(filter + page params).

**Key Design Points**:

- Combinatorial key space (accountId × status × page × size) solved via MD5 hash → compact, deterministic keys
- Short TTL (30s) avoids expensive cache invalidation (SCAN+DEL is O(N))
- Staleness acceptable for list views (exact counts should query DB directly)
- No per-item invalidation — entire page cache clears after TTL

**Demo Flow**:

```bash
# Filter by account + status, page 0
curl -w "\ntime:%{time_total}s (first, DB)\n" \
  "http://localhost:8082/transactions/search?accountId=ACC001&status=PENDING&page=0&size=3"

# Same query — cached, <30ms
curl -w "\ntime:%{time_total}s (cached)\n" \
  "http://localhost:8082/transactions/search?accountId=ACC001&status=PENDING&page=0&size=3"

# Different page — different cache key, hits DB
curl "http://localhost:8082/transactions/search?accountId=ACC001&status=PENDING&page=1&size=3"

# Different account — different key, hits DB
curl "http://localhost:8082/transactions/search?accountId=ACC002&status=COMPLETED&page=0&size=5"

# Wait 30s and repeat first query — cache expired, hits DB again
```

**Files**:

- `transaction/TransactionQueryService.java` — MD5 cache key builder, short TTL strategy
- `transaction/TransactionRepository.java` — simulates slow paginated query (600ms)
- `transaction/TransactionController.java` — endpoints

**Discussion Points**:

- MD5 ensures queries with identical params get same cache key (order-independent).
- TTL = 30s is aggressive but acceptable for "pending transactions" — accurate counts via direct query if needed.
- Alternative: cache invalidation on write (monitor `UPDATE transaction ...` events), but adds complexity.
- Real-world: expose cache stats (hits, misses, TTL) via Micrometer for observability.

---

## Scenario 5: Session / User Profile Caching

**Pattern**: Cross-instance user context caching. Resolves "who is this user + entitlements" from IAM once, then serves from Redis for 15 min.

**Key Design Points**:

- All app instances share same Redis → session data survives restarts and horizontal scale-out
- TTL = 15 min (matches typical Keycloak access token lifetime)
- Explicit logout evicts cache (`DELETE /session/{token}`)
- Real-world: replace IAM mock with actual Keycloak token introspection

**Demo Flow**:

```bash
# First call — Keycloak hit (~400ms latency simulated)
curl -w "\ntime:%{time_total}s (first, IAM)\n" http://localhost:8082/session/token-alice

# Second call — Redis hit, <10ms
curl -w "\ntime:%{time_total}s (cached)\n" http://localhost:8082/session/token-alice

# Check bob's context
curl http://localhost:8082/session/token-bob

# Logout — evicts cache
curl -X DELETE http://localhost:8082/session/token-alice

# Next call — IAM hit again (cache evicted)
curl -w "\ntime:%{time_total}s (after logout, IAM)\n" http://localhost:8082/session/token-alice

# Invalid token
curl http://localhost:8082/session/invalid-token  # 404

# Valid tokens: token-alice, token-bob, token-admin
```

**Files**:

- `session/UserContextCache.java` — token → UserContext lookup, explicit eviction
- `session/SessionController.java` — endpoints
- `session/UserContext.java` — roles + entitlements model

**Discussion Points**:

- Why not just validate JWT locally? Because entitlements (what a user can do) are mutable and live in IAM. Caching prevents needing to call IAM on every request.
- Horizontal scale: if app instance A caches user context in local memory, instance B doesn't see it. Redis solves this.
- Spring Session integration: `spring-session-data-redis` makes `HttpSession` Redis-backed transparently — no manual template calls needed.

---

## Testing with Redis CLI

Inspect cache keys in Redis directly:

```bash
redis-cli -a hihi

# List all keys
KEYS "*"

# Check account cache
GET "com.datmt.springbootrediscache.accounts:ACC001"

# Check FX rate
GET "fx:USD-EUR"

# Check leaderboard ZSET
ZRANGE "leaderboard:2026-06-13" 0 -1 WITHSCORES

# Check transaction search MD5 key
KEYS "txn:search:*"

# Check session
GET "session:token-alice"
```

---

## Configuration

**Redis Connection** (`application.properties`):

```properties
spring.redis.host=localhost
spring.redis.port=6379
spring.redis.password=hihi
```

**Cache Configuration** (`SpringbootRedisCacheApplication.java`):

- Default TTL: 1 hour
- Per-cache overrides: accounts (10 min), secretData (30 min)
- Serialization: Jackson with `JavaTimeModule` (handles `LocalDateTime`, etc.)
- Default typing: enables polymorphic deserialization for complex objects

---

## Extension Ideas

1. **Cache Stampede Protection**: Add local Caffeine near-cache (Scenario 1) to absorb thundering herd when Redis key expires.
2. **Distributed Locking**: Use Redis `SET nx ex` for pessimistic lock on cache refresh (Scenario 2).
3. **Cache Warming**: Batch import data on startup (Scenario 2).
4. **Metrics**: Add Micrometer `@Timed` to compare cached vs uncached latencies.
5. **Compression**: For large pages (Scenario 4), compress value before caching.
6. **Async Refresh**: Use `CompletableFuture` to refresh expired cache in background while serving stale data (Scenario 2, 5).

---

## References

- Spring Cache Abstraction: https://docs.spring.io/spring-framework/reference/integration/cache.html
- Spring Data Redis: https://docs.spring.io/spring-data/redis/reference/
- Redis Data Structures: https://redis.io/docs/data-types/
- BFSI Caching Best Practices: Consider regulatory requirements for staleness (PCI-DSS, SOX).
