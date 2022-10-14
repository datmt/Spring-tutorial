package com.example.unittestcucumber;

import com.example.unittestcucumber.controller.HelloController;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class HelloControllerSteps {
    @Autowired
    HelloController helloController;

    private String userName;
    @When("making request to the root URL")
    public void makingRequest() {
       assertNotNull("Some shit");
    }


    @Then("value is {string}")
    public void valueIs(String content) {
        assertEquals(helloController.hello(), content);
    }

    @When("making request with a name {string}")
    public void makingRequestWithAName(String userName) {
       this.userName = userName;
    }

    @Then("the greeting string must be {string}")
    public void theGreetingStringMustBe(String controllerResult) {
        assertEquals(controllerResult, helloController.helloWithName(userName));
    }
}
