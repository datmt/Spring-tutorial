package com.datmt.springbootrediscache.leaderboard;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/leaderboard")
public class LeaderboardController {

    private final TradingActivityService service;

    public LeaderboardController(TradingActivityService service) {
        this.service = service;
    }

    @PostMapping("/trade")
    public String recordTrade(@RequestParam String userId, @RequestParam double volume) {
        service.recordTrade(userId, volume);
        return "Recorded trade: " + userId + " +" + volume;
    }

    @GetMapping("/top")
    public List<LeaderboardEntry> getTop(@RequestParam(defaultValue = "10") int count) {
        return service.getTopTraders(count);
    }

    @GetMapping("/rank/{userId}")
    public Map<String, Object> getRank(@PathVariable String userId) {
        return Map.of(
                "userId", userId,
                "rank", service.getUserRank(userId) != null ? service.getUserRank(userId) : "not ranked",
                "totalVolume", service.getUserScore(userId) != null ? service.getUserScore(userId) : 0.0
        );
    }
}
