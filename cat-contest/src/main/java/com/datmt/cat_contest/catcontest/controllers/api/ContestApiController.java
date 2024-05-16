package com.datmt.cat_contest.catcontest.controllers.api;

import com.datmt.cat_contest.catcontest.models.VoteCounter;
import com.datmt.cat_contest.catcontest.repositories.CatRepository;
import com.datmt.cat_contest.catcontest.repositories.ContestRepository;
import com.datmt.cat_contest.catcontest.repositories.VoteCounterRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/contests")
@RequiredArgsConstructor
@Slf4j
public class ContestApiController {
    private final CatRepository catRepository;
    private final ContestRepository contestRepository;
    private final VoteCounterRepository voteCounterRepository;

    @PutMapping("add-cats/{contestId}")
    public String addCats(@PathVariable Long contestId, @RequestBody List<Long> catIds) {
        var contest = contestRepository.findById(contestId).orElseThrow();

        catRepository.findAllById(catIds).forEach(cat -> {
            if (cat.getContests() == null) {
                cat.setContests(new HashSet<>());
            }

            cat.getContests().add(contest);

            catRepository.save(cat);
        });
        contestRepository.save(contest);

        return "OK";
    }

    @PutMapping("vote/{contestId}/{catId}")
    @Transactional()
    public String vote(@PathVariable Long contestId, @PathVariable Long catId) {
        var voteCounterOptional = voteCounterRepository.findByCatIdAndContestId(catId, contestId);
        VoteCounter vote;
        if (voteCounterOptional.isEmpty()) {
            vote = new VoteCounter();
            vote.setCatId(catId);
            vote.setContestId(contestId);
            vote.setVoteCount(1L);
            vote.setVersion(0L); // Explicitly set if not defaulting in the entity
        } else {
            vote = voteCounterOptional.get();
            vote.setVoteCount(vote.getVoteCount() + 1);
        }
        voteCounterRepository.save(vote);
        return "Processed vote";
    }
}
