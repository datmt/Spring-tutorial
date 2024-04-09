package com.datmt.springdatapostgres.mapping;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.datmt.springdatapostgres.model.Chapter;
import com.datmt.springdatapostgres.model.Laptop;
import com.datmt.springdatapostgres.model.Novel;
import com.datmt.springdatapostgres.repository.CommonRepository;

@SpringBootTest
public class TestSimpleCollection {

    @Autowired
    private CommonRepository commonRepository;

    @Test
    @DisplayName("test save entity with simple collection")
    public void runTest() {
        var l = new Laptop();
        l.setName("Framework");
        var keys = List.of("a", "b", "c");
        l.setKeys(keys);
        commonRepository.save(l);
    }

    @Test
    @DisplayName("test save entity with embeddable collection")
    public void runTest2() {
        var c1 = new Chapter();
        c1.setPages(100);
        c1.setTitle("Chapter 1");

        var n1 = new Novel();
        n1.setChapters(List.of(c1));
        n1.setTitle("One chapter book");

        commonRepository.save(n1);
    }
}
