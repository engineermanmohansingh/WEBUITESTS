package com.toptal.webtests;

import org.testng.annotations.Test;

import com.monibox.framework.PreAndPostTestEvents;
import com.toptal.keywords.CartPageKeywords;
import com.toptal.keywords.HomePageKeywords;
import com.toptal.keywords.ProductPageKeywords;

import static com.monibox.helperUtils.ConfigPropertyReader.getProperty;

import java.util.ArrayList;
import java.util.List;

public class EcommerceScenarioTest extends PreAndPostTestEvents {

	HomePageKeywords homePageTasks;
	ProductPageKeywords productPageTasks;
	CartPageKeywords cartPageTasks;

	public void init() {
		homePageTasks = new HomePageKeywords(driver);
		productPageTasks = new ProductPageKeywords(driver);
		cartPageTasks = new CartPageKeywords(driver);
	}

	 @Test(description="As a user, I must be able to login to commerce site")
	public void TC01_LoginToECommerceSite() {
		driver.launchApplication("http://www.automationpractice.com");
		homePageTasks.SignInToTheApp(getProperty("username"), getProperty("password"));
	}

	 @Test(description="When I search for a given product, I should be able to add them in the cart")
	public void TC02_AddItemsToCartViaSearch() {
		productPageTasks.searchForSpecificProduct("shirts");
		productPageTasks.verifyProductRequirementAndAddToCart("Faded Short Sleeve T-shirts");
	}

	@Test(description = "When I search for product with specific criteria, I should get correct results")
	public void TC03_AddLowestItemToCartViaAdvancedSearch() {
		productPageTasks.searchForSpecificProduct("dress");
		productPageTasks.sortSearchResults("Price: Highest first");
		productPageTasks.verifyFirstProductPriceMatchesTheExpectation("$50.99");
		productPageTasks.sortSearchResults("Price: Lowest first");
		productPageTasks.verifyFirstProductPriceMatchesTheExpectation("$16.51");
		productPageTasks.verifyProductRequirementAndAddToCart("Blouse");
		productPageTasks.verifyProductRequirementAndAddToCart("Printed Chiffon Dress");

	}

	@Test(description = "Then I remove the first item from the cart")
	public void TC04_RemoveItemFromCart() {
		cartPageTasks.openCart();
		cartPageTasks.removeItemFromCart("Blouse");
	}

	 @Test(description="Finally, I should be able to checkout the items in my cart")
	public void TC05_ProceedToCheckoutCartItems() {
		List<String> Address = new ArrayList<String>();
		Address.add("Manmohan Singh");
		Address.add("Testing");
		Address.add("A-69 wilson street 20301");
		Address.add("Washington, Washington 20301");
		Address.add("United States");
		Address.add("88855554");
		cartPageTasks.checkoutShoppingCart(Address,"$34.91","check");
	}

	@Test(description = "After performing my task , I should be able to log out")
	public void TC06_LogoutFromTheSite() {
		homePageTasks.SignOutFromApp();
	}
}
