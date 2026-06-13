package com.datmt.springbootrediscache.session;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Map;

/**
 * Scenario 5 — Session / user-profile caching across horizontal instances.
 *
 * Problem: reading "who is this user + what are their entitlements" from
 * Keycloak/IAM on every request is expensive and couples us tightly to IAM
 * availability.
 *
 * Solution: cache the resolved UserContext in Redis keyed by session token.
 * All app instances share the same Redis, so the cache works across restarts
 * and scale-out — unlike an in-memory local cache.
 *
 * TTL = 15 min, same as Keycloak access token lifetime. On logout, evict explicitly.
 *
 * Production note: for Spring Session integration, add spring-session-data-redis
 * and @EnableRedisHttpSession — HttpSession reads/writes become Redis-backed
 * transparently, no manual template calls needed.
 */
@Component
public class UserContextCache {

    private static final Duration SESSION_TTL = Duration.ofMinutes(15);
    private static final String KEY_PREFIX = "session:";

    // Simulated user store (replace with Keycloak token introspection)
    private static final Map<String, UserContext> KEYCLOAK_STORE = Map.of(
            "token-alice", new UserContext("U001", "alice", "alice@bank.com",
                    List.of("ROLE_TRADER"), List.of("READ_PORTFOLIO", "EXECUTE_TRADE")),
            "token-bob", new UserContext("U002", "bob", "bob@bank.com",
                    List.of("ROLE_VIEWER"), List.of("READ_PORTFOLIO")),
            "token-admin", new UserContext("U003", "admin", "admin@bank.com",
                    List.of("ROLE_ADMIN"), List.of("READ_PORTFOLIO", "EXECUTE_TRADE", "MANAGE_USERS"))
    );

    private final RedisTemplate<String, Object> redisTemplate;

    public UserContextCache(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public UserContext getUserContext(String token) {
        String key = KEY_PREFIX + token;

        Object cached = redisTemplate.opsForValue().get(key);
        if (cached instanceof UserContext) {
            return (UserContext) cached;
        }

        UserContext ctx = loadFromKeycloak(token);
        if (ctx != null) {
            redisTemplate.opsForValue().set(key, ctx, SESSION_TTL);
        }
        return ctx;
    }

    public void evict(String token) {
        redisTemplate.delete(KEY_PREFIX + token);
    }

    private UserContext loadFromKeycloak(String token) {
        simulateIamLatency();
        return KEYCLOAK_STORE.get(token);
    }

    private void simulateIamLatency() {
        try { Thread.sleep(400); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
