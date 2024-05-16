package com.datmt.cat_contest.catcontest.controllers;

import com.datmt.cat_contest.catcontest.dto.CatVote;
import com.datmt.cat_contest.catcontest.repositories.CatRepository;
import com.datmt.cat_contest.catcontest.repositories.ContestRepository;
import com.datmt.cat_contest.catcontest.repositories.VoteCounterRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {
    private final CatRepository catRepository;
    private final ContestRepository contestRepository;
    private final VoteCounterRepository voteCounterRepository;

    @GetMapping("contests")
    public String contests(Model model) {
        model.addAttribute("contests", contestRepository.findAll());
        return "contest";
    }

    @GetMapping("cats")
    public String cats(Model model) {
        model.addAttribute("cats", catRepository.findAll());
        return "cats";
    }

    @GetMapping("contests/{contestId}")
    @Transactional
    public String contestDetails(@PathVariable Long contestId, Model model) {
        var contest = contestRepository.findById(contestId).orElseThrow();
        var voteCatList = new ArrayList<CatVote>();
        var cats = contest.getCats();

        cats.forEach(cat -> {
            var voteCounterOptional = voteCounterRepository.findByCatIdAndContestId(cat.getId(), contestId);
            var catVote = new CatVote(cat, voteCounterOptional.isEmpty() ? 0 : voteCounterOptional.get().getVoteCount());
            voteCatList.add(catVote);
        });
        model.addAttribute("cats", voteCatList);
        model.addAttribute("contest", contest);
        return "contest-details";
    }


}
