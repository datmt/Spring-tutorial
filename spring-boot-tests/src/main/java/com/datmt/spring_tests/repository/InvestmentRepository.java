package com.datmt.spring_tests.repository;

import com.datmt.spring_tests.model.Investment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface InvestmentRepository extends MongoRepository<Investment, String> {

    Optional<List<Investment>> findAllByAmountGreaterThan(Float amount);
    Optional<Investment> findByName(String name);

}
