Feature: As tester i want to validate the Search functionality using specific criteria

  @TestScenario
#    Update scenario outline description
  Scenario: Test search option
    Given Lieferando search URL
    When Tester Enter address
    And Choose the first suggested address option

