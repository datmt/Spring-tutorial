package com.datmt.cat_contest.catcontest.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "vote_counter", uniqueConstraints = @UniqueConstraint(columnNames = {"cat_id", "contest_id"}))
public class VoteCounter {
    @Id
    @GeneratedValue
    private Long id;
    private Long catId;
    private Long contestId;
    private Long voteCount;
    @Version
    private Long version = 0L;
}
