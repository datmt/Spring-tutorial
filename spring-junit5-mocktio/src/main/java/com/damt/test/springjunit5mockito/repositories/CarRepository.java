package com.damt.test.springjunit5mockito.repositories;

import com.damt.test.springjunit5mockito.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByMaker(String maker);
}
