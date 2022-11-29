package com.datmt.commandlinerunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CommandlineRunnerApplication implements CommandLineRunner {

   Logger logger = LoggerFactory.getLogger(CommandlineRunnerApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(CommandlineRunnerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
       logger.info("Hello, world! From commandline runner");
    }

}
