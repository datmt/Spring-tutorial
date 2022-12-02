package com.datmt.spring.springloggingelasticsearch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLoggingElasticsearchApplication {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(SpringLoggingElasticsearchApplication.class);
        SpringApplication.run(SpringLoggingElasticsearchApplication.class, args);

        logger.info("Starting application");
    }

}
