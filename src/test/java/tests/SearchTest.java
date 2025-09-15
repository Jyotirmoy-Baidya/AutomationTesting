package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.DriverSetup;
import pages.SearchPage;

public class SearchTest {

    private SearchPage searchPage;

    @Parameters("browser")
    @BeforeClass
    public void setup(String browser) {
        searchPage = new SearchPage(DriverSetup.getDriver(browser));
    }

    @Test(groups = "search", dependsOnGroups = "login", priority = 2)
    public void testSearchProduct() {
        searchPage.searchProduct("laptop");
        Assert.assertTrue(searchPage.verifyResultsDisplayed(), "Search results not displayed!");
    }

    @Test(groups = "search", dependsOnGroups = "login", priority = 3)
    public void testApplyFilters() {
        searchPage.selectBrand("HP");
        searchPage.applyPriceRange();
        //searchPage.applyCustomerRatings();
        Assert.assertTrue(searchPage.verifyResultsDisplayed(), "Filtered results not displayed!");
    }

    @Test(groups = "search", dependsOnGroups = "login", priority = 4)
    public void testSortLowToHigh() {
    	searchPage.selectSortOption("Price: Low to High");
        //searchPage.sortLowToHigh();
        Assert.assertTrue(searchPage.verifyResultsDisplayed(), "Results not visible after Low-High sort!");
    }

    @Test(groups = "search", dependsOnGroups = "login", priority = 1)
    public void testBlankSearch() {
        String title = searchPage.searchBlank();
        Assert.assertTrue(title.contains("Amazon"), "Blank search did not stay on amazon");
    }

    @Test(groups = "search", dependsOnGroups = "login", priority = 5)
    public void testViewProductDetails() {
        searchPage.searchProduct("laptop");
        searchPage.clickFirstProduct();
        DriverSetup.switchTab();

        String title = searchPage.getSelectedProductTitle();
        String price = searchPage.getSelectedProductPrice();

        System.out.println("Product Title: " + title);
        System.out.println("Product Price: " + price);

        Assert.assertFalse(title.isEmpty(), "Product title is not displayed!");
    }
}
