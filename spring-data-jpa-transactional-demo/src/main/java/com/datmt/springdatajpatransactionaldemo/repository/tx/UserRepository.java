package com.datmt.springdatajpatransactionaldemo.repository.tx;

import com.datmt.springdatajpatransactionaldemo.model.Customer;

import java.util.Optional;

public interface UserRepository extends org.springframework.data.repository.CrudRepository<Customer, Long> {
    Optional<Customer> findByName(String name);
}
