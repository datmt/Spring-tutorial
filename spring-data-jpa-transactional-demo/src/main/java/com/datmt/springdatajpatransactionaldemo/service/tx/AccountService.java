package com.datmt.springdatajpatransactionaldemo.service.tx;

import com.datmt.springdatajpatransactionaldemo.model.Account;
import com.datmt.springdatajpatransactionaldemo.repository.tx.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void transferMoney(int amount, String fromName, String toName) {
        this.updateAccount(fromName, -amount);
        this.updateAccount(toName, amount);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.NESTED)
    public void updateAccount(String accountName, int amount) {
        var account = this.accountRepository.findByName(accountName).orElseThrow();
        if (account.getBalance() + amount < 0) {
            throw new RuntimeException("Insufficient fund");
        }
        System.out.println(Thread.currentThread().getName() + ": Updating " + accountName + " balance to " + (account.getBalance() + amount));
        account.setBalance(account.getBalance() + amount);
        this.accountRepository.save(account);
    }

    public synchronized void transferMultiple(String fromAccount, List<String> toAccounts, int amount) {
        for (var toAccount : toAccounts) {
            System.out.println(Thread.currentThread().getName() + ": Transfering " + amount + " from " + fromAccount + " to " + toAccount);
            transferMoney(amount, fromAccount, toAccount);
        }
    }

    public Account createAccount(String name, int balance) {
        var account = new Account();
        account.setName(name);
        account.setBalance(balance);
        return this.accountRepository.save(account);
    }

}