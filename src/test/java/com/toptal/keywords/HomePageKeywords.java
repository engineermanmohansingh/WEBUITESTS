package com.toptal.keywords;

import org.openqa.selenium.By;

import com.bravura.pageobjects.HomePageObjects;
import com.monibox.framework.webDriverCreator;
import com.monibox.helperUtils.AESCryptoGenerator;
import static  com.monibox.helperUtils.ConfigPropertyReader.getProperty;
public class HomePageKeywords {
		webDriverCreator driver;
		HomePageObjects homePage = new HomePageObjects();
	public HomePageKeywords(webDriverCreator driver) {
		this.driver = driver;
	}
	
	public void SignInToTheApp(String username,String password) {
		driver.page.clickElement(homePage.signIn_Button);
		driver.page.isElementDisplayed(homePage.email_TextField);
		driver.page.enterText(homePage.email_TextField, username);
		driver.page.enterText(homePage.password_TextField, AESCryptoGenerator.decrypt(password,System.getProperty("secret",getProperty("browser"))));
		driver.page.clickElement(homePage.signInSubmit_Button);
		driver.page.isElementDisplayed(homePage.signOut_Button);
	}
	
	public void SignOutFromApp() {
		driver.page.isElementDisplayed(homePage.signOut_Button);
		driver.page.clickElement(homePage.signOut_Button);
		driver.page.isElementDisplayed(homePage.signIn_Button);
	}
}
