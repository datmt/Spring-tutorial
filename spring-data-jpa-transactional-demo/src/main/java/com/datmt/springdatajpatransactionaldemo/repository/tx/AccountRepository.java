package com.datmt.springdatajpatransactionaldemo.repository.tx;

import com.datmt.springdatajpatransactionaldemo.model.Account;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AccountRepository extends org.springframework.data.repository.CrudRepository<com.datmt.springdatajpatransactionaldemo.model.Account, Long>{

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Account> findByName(String name);
}
