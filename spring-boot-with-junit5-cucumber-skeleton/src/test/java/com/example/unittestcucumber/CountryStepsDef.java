package com.example.unittestcucumber;

import com.example.unittestcucumber.model.Country;
import com.example.unittestcucumber.repository.CountryRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class CountryStepsDef {

    @Autowired
    CountryRepository countryRepository;

    private String countryName;
    private String countryCode;

    private Country savedCountry;

    @Given("a country with name {string} and country code {string}")
    public void aCountryWithNameAndCountryCode(String countryName, String countryCode) {
        this.countryCode = countryCode;
        this.countryName = countryName;

    }

    @When("calling to save the country")
    public void callingToSaveTheCountry() {
        var country = new Country(null, countryName, countryCode);
        assertDoesNotThrow(() -> savedCountry = countryRepository.save(country));
    }

    @Then("the country exists in DB with name {string} and code {string}")
    public void theCountryExistsInDBWithNameAndCode(String name, String code) {

        assertEquals(name, savedCountry.getName());
        assertEquals(code, savedCountry.getCode());
    }
}
