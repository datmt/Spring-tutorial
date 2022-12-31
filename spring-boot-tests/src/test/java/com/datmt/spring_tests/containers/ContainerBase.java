package com.datmt.spring_tests.containers;

import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.lifecycle.Startables;

public abstract class ContainerBase {

    static final GenericContainer mongo;

    static {
        mongo = new GenericContainer("mongo:6.0.3")
                .withExposedPorts(27017)
                .waitingFor(Wait.forLogMessage(".*Waiting for connections.*\\n", 1))
                .withEnv("MONGO_INITDB_ROOT_USERNAME", "datmt_root")
                .withReuse(true)
                .withEnv("MONGO_INITDB_ROOT_PASSWORD", "datmt_root");
    }

    static {
        Startables.deepStart(mongo).join();
    }

    @DynamicPropertySource
    static void mongoProperties(org.springframework.test.context.DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.host", mongo::getHost);
        registry.add("spring.data.mongodb.port", mongo::getFirstMappedPort);
        registry.add("spring.data.mongodb.database", () -> "app_db");
        registry.add("spring.data.mongodb.username", () -> "datmt_root");
        registry.add("spring.data.mongodb.password", () -> "datmt_root");
    }


}
