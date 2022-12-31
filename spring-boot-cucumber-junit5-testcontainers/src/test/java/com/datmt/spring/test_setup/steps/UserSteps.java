package com.datmt.spring.test_setup.steps;

import com.datmt.spring.test_setup.entity.User;
import com.datmt.spring.test_setup.repository.UserRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserSteps {
    @Autowired
    UserRepository userRepository;
    List<User> users;

    @Given("I create an user named {string} and store to database")
    public void iCreateAnUserNamedAndStoreToDatabase(String arg0) {
        User user = new User();
        user.setName(arg0);
        userRepository.save(user);
    }

    @When("I search for that user by the name {string}")
    public void iSearchForThatUserByTheName(String arg0) {
        users = userRepository.findUsersByName(arg0);
    }

    @Then("I should find at least one result")
    public void iShouldFindAtLeastOneResult() {
        Assertions.assertTrue(users.size() > 0);
    }

    @Given("No users exist")
    public void noUsersExist() {
        userRepository.deleteAll();
    }

    @Then("I should have {} users")
    public void iShouldHaveUsers(int userCount) {
        Assertions.assertEquals(userCount, userRepository.count());
    }


}
