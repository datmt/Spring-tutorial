package com.datmt.springdatamongodb.repository;

import com.datmt.springdatamongodb.model.BankAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends MongoRepository<BankAccount, String> {

    Optional<BankAccount> findByName(String name);

    <S extends BankAccount> S save(S entity);
}
