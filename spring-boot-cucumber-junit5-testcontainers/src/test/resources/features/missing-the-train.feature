Feature: Missing train notification
  Scenario: Missing train
    Given I'm on the train
    And You are not in that train
    Then You know that I'm gone