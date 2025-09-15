package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.ConfigLoader;
import base.DriverSetup;
import pages.LoginPage;

public class LoginTest extends DriverSetup {
    private LoginPage lp;

    @Parameters("browser")
    @BeforeClass
    public void setDriver(String browser) {
        ConfigLoader.loadConfig();
        lp = new LoginPage(DriverSetup.getDriver(browser));
    }

    @Test(groups = "login", priority = 1)
    public void verifyLoginTest() {
        lp.navigateToUrl(ConfigLoader.getProperty("url"));

        Assert.assertTrue(
            lp.loginButtonDisplayed(),
            "❌ Login button should be displayed on the home page"
        );
        Assert.assertTrue(
            lp.loginButtonEnabled(),
            "❌ Login button should be enabled on the home page"
        );
    }

    @Test(groups = "login", priority = 2)
    public void invalidLoginTest() {
        lp.clickLoginButton();
        lp.enterEmail("8777293979"); 
        lp.clickContinue();
        lp.enterPassword("test12");
        lp.clickSubmitLogin();
        Assert.assertTrue(
            lp.isLoginErrorDisplayed(),
            "❌ Error message should be displayed for invalid login"
        );
    }

    @Test(groups = "login", priority = 3)
    public void validLoginTest() {
    	lp.navigateToUrl(ConfigLoader.getProperty("url"));
        lp.clickLoginButton();
        lp.enterEmail(ConfigLoader.getProperty("username"));
        lp.clickContinue();
        lp.enterPassword(ConfigLoader.getProperty("password"));
        lp.clickSubmitLogin();

        // ✅ Assertion to ensure login was successful
        Assert.assertFalse(
            lp.submitLoginButtonAfterLoginDisplayed(),
            "❌ Login button should not be displayed after successful login"
        );
    }
}
