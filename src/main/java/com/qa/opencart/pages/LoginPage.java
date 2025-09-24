package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private static final Logger log = LogManager.getLogger(LoginPage.class);
	/**
	 * Page class is responsible for behaviour of the page.. Don't write testng annotations here.
	 * page class is classic example of encapsulation.
	 * Hw-- Add more test cases for login page.
	 */
	
	// Private By locators: Page Objects

	private final By emailID = By.id("input-email");
	private final By pwd = By.id("input-password");
	private final By header = By.tagName("h2");
	private final By forgotPwdLink = By.linkText("Forgotten Password");
	private final By loginBtn = By.xpath("//input[@value= 'Login']");
	private final By registerLink = By.linkText("Register");
	private final By loginErrorMessg = By.cssSelector("div.alert.alert-danger.alert-dismissible");
	

	// public constructor:
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
	}
	
	//public page method/action
	@Step("Getting login page tiltle")
	public String getLoginPageTitle() {
		
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_SHORT_WAIT);		
//		System.out.println("Page title is : "+ title);
		log.info("Page title is : "+ title);
		return title;
	}
	
	@Step("Getting login page URL")
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.ACC_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_WAIT);
//		System.out.println("Page URL is : "+ url);
		log.info("Page URL is : "+ url);
		return url;
	}
	
	@Step("forgot password link exist")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.isElementDisplayed(forgotPwdLink);
	}
	
	@Step("checking page header is exist...")
	public boolean isheaderExist() {
		return eleUtil.isElementDisplayed(header);
	}
	
	@Step("Login with invalid username: {0} and invalid password {1}")
	public boolean doLoginWithInvalidCredentials(String invalidUN, String invalidPWD) {
		log.info("Application invalid credentials: "+ invalidUN + ":" + invalidPWD);
		WebElement emailEle = eleUtil.waitForElementVisible(emailID, AppConstants.DEFAULT_SHORT_WAIT);
		emailEle.clear();
		emailEle.sendKeys(invalidUN);
		eleUtil.doSendKeys(pwd, invalidPWD);
		eleUtil.doClick(loginBtn);
		String errorMessg = eleUtil.doElementGetText(loginErrorMessg);
		log.info("invalid credentials error message: "+ errorMessg);
		if(errorMessg.contains(AppConstants.LOGIN_BLANK_CREDS_ERROR_MESSG)) {
			return true;
		}		
		else if(errorMessg.contains(AppConstants.LOGIN_INVALID_CREDS_MESSG)) {
			return true;
		}
		return false;
	}
	
	
	
	@Step("Login with correct username: {0} and password {1}")
	public AccountsPage doLogin(String appUserName, String appPassword) {
//		System.out.println("Application credentials: "+ appUserName + ":" + appPassword);
		log.info("Application credentials: "+ appUserName + ":" + "*********");
		eleUtil.waitForElementVisible(emailID, AppConstants.DEFAULT_MEDIUM_WAIT).sendKeys(appUserName);		
		driver.findElement(pwd).sendKeys(appPassword);
		driver.findElement(loginBtn).click();
		return new AccountsPage(driver);		
	}
	
	@Step("Navigating to Register page")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.waitForElementVisible(registerLink, AppConstants.DEFAULT_SHORT_WAIT).click();
		return new RegisterPage(driver);
	}
	
	
}
