package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;

public class ProductInfoTest extends BaseTest {
	
	// BT (chrome+url)--> BC(login)---> @Test 
	
	@BeforeClass
	public void productInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	/* 
	 * Data provider in testng is always return 2D object array.
	 */
	@DataProvider
	public Object[][] getProducts() {
		return new Object[][] {
			{"macbook", "MacBook Pro"},
			{"samsung", "Samsung SyncMaster 941BW"},
			{"imac", "iMac"},
			{"canon", "Canon EOS 5D"}
		};
	}
	
	@Test(dataProvider = "getProducts")
	public void productHeaderTest(String searchKey, String productName) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		String actHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(actHeader, productName);
	}
	
	
	@DataProvider
	public Object[][] getProductsImage() {
		return new Object[][] {
			{"macbook", "MacBook Pro", 4},
			{"samsung", "Samsung SyncMaster 941BW",1},			
			{"canon", "Canon EOS 5D", 3}
		};
	}
	
	@Test(dataProvider = "getProductsImage")
	public void productImagesCountTest(String searchKey, String productName, int imagecount) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		int actImagesCount = productInfoPage.getProductImages();
		Assert.assertEquals(actImagesCount, imagecount);
	}
	
		
	@Test
	public void productInfoTest() {
		searchResultsPage = accPage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> productDataMap = productInfoPage.getProductData();
		
		SoftAssert softassert = new SoftAssert();
		
		softassert.assertEquals(productDataMap.get("Brand"), "Apple");
		softassert.assertEquals(productDataMap.get("Product Code"), "Product 18");
		softassert.assertEquals(productDataMap.get("Reward Points"), "800");
		softassert.assertEquals(productDataMap.get("Availability"), "Out Of Stock");
		softassert.assertEquals(productDataMap.get("ProductName"), "MacBook Pro");
		softassert.assertEquals(productDataMap.get("product price"), "$2,000.00");
		softassert.assertEquals(productDataMap.get("exTaxprice"), "$2,000.00");

		softassert.assertAll(); // It will tell out of n number assertion how many assertion got failed.
		
	}
	
	// AAA pattern : Arrange Act Assert
	// We can have multiple soft assertion in single test case 
	// but we can have only one hard assertion in a test case
	
	//Difference between hard assert and soft assert:
	
    //Hard assertion means test will be terminated at the moment when hard assertion will be failed.
	// hard assert we will will use for one single assertion.
	//In the single test we really want to write single assertion we will go with hard assertion.
	//But if i have functionality where i need to check multiple things for that test case the i will prefer soft assertion. In single test case i have multiple check points or multiple validations
	// for the same functionality i will go with soft assertion.
	

}
