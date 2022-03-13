package com.datmt.spring.test_setup.base;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public abstract class ContainerBase {

    public static final PostgreSQLContainer DB_CONTAINER;

    static  {
        DB_CONTAINER = new PostgreSQLContainer("postgres:13.6-alpine")
                .withDatabaseName("openexl")
                .withUsername("root")
                .withPassword("root");


        DB_CONTAINER.start();

    }

    @Container
    private static PostgreSQLContainer container = DB_CONTAINER;


    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.username", DB_CONTAINER::getUsername);
        registry.add("spring.datasource.password", DB_CONTAINER::getPassword);
        registry.add("spring.datasource.url", DB_CONTAINER::getJdbcUrl);
    }
}
