package com.datmt.springdatajpatransactionaldemo.service.tx;

import com.datmt.springdatajpatransactionaldemo.model.Account;
import com.datmt.springdatajpatransactionaldemo.repository.tx.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void transferMoney(int amount, String fromName, String toName) {
        System.out.println("Transfering money...");
        var fromAccount = this.accountRepository.findByName(fromName).orElseThrow();
        var toAccount = this.accountRepository.findByName(toName).orElseThrow();

        this.updateAccount(fromAccount, -amount);
        this.updateAccount(toAccount, amount);

        System.out.println("Transfering money done!");
    }

    public void updateAccount(Account account, int amount) {
        account.setBalance(account.getBalance() + amount);

        if (amount == 100)
            throw new RuntimeException("Oops!");
        this.accountRepository.save(account);
    }

    public Account createAccount(String name, int balance) {
        var account = new Account();
        account.setName(name);
        account.setBalance(balance);
        return this.accountRepository.save(account);
    }

}