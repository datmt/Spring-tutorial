package com.datmt.springdatapostgres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.datmt.springdatapostgres.repository")
public class SpringDataPostgresApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataPostgresApplication.class, args);
    }
}
