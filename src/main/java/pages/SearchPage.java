package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public SearchPage(WebDriver driver) {
        try {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            this.js = (JavascriptExecutor) driver;
            PageFactory.initElements(driver, this);
        } catch (Exception e) {
            System.out.println("Error initializing SearchPage: " + e.getMessage());
        }
    }

    // Locators
    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchBox;

    @FindBy(id = "nav-search-submit-button")
    private WebElement searchBtn;

//    @FindBy(xpath = "//span[text()='Sort by']/following-sibling::span")
//    private WebElement sortDropdown;

    @FindBy(id = "s-result-sort-select")
    private WebElement sortDropdown2;

    @FindBy(xpath = "(//a[@class='a-link-normal s-line-clamp-2 s-line-clamp-3-for-col-12 s-link-style a-text-normal'])[1]")
    private WebElement firstProduct;


    @FindBy(id = "productTitle")
    private WebElement productTitle;

    @FindBy(xpath = "(//span[contains(@class,'priceToPay')]//span[@class='a-price-whole'])[1]")
    private WebElement productPrice;


    @FindBy(xpath = "//*[@id='p_36/dynamic-picker-3']/span/a/span")
    private WebElement rangeElement;

    @FindBy(xpath = "//li[@id='p_72/1318476031']//i")
    private WebElement ratingButton;

    // ---------------- Actions ---------------- //

    public void searchProduct(String productName) {
        try {
            wait.until(ExpectedConditions.visibilityOf(searchBox)).clear();
            searchBox.sendKeys(productName);
            wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
        } catch (Exception e) {
            System.out.println("Error in searchProduct: " + e.getMessage());
        }
    }

    public String searchBlank() {
        try {
            wait.until(ExpectedConditions.visibilityOf(searchBox)).clear();
            wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
        } catch (Exception e) {
            System.out.println("Error in searchBlank: " + e.getMessage());
        }
        return driver.getTitle();
    }

    public void selectBrand(String brand) {
        try {
            By brandLocator = By.xpath("//span[contains(@class,'a-size-base a-color-base') and text()='" + brand + "']/preceding-sibling::div");
            wait.until(ExpectedConditions.elementToBeClickable(brandLocator)).click();
        } catch (Exception e) {
            System.out.println("Error selecting brand: " + e.getMessage());
        }
    }

    public void clickFirstProduct() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(firstProduct)).click();
        } catch (Exception e) {
            System.out.println("Error clicking first product: " + e.getMessage());
            js.executeScript("arguments[0].click();", firstProduct);
        }
    }

    public boolean verifyResultsDisplayed() {
        try {
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//div[@data-component-type='s-search-result']"))).size() > 0;
        } catch (Exception e) {
            System.out.println("Error verifying results: " + e.getMessage());
            return false;
        }
    }

    public String getSelectedProductTitle() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(productTitle)).getText();
        } catch (Exception e) {
            System.out.println("Error getting product title: " + e.getMessage());
            return "Title not available";
        }
    }

    public String getSelectedProductPrice() {
        try {
            String priceText = wait.until(ExpectedConditions.visibilityOf(productPrice))
                                   .getAttribute("innerText")
                                   .replace(".", "") // remove decimal dot
                                   .trim();
            return priceText;
        } catch (Exception e) {
            System.out.println("Error getting product price: " + e.getMessage());
            return "Price not available";
        }
    }

    public void applyPriceRange() {
        try {
            js.executeScript("arguments[0].scrollIntoView(true);", rangeElement);
            wait.until(ExpectedConditions.elementToBeClickable(rangeElement)).click();
        } catch (Exception e) {
            System.out.println("Error applying price range: " + e.getMessage());
        }
    }

    public void applyCustomerRatings() {
        try {
            js.executeScript("arguments[0].scrollIntoView(true);", ratingButton);
            wait.until(ExpectedConditions.elementToBeClickable(ratingButton)).click();
        } catch (Exception e) {
            System.out.println("Error applying customer ratings: " + e.getMessage());
        }
    }

    public void selectSortOption(String optionText) {
        try {
        	js.executeScript("arguments[0].click();", sortDropdown2);
            System.out.println("⚡ Sort dropdown clicked using JS fallback.");

            By optionLocator = By.xpath("//a[@id='s-result-sort-select_1' and text()='" + optionText + "']");

            System.out.println("🔹 Option Locator XPath: " + optionLocator.toString());

            WebElement optionElement = driver.findElement(optionLocator);
            js.executeScript("arguments[0].click();", optionElement);
            System.out.println("⚡ Sort option '" + optionText + "' clicked using JS fallback.");
        }  catch (Exception e) {
            System.out.println("❌ Error selecting sort option: " + e.getMessage());
        }
    }

}
