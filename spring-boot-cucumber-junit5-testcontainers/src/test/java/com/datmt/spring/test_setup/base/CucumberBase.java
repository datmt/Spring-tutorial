package com.datmt.spring.test_setup.base;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@CucumberContextConfiguration
@SpringBootTest
@CucumberOptions(plugin = {"pretty"}, features = "src/test/resources/features")
public class CucumberBase extends ContainerBase {


}
