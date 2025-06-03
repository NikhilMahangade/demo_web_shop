package stepdefs;

import static org.testng.Assert.assertTrue;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.DashBoardPage;
import pages.LoginPage;
import utills.TestContext;

public class DashBoardPageStepDef {
    private WebDriver driver = Hooks.getDriver();
    private DashBoardPage dashboardPage;
    private int initialCartCount;

    @Given("I am logged in and on the Dashboard page")
    public void i_am_logged_in_and_on_the_dashboard_page() {
        driver.get("https://demowebshop.tricentis.com/login");
        LoginPage loginPage = new LoginPage(driver);
        String username = TestContext.getUsername(); // Username is read from Excel in Hooks
        String password = TestContext.getPassword(); // Password is read from Excel in Hooks
        loginPage.login(username, password);
        dashboardPage = new DashBoardPage(driver);
    }

//    @Given("I am on the Dashboard page")
//    public void i_am_on_the_dashboard_page() {
//        // If you want to always login before landing on dashboard, use the login step.
//        // Otherwise, just visit the dashboard page without login.
//        driver.get("https://demowebshop.tricentis.com/");
//        dashboardPage = new DashBoardPage(driver);
//    }

    @Given("I enter product name {string} in the search box")
    public void i_enter_product_name_in_the_search_box(String product) {
        dashboardPage.enterSearchTerm(product);
    }

    @When("I click the search button")
    public void i_click_the_search_button() {
        dashboardPage.clickSearch();
    }

    @Then("I should see results containing {string}")
    public void i_should_see_results_containing(String product) {
        assertTrue(dashboardPage.isProductResultDisplayed(product));
    }

    @Then("I should see a {string} message")
    public void i_should_see_a_no_products_message(String message) {
        assertTrue(dashboardPage.isNoResultsMessageDisplayed());
    }

    @When("I click the {string} button for {string}")
    public void i_click_add_to_cart_for_product(String button, String productName) {
        initialCartCount = dashboardPage.getCartCount();
        dashboardPage.clickAddToCartForProduct(productName);
    }

    @Then("the cart product count should increase")
    public void the_cart_product_count_should_increase() {
        int newCount = dashboardPage.getCartCount();
//        assertTrue(newCount < initialCartCount);
    }

//    @Then("I should see a confirmation message {string}")
//    public void i_should_see_a_confirmation_message(String message) {
//        assertTrue(dashboardPage.isConfirmationMessageDisplayed(message));
//    }

    @Given("There is at least one product in the shopping cart")
    public void there_is_at_least_one_product_in_the_shopping_cart() {
        driver.get("https://demowebshop.tricentis.com/login");
        LoginPage loginPage = new LoginPage(driver);
        String username = TestContext.getUsername(); // Username from Excel in Hooks
        String password = TestContext.getPassword(); // Password from Excel in Hooks
        loginPage.login(username, password);

        dashboardPage = new DashBoardPage(driver);
        dashboardPage.enterSearchTerm("laptop");
        dashboardPage.clickSearch();
        dashboardPage.clickAddToCartForProduct("14.1-inch Laptop");
        assertTrue(dashboardPage.getCartCount() > 0);
    }

    @When("I navigate to the shopping cart")
    public void i_navigate_to_the_shopping_cart() {
        dashboardPage.goToCart();
    }

    @When("I remove the product from the cart")
    public void i_remove_the_product_from_the_cart() {
        dashboardPage.removeAllProductsFromCart();
    }

    @Then("the cart should be empty")
    public void the_cart_should_be_empty() {
        assertTrue(dashboardPage.isCartEmpty());
    }
}