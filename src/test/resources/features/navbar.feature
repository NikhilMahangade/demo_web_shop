Feature: Wishlist via Navbar

  As an authenticated user of the Demo Web Shop
  I want to login and add a jewelry item to my wishlist via the navigation bar
  So that I can save items for later

  Scenario: Login and add a jewelry product to wishlist from the navbar
    Given I login with valid credentials from Excel
    When I navigate to the home page
    And I click on the Jewelry navbar
    Then I should see a jewelry product "Black & White Diamond Heart" listed
    When I add the jewelry product "Black & White Diamond Heart" to the wishlist
    Then I should see a confirmation message "The product has been added to your wishlist" for wishlist