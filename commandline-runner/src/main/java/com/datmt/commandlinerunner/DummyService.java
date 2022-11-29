package com.datmt.commandlinerunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DummyService {
    Logger logger = LoggerFactory.getLogger(DummyService.class);

    public void doSomething() {
        logger.info("Hello, world! From DummyService");
    }
}
