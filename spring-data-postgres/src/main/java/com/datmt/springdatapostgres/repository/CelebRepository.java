package com.datmt.springdatapostgres.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.datmt.springdatapostgres.model.o2m.Celeb;

@Repository
public interface CelebRepository extends CrudRepository<Celeb, Long> {

}
