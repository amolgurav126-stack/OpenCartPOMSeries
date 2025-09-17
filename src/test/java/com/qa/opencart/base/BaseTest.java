package com.qa.opencart.base;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.listeners.TestAllureListeners;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

import io.qameta.allure.Description;
//@Listeners(ChainTestListener.class)
//@Listeners({TestAllureListeners.class, ChainTestListener.class})


public class BaseTest {
	protected WebDriver driver;
	DriverFactory df;
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected Properties prop;
	protected RegisterPage registerPage;
	protected CommonsPage commonsPage;
	
	
	/**
	 * Before test method is behaving like pre-condition.
	 */
	@Description("Launch the browser: {0} and URL")
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(@Optional("chrome") String browserName) {
		df = new DriverFactory();
		
		prop = df.initProp();			
		     if(browserName!=null) {
			    prop.setProperty("browser" ,browserName);
		}
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		commonsPage = new CommonsPage(driver);
	}
	
	@AfterMethod // It will run after each @Test method
	public void attachScreenshot(ITestResult result) {
		
		if(!result.isSuccess()) {
			ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
		}
//			else {	
//		ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
//		}
	}


	@Description("Close the browser")
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	
}
