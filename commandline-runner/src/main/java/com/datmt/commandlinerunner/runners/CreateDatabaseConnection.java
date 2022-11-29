package com.datmt.commandlinerunner.runners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateDatabaseConnection implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(CreateDatabaseConnection.class);

    @Override
    public void run(String... args) throws Exception {
       logger.info("DB connection created");
    }
}
