package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DashBoardPage {
    private WebDriver driver;

    @FindBy(id = "small-searchterms")
    private WebElement searchBox;

    @FindBy(css = "input.button-1.search-box-button")
    private WebElement searchButton;

    @FindBy(css = ".product-item")
    private List<WebElement> productItems;

    @FindBy(css = ".product-title a")
    private List<WebElement> productTitles;

    @FindBy(css = ".product-box-add-to-cart-button")
    private List<WebElement> addToCartButtons;

    @FindBy(css = "p.content")
    private WebElement noResultsMessage;

    @FindBy(css = "span.cart-qty")
    private WebElement cartQuantity;

    @FindBy(css = "p.content")
    private WebElement confirmationMessage;

    @FindBy(className = "ico-cart")
    private WebElement shoppingCartLink;

    @FindBy(css = "input[name^='removefromcart']")
    private List<WebElement> removeCheckboxes;

    @FindBy(css = "input[name='updatecart']")
    private WebElement updateCartButton;

    @FindBy(css = ".order-summary-content")
    private WebElement emptyCartMessage;

    public DashBoardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterSearchTerm(String term) {
        searchBox.clear();
        searchBox.sendKeys(term);
    }

    public void clickSearch() {
        searchButton.click();
    }

    public boolean isProductResultDisplayed(String productName) {
        return productTitles.stream().anyMatch(e -> e.getText().toLowerCase().contains(productName.toLowerCase()));
    }

    public boolean isNoResultsMessageDisplayed() {
        return noResultsMessage.isDisplayed() && noResultsMessage.getText().contains("No products were found");
    }

    public void clickAddToCartForProduct(String productName) {
        for (int i = 0; i < productTitles.size(); i++) {
            if (productTitles.get(i).getText().equalsIgnoreCase(productName)) {
                addToCartButtons.get(i).click();
                break;
            }
        }
    }

    public int getCartCount() {
        String qty = cartQuantity.getText().replaceAll("[^0-9]", "");
        return Integer.parseInt(qty.isEmpty() ? "0" : qty);
    }

    // Uncomment if confirmation message checking is needed elsewhere
//    public boolean isConfirmationMessageDisplayed(String expected) {
//        return confirmationMessage.isDisplayed() && confirmationMessage.getText().contains(expected);
//    }

    public void goToCart() {
        shoppingCartLink.click();
    }

    public void removeAllProductsFromCart() {
        for (WebElement checkbox : removeCheckboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }
        updateCartButton.click();
    }

    public boolean isCartEmpty() {
        return emptyCartMessage.isDisplayed() && emptyCartMessage.getText().contains("Your Shopping Cart is empty!");
    }

    // Only add the product to the cart after searching, without any verification
    public void addProductToCart(String searchTerm, String productName) {
        enterSearchTerm(searchTerm);
        clickSearch();
        clickAddToCartForProduct(productName);
    }
}