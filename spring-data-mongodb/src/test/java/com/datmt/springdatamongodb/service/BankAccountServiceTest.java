package com.datmt.springdatamongodb.service;

import com.datmt.springdatamongodb.model.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BankAccountServiceTest {

    @Autowired
    BankAccountService bankAccountService;

    @BeforeEach
    void setUp() {
        System.out.println("----->>> setUp");
        bankAccountService.deleteAll();
    }

    //Test deposit concurrently
    @DisplayName("Deposit concurrently")
    @RepeatedTest(100)
    void depositConcurrently() {
        System.out.println("----->>> depositConcurrently");

        //create a new account
        var jane = BankAccount.builder().name("Jane").balance(0).build();
        var janeAccount = bankAccountService.create(jane);
        //Create 2 threads
        Thread t1 = new Thread(() -> {
            bankAccountService.deposit(janeAccount.get_id(), 100);
        });
        Thread t2 = new Thread(() -> {
            bankAccountService.deposit(janeAccount.get_id(), 200);
        });
        Thread t3 = new Thread(() -> {
            bankAccountService.deposit(janeAccount.get_id(), 200);
        });

        //Start 2 threads
        t1.start();
        t2.start();
        t3.start();
        //Wait for 2 threads finish
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Check balance
        assertEquals(500, bankAccountService.findByName("Jane").getBalance());
    }


    @Test
    void testGetAll() {
        assertEquals(0, bankAccountService.getAll().size());
    }
}