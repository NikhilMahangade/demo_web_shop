package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    @FindBy(id = "Email")
    private WebElement emailInput;

    @FindBy(id = "Password")
    private WebElement passwordInput;

    @FindBy(css = "input.button-1.login-button")
    private WebElement loginButton;

    @FindBy(className = "ico-logout")
    private WebElement logoutButton;

    @FindBy(css = ".validation-summary-errors")
    private WebElement errorMessage;

    @FindBy(xpath = "//span[@class='forgot-password']/a")
    private WebElement forgotPasswordLink;

    @FindBy(css = "div.page-title h1")
    private WebElement passwordRecoveryTitle;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    // Add this method for easy login in one call
    public void login(String username, String password) {
        enterEmail(username);
        enterPassword(password);
        clickLoginButton();
    }

    public boolean isLogoutButtonDisplayed() {
        try {
            return logoutButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getInvalidLoginAlert() {
        try {
            return errorMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public void clickForgotPasswordButton() {
        forgotPasswordLink.click();
    }

    public String getPasswordRecoveryTitle() {
        return passwordRecoveryTitle.getText();
    }
}