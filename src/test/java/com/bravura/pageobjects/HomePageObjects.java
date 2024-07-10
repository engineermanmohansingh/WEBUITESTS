package com.bravura.pageobjects;

import org.openqa.selenium.By;

public class HomePageObjects {

	public By signIn_Button = By.linkText("Sign in");
	public By email_TextField = By.id("email");
	public By password_TextField = By.id("passwd");
	public By signInSubmit_Button = By.id("SubmitLogin");
	public By signOut_Button = By.className("logout");
}
