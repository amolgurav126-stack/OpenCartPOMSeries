package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class CommonsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private static final Logger log = LogManager.getLogger(CommonsPage.class);
	
	public CommonsPage(WebDriver driver) {
		this.driver= driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private final By logo = By.cssSelector("img.img-responsive");
	private final By searchField = By.xpath("//input[@placeholder='Search']"); 
	private final By footer = By.cssSelector("footer a");
	
	@Step("Checking logo is exist..")
	public boolean isLogoExist() {
		return eleUtil.isElementDisplayed(logo);
	}
	
	@Step("Checking searchfield is exist..")
	public boolean isSearchfieldExist() {
		return eleUtil.isElementDisplayed(searchField);
	}
	
	@Step("Checking footers...")
	public List<String> isFooterLinksExist() {
		List<WebElement> footerList = eleUtil.waitForElementsVisible(footer, AppConstants.DEFAULT_MEDIUM_WAIT);
		log.info("size of footer list is : "+ footerList.size());
		List<String> footerValueList = new ArrayList<String>();
		for(WebElement e: footerList) {
			String text = e.getText();
			footerValueList.add(text);
		}
		return footerValueList;
	}
	
	
}
