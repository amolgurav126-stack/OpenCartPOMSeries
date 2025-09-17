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

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private static final Logger log = LogManager.getLogger(AccountsPage.class);

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private final By headers = By.cssSelector("div#content h2");
	private final By logoutLink = By.linkText("Logout");
	private final By search = By.xpath("//input[@placeholder = 'Search']");
	private final By searchIcon = By.cssSelector("div#search button");

	public List<String> getAccountsPageHeader() {
		List<WebElement> headerList = eleUtil.waitForElementsPresence(headers, AppConstants.DEFAULT_SHORT_WAIT);
//		System.out.println("Total number of header: " + headerList.size());
		log.info("Total number of header: " + headerList.size());

		List<String> headerValList = new ArrayList<String>();
		for (WebElement e : headerList) {
			String text = e.getText();
//			System.out.println(text);
			log.info(text);
			headerValList.add(text);
		}
		return headerValList;

	}

	public boolean isLogoutLinkExist() {
		return eleUtil.waitForElementVisible(logoutLink, AppConstants.DEFAULT_SHORT_WAIT).isDisplayed();
		
	}

	public SearchResultsPage doSearch(String searchKey) {
//		System.out.println("search key-->"+ searchKey);
		log.info("search key-->"+ searchKey);
		WebElement searchEle = eleUtil.waitForElementVisible(search, AppConstants.DEFAULT_MEDIUM_WAIT);
		searchEle.clear();
		searchEle.sendKeys(searchKey);
		eleUtil.doClick(searchIcon);
		return new SearchResultsPage(driver);
	}

}
