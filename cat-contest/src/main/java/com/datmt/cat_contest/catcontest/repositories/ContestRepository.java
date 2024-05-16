package com.datmt.cat_contest.catcontest.repositories;

import com.datmt.cat_contest.catcontest.models.Cat;
import com.datmt.cat_contest.catcontest.models.Contest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestRepository extends CrudRepository<Contest, Long> {
}
