package com.datmt.cat_contest.catcontest.repositories;

import com.datmt.cat_contest.catcontest.models.VoteCounter;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VoteCounterRepository extends CrudRepository<VoteCounter, Long> {

   Optional<VoteCounter> findByCatIdAndContestId(Long catId, Long contestId);
    @Modifying
    @Query("UPDATE VoteCounter v SET v.voteCount = v.voteCount + 1 WHERE v.catId = :catId AND v.contestId = :contestId")
    int incrementVoteCount(@Param("catId") Long catId, @Param("contestId") Long contestId);

    @Query("SELECT v FROM VoteCounter v WHERE v.catId = :catId AND v.contestId = :contestId")
    VoteCounter findByCatIdAndContestIdLocked(Long catId, Long contestId);
}
