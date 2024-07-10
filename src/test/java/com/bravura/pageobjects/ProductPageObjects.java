package com.bravura.pageobjects;

import org.openqa.selenium.By;

public class ProductPageObjects {
	public By search_TextField = By.id("search_query_top");
	public By search_Button = By.name("submit_search");
	public By productList_Table = By.cssSelector("*[class^=product_list]");
	public By listedProduct_Labels = By.xpath("//*[@class='product-container']//h5");
	public By cross_Button = By.className("cross");
	public By productSort_Form = By.id("productsSortForm");
	public By productSort_Dropdown = By.xpath("//*[@id='productsSortForm']//select[contains(@class,'selectProductSort')]");
	
}
