package com.example.unittestcucumber.utils;

import java.util.Locale;

public class CountryUtils {

    public static void validateCountryName(String name) {
        assert name != null;
        assert name.length() < 255;
    }

    public static void validateCountryCode(String code) {
        if (!code.toUpperCase(Locale.ROOT).equals(code)) {
            throw new RuntimeException("country code must be in uppercase");
        }

        if (code.trim().length() != 3) {
            throw new RuntimeException("country code must be 3 characters");
        }

    }
}
