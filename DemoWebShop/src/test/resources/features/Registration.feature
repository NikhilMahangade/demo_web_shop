Feature: Registration functionality for Tricentis Demo Web Shop

  As a user of Tricentis Demo Web Shop
  I want to register on the website
  So that I can access account-related features

  Background:
    Given I am on the Tricentis Demo Web Shop Registration page

  Scenario: Successful registration with valid data
    Given I select male gender
    When I enter first name "Anup" and last name "Mahto" and email "anup11@gmail.com"
    And I enter password "Test@1234" and confirm password "Test@1234"
    And I click on the Register button
    Then I should be successfully registered and see the logout button

  Scenario Outline: Registration fails with invalid or incomplete data
    When I submit invalid registration with firstname "<firstname>", lastname "<lastname>", email "<email>", password "<password>", confirm password "<confirmpassword>"
    Then I should see a registration error containing "<error_message>"

    Examples:
      | firstname | lastname     | email                   | password    | confirmpassword | error_message                            |
      |           | Mahto        | anup11@gmail.com        | Test@1234   | Test@1234       | First name is required.                  |
      | Anup      |            	 | anup11@gmail.com        | Test@1234   | Test@1234       | Last name is required.                   |
      | Anup      | Mahto        | anup11@gmail.com        | Test@1234   | Test@5678       | The password and confirmation password do not match. |