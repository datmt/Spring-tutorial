Feature: Making investment

  Scenario: Create multiple investments
    Given I current have no investment
    Given I create an investment with "1000"  with the name "Invest in the future"
    And I create an investment with "2000"  with the name "Invest in the next big thing"
    And I create an investment with "3000"  with the name "Invest in the next small thing"
    Then I should have "3" investments with the total of "6000" in my investment account