package com.datmt.springdatajpatransactionaldemo.repository.mapping;

import com.datmt.springdatajpatransactionaldemo.model.car.one_many.Maker;

import java.util.Optional;

public interface MakerRepository extends org.springframework.data.repository.CrudRepository<Maker, Long>{
    Optional<Maker> findByName(String name);
}
