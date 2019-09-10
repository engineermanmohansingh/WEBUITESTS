package com.bravura.tests;

import org.testng.annotations.Test;

import com.monibox.automation.PreAndPostTestEvents;

public class LoginLogoutTest extends PreAndPostTestEvents {

	@Test(description="As a user, I must be able to login to commerce site")
	public void TC01_LoginToECommerceSite() {
		driver.launchApplication("http://www.automationpractice.com");
	}
}
