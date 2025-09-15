package pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public LoginPage() {
        this.driver = null;
    }

    @FindBy(xpath = "//button[contains(@class,'a-button-text')]")
    private WebElement submitButton;

    @FindBy(xpath = "//span[@id='nav-link-accountList-nav-line-1']")
    private WebElement loginButton;
    
    @FindBy(xpath = "//*[@id='nav-link-accountList-nav-line-1' and text()='Hello, sign in']")
    private WebElement submitLoginButtonAfterLogin;

    @FindBy(id = "ap_email_login")
    private WebElement emailField;

    @FindBy(xpath = "//input[contains(@class,'a-button-input') and @type='submit']")
    private WebElement continueButton;

    @FindBy(id = "ap_password")
    private WebElement passwordField;

    @FindBy(id = "signInSubmit")
    private WebElement submitLoginButton;

    @FindBy(id = "auth-error-message-box")
    private WebElement loginErrorBox;

    // ---------------- Methods ---------------- //

    public void clickSubmitButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        } catch (TimeoutException e) {
            System.out.println("❌ Submit button not clickable: " + e.getMessage());
        }
    }

    public void clickLoginButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        } catch (TimeoutException e) {
            System.out.println("❌ Login button not clickable: " + e.getMessage());
        }
    }

    public void enterEmail(String email) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOf(emailField));
            element.clear();
            element.sendKeys(email);
        } catch (TimeoutException e) {
            System.out.println("❌ Email field not visible: " + e.getMessage());
        }
    }

    public void clickContinue() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
        } catch (TimeoutException e) {
            System.out.println("❌ Continue button not clickable: " + e.getMessage());
        }
    }

    public void enterPassword(String password) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOf(passwordField));
            element.clear();
            element.sendKeys(password);
        } catch (TimeoutException e) {
            System.out.println("❌ Password field not visible: " + e.getMessage());
        }
    }

    public void clickSubmitLogin() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(submitLoginButton)).click();
        } catch (TimeoutException e) {
            System.out.println("❌ Submit login button not clickable: " + e.getMessage());
        }
    }

    public boolean isLoginErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(loginErrorBox)).isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("ℹ️ Login error box not displayed.");
            return false;
        }
    }

    public void navigateToUrl(String url) {
        try {
            driver.navigate().to(url);
        } catch (Exception e) {
            System.out.println("❌ Failed to navigate to URL: " + url + " → " + e.getMessage());
        }
    }

    public boolean loginButtonDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(loginButton)).isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("ℹ️ Login button not displayed.");
            return false;
        }
    }
    
    public boolean submitLoginButtonAfterLoginDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(submitLoginButtonAfterLogin)).isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("ℹ️ Submit login button (after login) not displayed.");
            return false;
        }
    }


    public boolean loginButtonEnabled() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(loginButton)).isEnabled();
        } catch (TimeoutException e) {
            System.out.println("ℹ️ Login button not enabled.");
            return false;
        }
    }
}
