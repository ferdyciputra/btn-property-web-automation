Feature: Property price calculations

  @positive-test
  Scenario Outline: Property price calculations must be in accordance with the applicable formula
    Given the user navigate to property price calculation page
    When the user filled total income "<Total Income>" each month
    And the user filled total expenditure "<Total Expenditure>" each month
    And the user select time period "<Time Period>" years
    And the user click calculate button
    Then the system should return a price calculated according to the formula

    Examples:
      | Total Income | Total Expenditure | Time Period |
      | 10000000     | 2000000           | 10          |
      | 20000000     | 3000000           | 7           |
      | 20000000     | 2000000           | 10          |
      | 12000000     | 3000000           | 6           |
      | 14000000     | 2000000           | 5           |
      | 11000000     | 3000000           | 6           |

  @negative-test
  Scenario: Button calculate is disabled when user has not select time period
    Given the user navigate to property price calculation page
    When the user filled total income "100000" each month
    And the user filled total expenditure "100" each month
    Then the calculate button should be disabled

  @negative-test
  Scenario: Total income should not be less than total expenditure
    Given the user navigate to property price calculation page
    When the user filled total income "1000" each month
    And the user filled total expenditure "2000" each month
    Then the user receive a warning message "Isi kurang dari nilai sebelumnya"