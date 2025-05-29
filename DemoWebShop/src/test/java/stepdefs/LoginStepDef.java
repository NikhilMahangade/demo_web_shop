package stepdefs;

import static org.testng.Assert.assertTrue;

//import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
//import utills.ExcelReader;
import utills.TestContext;
//import utills.TestContext;

public class LoginStepDef {

    private WebDriver driver = Hooks.getDriver();
    private LoginPage loginPage;
    private static final Logger logger = LogManager.getLogger(LoginStepDef.class);

    @Given("I am on the Tricentis Demo Web Shop Login page")
    public void i_am_on_the_tricentis_demo_web_shop_login_page() {
		driver.get("http://demowebshop.tricentis.com/login");
        loginPage = new LoginPage(driver);
        logger.info("Navigated to login page");
    }

    @When("I enter login email and password")
    public void i_enter_login_email_and_password() {
    	String email = TestContext.getUsername();
        String password = TestContext.getPassword();
     
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
            

		logger.info("Logging in with username: {} and password: {}", email, password);
        }

    

    @When("I click on the login button")
    public void i_click_on_the_login_button() {
        loginPage.clickLoginButton();
        logger.info("Clicked on login button");
    }

    @Then("I should be logged in successfully and see the logout button")
    public void i_should_be_logged_in_successfully() {
        assertTrue(loginPage.isLogoutButtonDisplayed());
        logger.info("Logged in successfully, logout button displayed");
    }

    @Then("I should see a login error containing {string}")
    public void i_should_see_a_login_error(String expectedMessage) {
        String actualMessage = loginPage.getInvalidLoginAlert().trim().replaceAll("\\s+", " ");
        assertTrue(actualMessage.contains(expectedMessage), "Expected: " + expectedMessage + " but got: " + actualMessage);
        logger.info("Login error message displayed: " + actualMessage);
        
    }

    @When("I click on forgot password link")
    public void i_click_on_forgot_password_link() {
        loginPage.clickForgotPasswordButton();
        logger.info("Clicked on forgot password link");
    }

    @Then("I should be redirected to the password recovery page")
    public void i_should_be_redirected_to_password_recovery_page() {
        assertTrue(loginPage.getPasswordRecoveryTitle().equals("Password recovery"));
        logger.info("Redirected to password recovery page");
    }
}