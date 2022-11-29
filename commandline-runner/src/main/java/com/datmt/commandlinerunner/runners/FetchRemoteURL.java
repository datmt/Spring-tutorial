package com.datmt.commandlinerunner.runners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class FetchRemoteURL implements CommandLineRunner {
    Logger logger = LoggerFactory.getLogger(FetchRemoteURL.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("Remote data fetched");
    }
}
