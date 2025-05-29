Feature: Login functionality for Tricentis Demo Web Shop

  As a user of Tricentis Demo Web Shop
  I want to log in with my account
  So that I can access my account features

  Background:
    Given I am on the Tricentis Demo Web Shop Login page

  Scenario: Successful login with valid credentials
    When I enter login email and password
    And I click on the login button
    Then I should be logged in successfully and see the logout button

  Scenario Outline: validate error message after entering invalid username and password
    When I enter login email "<email>" and password "<password>"
    And I click on the login button
    Then I should see a login error containing "<error_message>"

    Examples:
      | email              | password        | error_message                                                                          |
      | invalid@email.com  | wrongPassword   | Login was unsuccessful. Please correct the errors and try again. No customer account found |
      | notanemail         | ValidPassword1  | Login was unsuccessful. Please correct the errors and try again. No customer account found |
      | valid@email.com    | wrongPassword   | Login was unsuccessful. Please correct the errors and try again. No customer account found |

  Scenario: Forgot password navigation
    When I click on forgot password link
    Then I should be redirected to the password recovery page