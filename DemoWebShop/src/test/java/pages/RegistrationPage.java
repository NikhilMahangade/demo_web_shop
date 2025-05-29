package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.System.Logger;
import java.time.Duration;

public class RegistrationPage {
    private WebDriver driver;

    @FindBy(id = "gender-male")
    private WebElement genderMaleRadio;

    @FindBy(id = "FirstName")
    private WebElement firstNameInput;

    @FindBy(id = "LastName")
    private WebElement lastNameInput;

    @FindBy(id = "Email")
    private WebElement emailInput;

    @FindBy(id = "Password")
    private WebElement passwordInput;

    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPasswordInput;

    @FindBy(id = "register-button")
    private WebElement registerButton;

    @FindBy(className = "ico-logout")
    private WebElement logoutButton;

    @FindBy(css = ".validation-summary-errors")
    private WebElement errorMessage;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void genderClick() {
        genderMaleRadio.click();
    }

    public void enterFirstName(String firstname) {
        firstNameInput.clear();
        firstNameInput.sendKeys(firstname);
    }

    public void enterLastName(String lastname) {
        lastNameInput.clear();
        lastNameInput.sendKeys(lastname);
    }

    public void enterEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void enterConfirmPassword(String confirmpass) {
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys(confirmpass);
    }

    public void clickOnRegistration() {
        registerButton.click();
    }

    /**
     * Wait up to 10 seconds for the logout button to become visible after registration.
     */
    public boolean isLogoutButtonDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(logoutButton));
            return logoutButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }
}