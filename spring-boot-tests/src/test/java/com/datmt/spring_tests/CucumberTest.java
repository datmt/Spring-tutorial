package com.datmt.spring_tests;

import com.datmt.spring_tests.containers.ContainerBase;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("com/datmt/spring_tests/features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.datmt.spring_tests")
@CucumberContextConfiguration
@SpringBootTest
@Testcontainers
public class CucumberTest extends ContainerBase {
}
