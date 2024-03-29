package com.datmt.springdatamongodb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataMongodbApplication implements CommandLineRunner  {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataMongodbApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello World!");
    }
}
