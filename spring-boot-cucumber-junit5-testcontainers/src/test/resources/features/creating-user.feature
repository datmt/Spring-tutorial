Feature: Create user
  Scenario: Create user and save to database
    Given I create an user named "Jolie" and store to database
    When I search for that user by the name "Jolie"
    Then I should find at least one result
