Feature: Test default hello

  Scenario: Default controller
    When making request to the root URL
    Then value is "Hey you"

  Scenario: Hello with name
    When making request with a name "Julia"
    Then the greeting string must be "Hello, Julia"