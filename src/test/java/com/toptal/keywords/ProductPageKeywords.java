package com.toptal.keywords;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.bravura.pageobjects.ProductPageObjects;
import com.mongodb.Mongo.Holder;
import com.monibox.framework.webDriverCreator;

public class ProductPageKeywords {
	webDriverCreator driver;
	ProductPageObjects productPage = new ProductPageObjects();

	public ProductPageKeywords(webDriverCreator driver) {
		this.driver = driver;
	}

	public void searchForSpecificProduct(String product) {
		driver.page.isElementDisplayed(productPage.search_TextField);
		driver.page.enterText(productPage.search_TextField, product);
		driver.page.clickElement(productPage.search_Button);
		driver.page.isElementDisplayed(productPage.productList_Table);
	}

	public void verifyProductRequirementAndAddToCart(String productDescription) {
		for (WebElement item : driver.page.findAll(productPage.listedProduct_Labels)) {
			driver.page.logMessage("Listed item :"+item.getText());
			
			if (item.getText().contains(productDescription)) {
				driver.page.executeJavascript("$('h5[itemprop=name]').mouseover();");
				driver.page.logMessage("Found item :"+item.getText());
				driver.page.clickElement(By.xpath("//*[@class='product-container']//h5[contains(.,'"+productDescription+"')]/..//*[contains(@class,'ajax_add_to_cart_button')]"));
				driver.page.clickElement(productPage.cross_Button);

			}
		}
	}

	public void sortSearchResults(String criteria) {
		driver.page.executeJavascript("$('#selectProductSort').find('option:contains(\""+criteria+"\")').attr(\"selected\",true);");
		driver.page.executeJavascript("$('#selectProductSort').click();");
		driver.page.wait.waitForJavaScriptToLoadCompletely();
		driver.page.wait.waitForPageToLoadCompletely();
//		driver.page.findFreshElement(productPage.productSort_Dropdown);
//		driver.page.clickElement(productPage.productSort_Dropdown);
//		driver.page.selectProvidedTextFromDropDown(productPage.productSort_Dropdown, criteria);
	}

	public void verifyFirstProductPriceMatchesTheExpectation(String Threshold) {
		driver.page.executeJavascript("$('h5[itemprop=name]').mouseover();");

		String price = driver.page.findAll(productPage.listedProduct_Labels).get(0)
				.findElement(By.xpath("//*[@itemprop='price']")).getText();
		driver.page.hardAssert(price,Threshold);
	}
}
