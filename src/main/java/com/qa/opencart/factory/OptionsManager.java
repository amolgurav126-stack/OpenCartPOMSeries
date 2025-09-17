package com.qa.opencart.factory;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.qa.opencart.pages.LoginPage;

public class OptionsManager {
	
	private Properties prop;
	private ChromeOptions co;
	private EdgeOptions eo;
	private FirefoxOptions fo;
	private static final Logger log = LogManager.getLogger(OptionsManager.class);
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			log.info("Runninmg test case in headless mode");;
			co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			log.info("Runninmg test case in incognito mode");
			co.addArguments("--incognito");
		}
		return co;
	}
	
	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			log.info("Runninmg test case in headless mode");
			eo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			log.info("Runninmg test case in incognito mode");
			eo.addArguments("--inPrivate");
		}
		return eo;
	}

	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			log.info("Runninmg test case in headless mode");
			fo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))){
			log.info("Runninmg test case in incognito mode");
			fo.addArguments("--inPrivate");
		}
		return fo;
	}
}
