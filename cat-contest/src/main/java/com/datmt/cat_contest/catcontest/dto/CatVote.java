package com.datmt.cat_contest.catcontest.dto;

import com.datmt.cat_contest.catcontest.models.Cat;

public record CatVote(Cat cat, Long voteCount) {
}
