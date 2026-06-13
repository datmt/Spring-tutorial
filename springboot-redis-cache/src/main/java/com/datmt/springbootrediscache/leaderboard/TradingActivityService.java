package com.datmt.springbootrediscache.leaderboard;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Scenario 3 — Leaderboard / ranking reads with Redis Sorted Sets (ZSET).
 *
 * Key design:
 *   - Key per day: "leaderboard:2026-06-13" — auto-expires, no cleanup job needed
 *   - ZINCRBY for atomic score increment (thread-safe, no read-modify-write)
 *   - ZREVRANGEWITHSCORES O(log N + M) vs SQL ORDER BY O(N log N) on full table
 *   - ZREVRANK O(log N) for user rank vs SQL COUNT(*) WHERE score > user_score
 *
 * Demo flow:
 *   POST /leaderboard/trade?userId=alice&volume=5000   (several times)
 *   GET  /leaderboard/top?count=5
 *   GET  /leaderboard/rank/alice
 */
@Service
public class TradingActivityService {

    private final StringRedisTemplate redisTemplate;

    public TradingActivityService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void recordTrade(String userId, double volume) {
        redisTemplate.opsForZSet().incrementScore(dailyKey(), userId, volume);
    }

    public List<LeaderboardEntry> getTopTraders(int count) {
        Set<ZSetOperations.TypedTuple<String>> top =
                redisTemplate.opsForZSet().reverseRangeWithScores(dailyKey(), 0, count - 1);

        List<LeaderboardEntry> result = new ArrayList<>();
        if (top == null) return result;

        long rank = 1;
        for (ZSetOperations.TypedTuple<String> tuple : top) {
            result.add(new LeaderboardEntry(tuple.getValue(), tuple.getScore(), rank++));
        }
        return result;
    }

    public Long getUserRank(String userId) {
        Long rank = redisTemplate.opsForZSet().reverseRank(dailyKey(), userId);
        return rank != null ? rank + 1 : null; // 1-indexed; null = user not on board
    }

    public Double getUserScore(String userId) {
        return redisTemplate.opsForZSet().score(dailyKey(), userId);
    }

    private String dailyKey() {
        return "leaderboard:" + LocalDate.now();
    }
}
