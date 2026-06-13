package com.datmt.springbootrediscache.exchangerate;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Scenario 2 — Read-through cache for reference data (FX rates).
 *
 * Pattern: read from Redis, fall back to upstream on miss, refresh on schedule.
 *
 * Demo points:
 *   - Pull-on-miss causes thundering herd on restart → the @Scheduled warm-up prevents it
 *   - Pipeline used in refreshAllRates() to batch 5 SET commands in 1 round trip
 *   - Short TTL (5 min) + scheduled refresh every 60s = rates rarely stale
 */
@Component
public class ExchangeRateCache {

    private static final String KEY_PREFIX = "fx:";
    private static final Duration TTL = Duration.ofMinutes(5);

    private final RedisTemplate<String, Object> redisTemplate;

    public ExchangeRateCache(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Double getRate(String currencyPair) {
        Object cached = redisTemplate.opsForValue().get(KEY_PREFIX + currencyPair);
        if (cached != null) {
            return ((Number) cached).doubleValue();
        }
        Double rate = fetchFromUpstream(currencyPair);
        redisTemplate.opsForValue().set(KEY_PREFIX + currencyPair, rate, TTL);
        return rate;
    }

    // Runs every 60s — warms cache before TTL expires, avoids thundering herd on restart
    @Scheduled(fixedRate = 60_000)
    public void refreshAllRates() {
        Map<String, Double> rates = fetchAllRatesFromUpstream();
        redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            rates.forEach((pair, rate) -> {
                byte[] key = (KEY_PREFIX + pair).getBytes();
                byte[] value = rate.toString().getBytes();
                connection.stringCommands().set(key, value);
            });
            return null;
        });
    }

    // Simulates upstream FX provider call (fixed values + small random drift)
    private Double fetchFromUpstream(String pair) {
        return fetchAllRatesFromUpstream().getOrDefault(pair, 0.0);
    }

    private Map<String, Double> fetchAllRatesFromUpstream() {
        Map<String, Double> rates = new HashMap<>();
        rates.put("USD-EUR", 0.92 + (Math.random() * 0.01));
        rates.put("USD-GBP", 0.79 + (Math.random() * 0.01));
        rates.put("USD-JPY", 157.0 + (Math.random() * 0.5));
        rates.put("EUR-GBP", 0.86 + (Math.random() * 0.01));
        rates.put("USD-SGD", 1.35 + (Math.random() * 0.01));
        return rates;
    }
}
