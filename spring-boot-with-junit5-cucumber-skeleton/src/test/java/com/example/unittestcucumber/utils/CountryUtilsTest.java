package com.example.unittestcucumber.utils;

import org.h2.util.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CountryUtilsTest {

    @Test
    @DisplayName("Invalid country code must throw exception")
    void validateCountryCode() {
        assertThrows(RuntimeException.class, () -> CountryUtils.validateCountryCode("HH"));
        assertThrows(RuntimeException.class, () -> CountryUtils.validateCountryCode("HHm"));
    }

    @Test
    @DisplayName("Validate country name")
    void validateCountryName() {
        assertThrows(AssertionError.class, () -> CountryUtils.validateCountryName(null));

        assertThrows(AssertionError.class, () -> CountryUtils.validateCountryName(String.format("%0257d", 1)));
    }
}