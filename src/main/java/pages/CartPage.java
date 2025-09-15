package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public CartPage() {
        this.driver = null;
    }

    @FindBy(xpath = "(//input[@id='add-to-cart-button'])[2]")
    private WebElement addCartButton;


    @FindBy(xpath = "//span[@id='productTitle']")
    private WebElement productTitle;

    @FindBy(xpath = "//div[@id='attach-cart-info-content']//span[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'added to cart')]")
    private WebElement successMessage;


    @FindBy(xpath = "//a[@id='nav-cart']")
    private WebElement goToCart;

    @FindBy(xpath = "//div[contains(@class,'sc-list-item')][1]//span[contains(@class,'a-truncate-cut')]")
    private WebElement cartProductTitleElement;

    @FindBy(xpath = "(//span[contains(@class,'a-icon-small-add')])[1]")
    private WebElement increaseProductCount;
  
    @FindBy(xpath="(//div[contains(@id,'sc-active')]//fieldset/button[1])[1])")
    private WebElement decreaseProductCount;

    @FindBy(xpath = "(//input[contains(@data-feature-id,'item-delete-button')])[1]")
    private WebElement deleteFromCart;

    @FindBy(xpath = "//input[@name='proceedToRetailCheckout']")
    private WebElement proceedToBuy;

    @FindBy(xpath = "//span[@id='deliver-to-address-text']")
    private WebElement address;

    @FindBy(xpath = "//span[@id='checkout-primary-continue-button-id-announce']")
    private WebElement orderPlace;


    // ---------------- Methods ---------------- //

    public void clickAddToCart() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(addCartButton));
            actions.moveToElement(addCartButton).click().perform();
            System.out.println("✅ Product added to cart button clicked.");
        } catch (Exception e) {
            System.out.println("❌ Failed to click Add to Cart: " + e.getMessage());
        }
    }

    public String getProductTitle() {
        try {
            wait.until(ExpectedConditions.visibilityOf(productTitle));
            return productTitle.getText();
        } catch (Exception e) {
            System.out.println("❌ Failed to get product title: " + e.getMessage());
            return "Title not available";
        }
    }

    public String getSuccessMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(successMessage));
            return successMessage.getText();
        } catch (Exception e) {
            System.out.println("❌ Failed to get success message: " + e.getMessage());
            return "Success message not available";
        }
    }

    public void clickGoToCart() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(goToCart));
            actions.moveToElement(goToCart).click().perform();
            System.out.println("✅ Navigated to cart.");
        } catch (Exception e) {
            System.out.println("❌ Failed to click Go to Cart: " + e.getMessage());
        }
    }

    public String getCartProductTitle() {
        try {
            wait.until(ExpectedConditions.visibilityOf(cartProductTitleElement));
            return cartProductTitleElement.getText();
        } catch (Exception e) {
            System.out.println("❌ Failed to get cart product title: " + e.getMessage());
            return "Cart product title not available";
        }
    }

    public void increaseProductCount() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(increaseProductCount)).click();
            System.out.println("✅ Increased product count.");
        } catch (Exception e) {
            System.out.println("❌ Failed to increase product count: " + e.getMessage());
        }
    }

    public void decreaseProductCount() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(decreaseProductCount)).click();
            System.out.println("✅ Decreased product count.");
        } catch (Exception e) {
            System.out.println("❌ Failed to decrease product count: " + e.getMessage());
        }
    }

    public boolean deleteProductFromCart() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(deleteFromCart)).click();
            System.out.println("✅ Product delete button clicked.");
            // Check removal text
            return driver.getPageSource().contains("was removed from Shopping Cart");
        } catch (Exception e) {
            System.out.println("❌ Failed to delete product from cart: " + e.getMessage());
            return false;
        }
    }



    public void cartAdd() {
        try {
            clickAddToCart();
            String title = getProductTitle();
            String message = getSuccessMessage();
            clickGoToCart();
            String cartTitle = getCartProductTitle();

            System.out.println("🛒 Product Title: " + title);
            System.out.println("🛒 Success Message: " + message);
            System.out.println("🛒 Cart Product Title: " + cartTitle);
        } catch (Exception e) {
            System.out.println("❌ Error during cart add flow: " + e.getMessage());
        }
    }

    public void quantityModify() {
        try {
            increaseProductCount();
            decreaseProductCount();
        } catch (Exception e) {
            System.out.println("❌ Error modifying quantity: " + e.getMessage());
        }
    }

    public void proceedToBuyFromCart() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(proceedToBuy)).click();
            System.out.println("✅ Proceeding to buy from cart.");
        } catch (Exception e) {
            System.out.println("❌ Failed to proceed to buy: " + e.getMessage());
        }
    }

    public String getAddress() {
        try {
            wait.until(ExpectedConditions.visibilityOf(address));
            return address.getText();
        } catch (Exception e) {
            System.out.println("❌ Failed to get address: " + e.getMessage());
            return "Address not available";
        }
    }

    public boolean buyingIsEnabled() {
        try {
            wait.until(ExpectedConditions.visibilityOf(orderPlace));
            return orderPlace.isEnabled();
        } catch (Exception e) {
            System.out.println("❌ Failed to check if buying is enabled: " + e.getMessage());
            return false;
        }
    }
}
