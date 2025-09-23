package com.qa.opencart.tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("COP-001: login page epic")
@Feature("COP-002: Login pages functionality")
@Story("COP-003: Login page core features")
public class LoginPageTest extends BaseTest{
	/**
	 * Sequence- Base Test (Before test)--> LoginPageTest(@Test)--->BaseTest(AfterTest)
	 * Don't write any Selenium methods in Test class
	 *If you have a webdriver APIs in your test class/methods, you're doing it wrong--simon stewart
	 */
	
	@Description("Login page title test.....")
	@Owner("Amol Gurav")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void LoginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		ChainTestListener.log("Login page Title: "+ actTitle);
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Description("Login page url test....")
	@Owner("Amol Gurav")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void LoginPageURLTest() {
		String actUrl = loginPage.getLoginPageURL();
		ChainTestListener.log("Login page url: "+ actUrl);
		Assert.assertTrue(actUrl.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
	}

	@Description("Forgot password link exist test....")
	@Owner("Amol Gurav")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void isForgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Description("Login page header test....")
	@Owner("Amol Gurav")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void isheaderExistTest() {
		Assert.assertTrue(loginPage.isheaderExist());
	}
	
	@Description("User able to login to app Test with valid credentials....")
	@Owner("Amol Gurav")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = Integer.MAX_VALUE)
	public void loginTest() {
		accPage =  loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));		
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	
}
