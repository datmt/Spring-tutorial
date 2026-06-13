package com.datmt.springbootrediscache.transaction;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.Duration;

/**
 * Scenario 4 — Paginated/filtered query result caching.
 *
 * Challenge: combinatorial key space from (accountId × status × page × size).
 * Solution:  MD5 of the serialized filter+page params → compact, deterministic key.
 *
 * Short TTL (30s) chosen deliberately:
 *   - Volatile data (pending txns change frequently)
 *   - Staleness risk acceptable for list views; exact counts need direct DB
 *   - Avoids complex invalidation logic (SCAN+DEL is expensive on large key sets)
 *
 * Demo flow:
 *   GET /transactions/search?accountId=ACC001&status=PENDING&page=0&size=5   -> 600ms
 *   Same request again                                                        -> <5ms
 *   Wait 30s and repeat                                                       -> 600ms (TTL expired)
 */
@Service
public class TransactionQueryService {

    private static final Duration TTL = Duration.ofSeconds(30);
    private static final String KEY_PREFIX = "txn:search:";

    private final RedisTemplate<String, Object> redisTemplate;
    private final TransactionRepository repository;

    public TransactionQueryService(RedisTemplate<String, Object> redisTemplate,
                                   TransactionRepository repository) {
        this.redisTemplate = redisTemplate;
        this.repository = repository;
    }

    public TransactionPage search(TransactionFilter filter, int page, int size) {
        String cacheKey = buildKey(filter, page, size);

        Object cached = redisTemplate.opsForValue().get(cacheKey);
        if (cached instanceof TransactionPage) {
            return (TransactionPage) cached;
        }

        TransactionPage result = repository.search(filter, page, size);
        redisTemplate.opsForValue().set(cacheKey, result, TTL);
        return result;
    }

    private String buildKey(TransactionFilter filter, int page, int size) {
        String raw = filter.toString() + "&page=" + page + "&size=" + size;
        return KEY_PREFIX + DigestUtils.md5DigestAsHex(raw.getBytes());
    }
}
