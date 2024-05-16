package com.datmt.springdatapostgres.mapping;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.datmt.springdatapostgres.model.o2m.Celeb;
import com.datmt.springdatapostgres.model.o2m.Goat;
import com.datmt.springdatapostgres.model.o2m.Manager;
import com.datmt.springdatapostgres.model.o2m.Owner;
import com.datmt.springdatapostgres.repository.CelebRepository;
import com.datmt.springdatapostgres.repository.CommonRepository;
import com.datmt.springdatapostgres.repository.GoatRepository;
import com.datmt.springdatapostgres.repository.OwnerRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class TestOneToMany {

    @Autowired
    private CommonRepository commonRepository;

    @Autowired
    private CelebRepository celebRepository;

    @Autowired
    private GoatRepository goatRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    @DisplayName("One to many uni directional")
    public void runTest() {
        var manager = new Manager();
        manager.setName("Manager");

        var savedManager = commonRepository.save(manager);

        var celeb1 = new Celeb();
        celeb1.setManager(savedManager);
        celeb1.setName("Jolie");
        var celeb2 = new Celeb();
        celeb2.setManager(savedManager);
        celeb2.setName("Jackies");

        commonRepository.save(celeb1);
        commonRepository.save(celeb2);

        var c1 = celebRepository.findById(1L).orElseThrow();
        log.info("Manager {}", c1.getManager());

    }

    @Test
    @DisplayName("One to many bi directional")
    @Order(1)
    public void runTest2() {
        var owner = new Owner();
        owner.setName("Alibaba");

        var goat1 = new Goat();
        goat1.setGender("Male");

        var goat2 = new Goat();
        goat2.setGender("Female");

        var savedOwner = ownerRepository.save(owner);
        log.info("Saved owner id {}", savedOwner.getId());

        // save goats
        goat1.setOwner(savedOwner);
        goat2.setOwner(savedOwner);

        goatRepository.save(goat1);
        goatRepository.save(goat2);

    }

    @Test
    @DisplayName("Test n+1")
    @Order(2)
    public void printGoats() {
        var onwer1 = ownerRepository.findById(1L).orElseThrow();
        log.info("Owner goats {}", onwer1.getGoats());
    }
}
