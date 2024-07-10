package com.toptal.keywords;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.bravura.pageobjects.CartPageObjects;
import com.monibox.framework.webDriverCreator;

public class CartPageKeywords {
	webDriverCreator driver;

	CartPageObjects cartPage = new CartPageObjects();

	public CartPageKeywords(webDriverCreator driver) {
		this.driver = driver;
	}

	public void openCart() {
		driver.page.isElementDisplayed(cartPage.cart_hoverDown);
		driver.page.clickElement(cartPage.cart_hoverDown);
		driver.page.isElementDisplayed(cartPage.proceedToCheckout_Button);
	}

	public void removeItemFromCart(String itemDescription) {
		for (WebElement row : driver.page.findAll(cartPage.cart_summaryTable)) {
			if (row.findElement(By.className("cart_description")).getText().contains(itemDescription)) {
				driver.page.logMessage(row.findElement(By.className("cart_description")).getText());
				driver.page.clickElement(By.xpath("//td[@class='cart_description']//a[text()='" + itemDescription
						+ "']/../../..//*[@title='Delete']"));
			}
		}
	}

	public void checkoutShoppingCart(List<String> Address,String totalAmount,String paymentType) {
		driver.page.isElementDisplayed(cartPage.proceedToCheckout_Button);
		driver.page.clickElement(cartPage.proceedToCheckout_Button);
		for (String info : Address) {

			driver.page.isElementDisplayed(
					By.xpath("//h3[text()='Your delivery address']/../following-sibling::li[contains(.,'"+info+"')]"));
		}
		driver.page.logSuccessMessage("Verified Delivery address");
		for (String info : Address) {
			driver.page.isElementDisplayed(
					By.xpath("//h3[text()='Your billing address']/../following-sibling::li[contains(.,'"+info+"')]"));

		}
		driver.page.logSuccessMessage("Verified Billing address");
		
		driver.page.clickElement(cartPage.proceedToCheckout_Button);
		driver.page.isElementDisplayed(cartPage.termsOfService_Checkbox);
		driver.page.clickElement(cartPage.termsOfService_Checkbox);
		driver.page.clickElement(cartPage.finalCheckout_Button);
		
		driver.page.verifyElementText(cartPage.totalAmount_label, totalAmount);
		driver.page.isElementDisplayed(By.xpath("//a[contains(@title,'Pay by "+paymentType+"')]"));

		driver.page.clickElement(By.xpath("//a[contains(@title,'Pay by "+paymentType+"')]"));
		
		driver.page.clickElement(cartPage.confirmOrder_Button);
	}
}
