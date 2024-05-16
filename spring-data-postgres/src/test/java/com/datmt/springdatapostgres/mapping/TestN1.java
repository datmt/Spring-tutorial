package com.datmt.springdatapostgres.mapping;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.datmt.springdatapostgres.model.o2m.Goat;
import com.datmt.springdatapostgres.model.o2m.Owner;
import com.datmt.springdatapostgres.repository.GoatRepository;
import com.datmt.springdatapostgres.repository.OwnerRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class TestN1 {
    @Autowired
    private GoatRepository goatRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @BeforeEach
    public void init() {
        var owner1 = new Owner();
        owner1.setName("Alibaba");
        var owner2 = new Owner();
        owner2.setName("Simbad");

        var goat1 = new Goat();
        goat1.setGender("Male");

        var goat2 = new Goat();
        goat2.setGender("Female");

        var savedAli = ownerRepository.save(owner1);
        var savedSimbad = ownerRepository.save(owner2);

        // save goats
        goat1.setOwner(savedAli);
        goat2.setOwner(savedSimbad);

        goatRepository.save(goat1);
        goatRepository.save(goat2);

    }

    @Test
    @DisplayName("Test n+1")
    @Transactional
    public void printGoats() {

        var owners = ownerRepository.findAll();

        for (var owner : owners) {
            log.info("Fetching goats");
            log.info("Owner goats {}", owner.getGoats());
        }

    }

    @AfterEach
    public void destroy() {
        goatRepository.deleteAll();
        ownerRepository.deleteAll();
    }
}
