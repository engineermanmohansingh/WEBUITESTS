package com.bravura.pageobjects;

import org.openqa.selenium.By;

public class CartPageObjects {

	public By cart_hoverDown = By.xpath("//a[@title='View my shopping cart']");
	public By cart_summaryTable = By.xpath("//table[@id='cart_summary']/tbody/tr");
	public By proceedToCheckout_Button = By.xpath("//span[text()='Proceed to checkout']");
	public By termsOfService_Checkbox = By.className("checker");
	public By totalAmount_label = By.id("total_price");
	public By confirmOrder_Button = By.xpath("//button[contains(.,'I confirm my order')]");
	public By orderSuccess_MessageLabel = By.xpath("//p[contains(.,'Your order on My Store is complete.')]");
	public By finalCheckout_Button = By.cssSelector("*[name=processCarrier]");
}
