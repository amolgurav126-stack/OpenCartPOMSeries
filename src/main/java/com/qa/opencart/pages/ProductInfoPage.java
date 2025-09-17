package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	Map<String, String> productMap;
	private static final Logger log = LogManager.getLogger(ProductInfoPage.class);


	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private final By header = By.tagName("h1");
	private final By productImages = By.cssSelector("ul.thumbnails img");
	private final By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private final By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");


	public String getProductHeader() {
		String headerVal = eleUtil.waitForElementVisible(header, AppConstants.DEFAULT_MEDIUM_WAIT).getText();
//		System.out.println("Product header value is --> "+ headerVal);
		log.info("Product header value is --> "+ headerVal);
		return headerVal;
	}
	
	public int getProductImages() {
		int imagescount = 
				  eleUtil.waitForElementsVisible(productImages, AppConstants.DEFAULT_MEDIUM_WAIT).size();
//		System.out.println("Total number of images : "+ imagescount);
		log.info("Total number of images : "+ imagescount);
		return imagescount;
	}
	
	
	public Map<String, String> getProductData() {
		//productMap = new HashMap<String, String>();                       // It will store data in random order
		
		//productMap = new LinkedHashMap<String, String>();                 //It will maintain the insertion order.
		
		productMap = new TreeMap<String, String>();                         // It will store data in sorted order.
		
		productMap.put("ProductName", getProductHeader());
		productMap.put("productImages", String.valueOf(getProductImages()));
		
		getProductMetaData();
		getProductpriceData();
		
//		System.out.println("=======productData======\n"+ productMap);
		log.info("=======productData======\n"+ productMap);
		return productMap;		
	}
	
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: Out Of Stock
	
	private void getProductMetaData() {
		List<WebElement> metaList = eleUtil.waitForElementsVisible(productMetaData, AppConstants.DEFAULT_MEDIUM_WAIT);
//		System.out.println("total meta data: " + metaList.size());
		log.info("total meta data: " + metaList.size());

		for (WebElement e : metaList) {
			String metaData = e.getText();
			String meta[] = metaData.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productMap.put(metaKey, metaValue);
		}
	}

//	$2,000.00
//	Ex Tax: $2,000.00
//	[0]       [1]
	private void getProductpriceData() {
		List<WebElement> priceList = eleUtil.waitForElementsVisible(productPriceData, AppConstants.DEFAULT_MEDIUM_WAIT);
//		System.out.println("total price data: " + priceList.size());
		log.info("total price data: " + priceList.size());

		String priceValue = priceList.get(0).getText();
		String exTaxValue = priceList.get(1).getText().split(":")[1].trim();

		productMap.put("product price", priceValue);
		productMap.put("exTaxprice", exTaxValue);		
	}
	
}
