package com.qa.opencart.tests;

import java.util.List;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class CommonsPageTest extends BaseTest{
	
	@Test
	public void checkCommonElementsOnLoginPageTest() {
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(commonsPage.isLogoExist());
		softAssert.assertTrue(commonsPage.isSearchfieldExist());
		
		List<String> ActualfooterLinksExist = commonsPage.isFooterLinksExist();
		softAssert.assertEquals(ActualfooterLinksExist.size(), AppConstants.FOOTER_LINKS_COUNT);
		softAssert.assertAll();
	}
	
	@Test(priority = Integer.MAX_VALUE)
	public void checkCommonElementsOnAccountsPageTest() {
		
	    loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));	
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(commonsPage.isLogoExist());
		softAssert.assertTrue(commonsPage.isSearchfieldExist());		
		List<String> ActualfooterLinksExist = commonsPage.isFooterLinksExist();
		softAssert.assertEquals(ActualfooterLinksExist.size(), AppConstants.FOOTER_LINKS_COUNT);
		softAssert.assertAll();
	}

}
