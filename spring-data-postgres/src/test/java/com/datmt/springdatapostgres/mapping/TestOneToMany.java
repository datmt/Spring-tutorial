package com.datmt.springdatapostgres.mapping;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.datmt.springdatapostgres.model.o2m.Celeb;
import com.datmt.springdatapostgres.model.o2m.Manager;
import com.datmt.springdatapostgres.repository.CelebRepository;
import com.datmt.springdatapostgres.repository.CommonRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class TestOneToMany {

    @Autowired
    private CommonRepository commonRepository;

    @Autowired
    private CelebRepository celebRepository;

    @Test
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

}
