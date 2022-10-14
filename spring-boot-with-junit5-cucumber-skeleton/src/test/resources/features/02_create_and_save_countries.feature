Feature: Create and save country
  Scenario: Create a new country
    Given a country with name "Wakanda" and country code "WKA"
    When calling to save the country
    Then the country exists in DB with name "Wakanda" and code "WKA"