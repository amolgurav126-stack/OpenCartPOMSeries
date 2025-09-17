package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class LoginPageNegativeTest extends BaseTest {
	
	@DataProvider
	public Object[][] invalidLoginData() {
		return new Object[][] {
			{"amiolsdh", "test@1213"},
			{"amol", "Selenium@12234"},
			{"March2024@open.com","Test@324"}, 
			{"","Test@132"},
			{"", ""}			
		};
	}
	
	
	@Test(dataProvider = "invalidLoginData")
	public void negativeLoginTest(String invalidUserName, String invalidPWD) {
		Assert.assertTrue(loginPage.doLoginWithInvalidCredentials(invalidUserName, invalidPWD));
	}

}
