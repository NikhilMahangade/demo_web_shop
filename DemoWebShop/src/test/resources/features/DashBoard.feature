Feature: Dashboard Functionality with User Login

  As a user of the Demo Web Shop
  I want to be able to log in, search for products, and add or remove them from my cart
  So that I can easily shop and manage my orders

  Background:
    Given I am logged in and on the Dashboard page

  Scenario: Search Box Functionality with a valid product name
    Given I enter product name "laptop" in the search box
    When I click the search button
    Then I should see results containing "laptop"

  Scenario: Search Box Functionality with an invalid product name
    Given I enter product name "abs" in the search box
    When I click the search button
    Then I should see a "No products were found that matched your criteria." message

  Scenario: Adding a product to the cart
    Given I enter product name "laptop" in the search box
    When I click the search button
    And I click the "Add to cart" button for "14.1-inch Laptop"
    Then the cart product count should increase
    And I should see a confirmation message "The product has been added to your shopping cart"

  Scenario: Removing a product from the cart
    Given There is at least one product in the shopping cart
    When I navigate to the shopping cart
    And I remove the product from the cart
    Then the cart should be empty