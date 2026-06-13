package com.datmt.springbootrediscache.leaderboard;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LeaderboardEntry {
    private String userId;
    private Double totalVolume;
    private Long rank; // 1-indexed
}
