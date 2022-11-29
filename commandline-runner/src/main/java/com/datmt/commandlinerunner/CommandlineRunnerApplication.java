package com.datmt.commandlinerunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

@SpringBootApplication
public class CommandlineRunnerApplication {

   Logger logger = LoggerFactory.getLogger(CommandlineRunnerApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(CommandlineRunnerApplication.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            logger.info("Hello, world! From commandline runner as a bean");
        };
    }

    @Bean
    CommandLineRunner runner2() {
        return args -> {
            logger.info("Hello, world! From commandline runner ALSO as a bean");
        };
    }
}
