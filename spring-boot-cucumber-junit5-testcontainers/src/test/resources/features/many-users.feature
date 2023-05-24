Feature: Many users

  Background: Clean up
    Given No users exist

  Scenario: Create and find many users
    Given I create an user named "John" and store to database
    Given I create an user named "Johnny" and store to database
    Then I should have 2 users

