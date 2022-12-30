package com.datmt.spring_tests;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class ContainerBase {

    @Container
    public static GenericContainer mongoDBContainer = new GenericContainer("mongo:5.0")
            .withExposedPorts(27017)
            .withEnv("MONGO_INITDB_ROOT_USERNAME", "datmt_root")
            .withEnv("MONGO_INITDB_ROOT_PASSWORD", "datmt_root");

    @DynamicPropertySource
    static void mongoProperties(org.springframework.test.context.DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.host", mongoDBContainer::getHost);
        registry.add("spring.data.mongodb.port", mongoDBContainer::getFirstMappedPort);
        registry.add("spring.data.mongodb.database", () -> "app_db");
        registry.add("spring.data.mongodb.username", () -> "datmt_root");
        registry.add("spring.data.mongodb.password", () -> "datmt_root");
    }

    @BeforeAll
    public static void beforeAll() {
        mongoDBContainer.start();
    }
}
