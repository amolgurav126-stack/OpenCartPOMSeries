package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.AccountsPage;

public class AccountsPageTest extends BaseTest {
	/*
	 * Sequence --> Base Test--> Before test --> BeforeClass --> @Test -->after test
	 */

	@BeforeClass
	public void accPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void isLogoutLinkExist() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Test
	public void accPageHeaderTest() {
		List<String> actHeaders = accPage.getAccountsPageHeader();
		Assert.assertEquals(actHeaders.size(), 4);
		Assert.assertEquals(actHeaders, AppConstants.expectedAccPageHeaderList);
		
	}

}
