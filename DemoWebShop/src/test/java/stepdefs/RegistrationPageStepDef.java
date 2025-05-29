package stepdefs;

import static org.testng.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.RegistrationPage;

public class RegistrationPageStepDef {

    private WebDriver driver = Hooks.getDriver();
    private RegistrationPage registrationPage;
    private static final Logger logger = LogManager.getLogger(RegistrationPageStepDef.class);

    @Given("I am on the Tricentis Demo Web Shop Registration page")
    public void i_am_on_the_tricentis_demo_web_shop_registration_page() {
        driver.get("https://demowebshop.tricentis.com/register");
        registrationPage = new RegistrationPage(driver);
        logger.info("Navigated to registration page");
    }

    @Given("I select male gender")
    public void i_select_male_gender() {
        registrationPage.genderClick();
        logger.info("Selected male gender");
    }

    @When("I enter first name {string} and last name {string} and email {string}")
    public void i_enter_firstname_lastname_and_email(String firstName, String lastName, String email) {
        registrationPage.enterFirstName(firstName);
        registrationPage.enterLastName(lastName);
        registrationPage.enterEmail(email);
    }

    @When("I enter password {string} and confirm password {string}")
    public void i_enter_password_and_confirm_password(String password, String confirmPassword) {
        registrationPage.enterPassword(password);
        registrationPage.enterConfirmPassword(confirmPassword);
    }

    @When("I click on the Register button")
    public void i_click_on_the_register_button() {
        registrationPage.clickOnRegistration();
        logger.info("Clicked register button");
    }

    @Then("I should be successfully registered and see the logout button")
    public void i_should_be_successfully_registered_and_see_logout() {
        assertTrue(registrationPage.isLogoutButtonDisplayed(), 
            "Expected registration to succeed, but logout button not found. Possible error: " + registrationPage.getErrorMessage());
        logger.info("Logout button displayed, registration successful");
    }

    @When("I submit invalid registration with firstname {string}, lastname {string}, email {string}, password {string}, confirm password {string}")
    public void i_submit_invalid_registration(String firstName, String lastName, String email, String password, String confirmPassword) {
        registrationPage.enterFirstName(firstName);
        registrationPage.enterLastName(lastName);
        registrationPage.enterEmail(email);
        registrationPage.enterPassword(password);
        registrationPage.enterConfirmPassword(confirmPassword);
        registrationPage.clickOnRegistration();
        logger.info("Submitted invalid registration");
    }

    @Then("I should see a registration error containing {string}")
    public void i_should_see_a_registration_error(String expectedMessage) {
        String actualMessage = registrationPage.getErrorMessage();
        assertTrue(actualMessage.contains(expectedMessage), 
            "Expected: " + expectedMessage + ", Actual: " + actualMessage);
        logger.info("Error message displayed: " + actualMessage);
    }
}