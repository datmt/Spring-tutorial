package com.datmt.springdatajpatransactionaldemo.repository.tx;

import com.datmt.springdatajpatransactionaldemo.model.Account;

import java.util.Optional;

public interface AccountRepository extends org.springframework.data.repository.CrudRepository<com.datmt.springdatajpatransactionaldemo.model.Account, Long>{
    Optional<Account> findByName(String name);
}
