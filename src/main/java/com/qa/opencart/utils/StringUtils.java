package com.qa.opencart.utils;

public class StringUtils {
	
	public static String getRandomEmail() {
		return "uiautomation" + System.currentTimeMillis()+ "@open.com"	;
	}
	
	// select * from user table where email like 'uiautomation%'

}
