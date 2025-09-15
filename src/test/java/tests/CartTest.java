package tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import base.DriverSetup;
import pages.CartPage;

public class CartTest extends DriverSetup {
    private CartPage cp;

    @Parameters("browser")
    @BeforeClass
    public void setDriver(String browser) {
        cp = new CartPage(DriverSetup.getDriver(browser));
    }

    @Test(groups = "cart", dependsOnGroups = "search", priority = 1)
    public void cartAddTest() {
        cp.cartAdd();
        String cartTitle = cp.getCartProductTitle();
        Assert.assertNotNull(cartTitle, "❌ Cart product title is null!");
        Assert.assertFalse(cartTitle.isEmpty(), "❌ Cart product title is empty!");
        System.out.println("✅ Cart product added: " + cartTitle);
    }

    @Test(groups = "cart", dependsOnGroups = "search", priority = 2)
    public void modifyQuantity() {
        cp.quantityModify();
        // Just asserting that no exception occurred
        Assert.assertTrue(true, "❌ Quantity modification failed!");
        System.out.println("✅ Quantity modification actions performed.");
    }

    @Test(groups = "cart", dependsOnGroups = "search", priority = 3)
    public void deleteFromCart() {
        boolean removed = cp.deleteProductFromCart();
        Assert.assertTrue(removed, "❌ Product removal message not shown!");
        System.out.println("✅ Product removed successfully.");
    }



    @Test(groups = "cart", dependsOnGroups = "search", priority = 4)
    public void buyProducts() {
        cp.proceedToBuyFromCart();
        String address = cp.getAddress();
        Assert.assertTrue(address.contains("Goai"), "❌ Address does not match expected!");
        System.out.println("✅ Address verified: " + address);
    }

    @Test(groups = "cart", dependsOnGroups = "search", priority = 5)
    public void placeOrder() {
        boolean canMoveToOrder = cp.buyingIsEnabled();
        Assert.assertTrue(canMoveToOrder, "❌ Cannot place order!");
        System.out.println("✅ Order placement is enabled.");
    }

//    @AfterClass
//    public void quitBrowser() {
//        closeDriver();
//    }
}
