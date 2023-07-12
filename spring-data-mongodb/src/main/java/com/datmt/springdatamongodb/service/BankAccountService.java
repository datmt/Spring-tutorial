package com.datmt.springdatamongodb.service;

import com.datmt.springdatamongodb.model.BankAccount;
import com.datmt.springdatamongodb.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    public void deleteAll() {
        bankAccountRepository.deleteAll();
    }

    public List<BankAccount> getAll() {
        return bankAccountRepository.findAll();
    }

    public BankAccount create(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    public BankAccount findByName(String name) {
        return bankAccountRepository.findByName(name).orElse(null);
    }

    public BankAccount update(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    public void delete(String id) {
        bankAccountRepository.deleteById(id);
    }

    public BankAccount deposit(String id, long amount) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        return bankAccountRepository.save(bankAccount);
    }

}
