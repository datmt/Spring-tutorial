package com.datmt.cat_contest.catcontest.repositories;

import com.datmt.cat_contest.catcontest.models.Cat;
import org.springframework.data.repository.CrudRepository;

public interface CatRepository extends CrudRepository<Cat, Long> {
}
