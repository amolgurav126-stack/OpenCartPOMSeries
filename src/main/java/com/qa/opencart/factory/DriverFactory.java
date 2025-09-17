package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {
	
	public WebDriver driver;	
	public Properties prop;
	public static String highlightEle;
	public OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	private static final Logger log = LogManager.getLogger(DriverFactory.class);
	
	/**
	 * This method is init driver basis on browser name.
	 * @param browserName
	 * @return it returns driver
	 */
	public WebDriver initDriver(Properties prop) {
		
		String browserName = prop.getProperty("browser");
		optionsManager = new OptionsManager(prop);
		
//		System.out.println("Browser name is : "+ browserName);
		log.info("Browser name is : "+ browserName);
		
		
		highlightEle = prop.getProperty("highlight");
		
		switch (browserName.trim().toLowerCase()) {
		case "chrome":
//			driver = new ChromeDriver();	
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "edge":
//			driver = new EdgeDriver();	
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;
		case "safari":
//			driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;
		case "firefox":
//			driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;

		default:
//			System.out.println(AppError.INVALID_BROWSER_MESG + ":" + browserName);
			log.error(AppError.INVALID_BROWSER_MESG + ":" + browserName);
			FrameworkException fe = new FrameworkException(AppError.INVALID_BROWSER_MESG + " : " + browserName);
			log.error("Exception occurred while initializing driver: ", fe);
			throw new FrameworkException("====INVALID BROWSER====");			
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();		
	}
	/**
	 * this method is used to get the local copy of the driver any time. This will prevent the race condition (Driver will never be blocked by specific thread). 
	 * @return It return the local copy of webDriver.
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	/*
	 * Properties is a class in java which is coming from java.util package
	 * FileInputStream makes the connection with config.properties file. In the
	 * constructor of FileInputStream we can pass the path of config.properties file
	 * ./ means current project directory. load method is used to load all the
	 * properties to prop object
	 */
	
	
	/**
	 * This method is init the prop with properties file
	 * @return 
	 * @return it return prop with all the properties.
	 */
	
	// mvn clean install -Denv="qa"
		// mvn clean install
		// mvn clean install -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/testng_regression.xml -Denv="dev"
		public Properties initProp() {
			prop = new Properties();
			FileInputStream ip = null;

			String envName = System.getProperty("env");
			log.info("Env name =======>" + envName);

			try {
				if (envName == null) {
					log.warn("no env.. is passed, hence running tcs on QA environment...by default..");
					ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
				}

				else {
					switch (envName.trim().toLowerCase()) {
					case "qa":
						ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
						break;
					case "stage":
						ip = new FileInputStream("./src/test/resources/config/config.stage.properties");
						break;
					case "uat":
						ip = new FileInputStream("./src/test/resources/config/config.uat.properties");
						break;
					case "dev":
						ip = new FileInputStream("./src/test/resources/config/config.dev.properties");
						break;
					case "prod":
						ip = new FileInputStream("./src/test/resources/config/config.properties");
						break;
					default:
						log.error("Env value is invalid...plz pass the right env value..");
						throw new FrameworkException("====INVALID ENVIRONMENT====");
					}
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			try {
				prop.load(ip);
			} catch (IOException e) {
				e.printStackTrace();
			}

			return prop;
		}
	
	
	
	/**
	 * TakeScreenshot
	 * @return 
	 */
	
	public static File getScreenshotFile() {
		File scrFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		return scrFile;
	}
	
	public static byte[] getScreenshotByte() {
		return ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES);		
	}

	public static String getScreenshotBase64() {
		return ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BASE64);		
	}
}
