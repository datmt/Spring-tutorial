package com.datmt.springdatapostgres.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.datmt.springdatapostgres.model.o2m.Owner;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {

    @Query("select o from Owner o left join fetch o.goats")
    Iterable<Owner> findAll();
}
