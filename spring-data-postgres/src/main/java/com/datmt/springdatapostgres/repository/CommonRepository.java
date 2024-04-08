package com.datmt.springdatapostgres.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.datmt.springdatapostgres.model.Thing;

@Repository
public interface CommonRepository extends CrudRepository<Thing, Long> {

}
