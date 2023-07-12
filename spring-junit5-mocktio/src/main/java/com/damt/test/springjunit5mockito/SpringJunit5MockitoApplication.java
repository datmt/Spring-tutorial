package com.damt.test.springjunit5mockito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringJunit5MockitoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJunit5MockitoApplication.class, args);
	}

}
