package com.datmt.springdatajpatransactionaldemo;

import com.datmt.springdatajpatransactionaldemo.service.mapping.CarService;
import com.datmt.springdatajpatransactionaldemo.service.mapping.MakerService;
import com.datmt.springdatajpatransactionaldemo.service.tx.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataJpaTransactionalDemoApplication implements CommandLineRunner {

    @Autowired
    private AccountService accountService;

    @Autowired
    MakerService makerService;

    @Autowired
    CarService carService;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaTransactionalDemoApplication.class, args);
    }

    @Override
    //adding this @Transactional annotation fix the lazyfetch exception
    public void run(String... args) throws Exception {
        testAccountService();
    }

    private void testAccountService() {
        var dadAccount = accountService.createAccount("Dad", 1000);
        var sonAccount = accountService.createAccount("Son", 0);
        var daughterAccount = accountService.createAccount("Daughter", 0);

        //spawn 10 threads to transfer money from dad to son and daughter
        for (int i = 0; i < 10; i++) {
            new Thread(() -> accountService.transferMoney(10, "Dad", "Daughter")).start();
        }

    }
}
