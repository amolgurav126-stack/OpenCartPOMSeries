package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.CsvUtil;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtils;

public class RegisterPageTest extends BaseTest {
	
	@BeforeClass
	public void goToRegisterPage() {
		registerPage = loginPage.navigateToRegisterPage();
	}
	
//	@DataProvider
//	public Object[][] getRegData() {
//		return new Object[][] {
//			{"papa", "gurav", "8886665454", "Amol@1994", "yes"},
//			{"pallavi", "gurav", "7058562962", "pallavi@1994", "no"},
//			{"Dipali", "Amol", "9890635646", "Dipali@1994", "yes"}
//		};
//	}
	
	@DataProvider
	public Object[][] getRegSheetData() {
		return ExcelUtil.getTestData("register");
	}
	
	@DataProvider
	public Object[][] getRegCSVData() {
		return CsvUtil.csvData("register");
	}
	
	@Test(dataProvider = "getRegCSVData")
	public void registerTest(String firstName, String lastName, String telephone, String password, String subscribe) {
	Assert.assertTrue
	(registerPage.userRegister(firstName, lastName, StringUtils.getRandomEmail(), telephone, password, subscribe));		
	}
	
	

}
