package com.example.unittestcucumber;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

/*
@SpringBootTest
@ExtendWith(SpringExtension.class)
@CucumberContextConfiguration
@CucumberOptions(plugin = {"pretty"},
		features = "src/test/resources/features",
		glue= "com.example.unittestcucumber"
)
*/
@Suite
@IncludeEngines("cucumber")
@SpringBootTest
@ExtendWith(SpringExtension.class)
@CucumberContextConfiguration
@SelectClasspathResource("features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.example.unittestcucumber")
class UnittestCucumberApplicationTests {
//this is the entry point
}
