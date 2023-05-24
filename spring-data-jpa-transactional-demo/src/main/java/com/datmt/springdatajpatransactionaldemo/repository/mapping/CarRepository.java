package com.datmt.springdatajpatransactionaldemo.repository.mapping;

import com.datmt.springdatajpatransactionaldemo.model.car.one_many.Car;

import java.util.Optional;

public interface CarRepository extends org.springframework.data.repository.CrudRepository<Car, Long>{
    Optional<Car> findByName(String name);
}
