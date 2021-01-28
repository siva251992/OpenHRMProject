package commonFunctions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class CommonFunctions {

	// To read from property file
	public static Properties properties = null;
	public static WebDriver driver = null;
	
	//Creating object for Logger using factory design pattern
	Logger logger = Logger.getLogger(CommonFunctions.class);

	public Properties loadPropertyFile() throws IOException {
		// load property file & return

		FileInputStream file = new FileInputStream("config.properties");
		properties = new Properties();
		properties.load(file);
		return properties;

	}

	@BeforeSuite
	public WebDriver launchBrowser() throws IOException {
		// Call the loadpropertyFile method
		
		PropertyConfigurator.configure("log4j.properties");
		logger.info("Orange HRM Execution begins");
		logger.info("Loading the property file");
		loadPropertyFile();

		// Set the browser,url, driverlocation from config.properties file
		String browser = properties.getProperty("browser");
		String url = properties.getProperty("url");
		String driverLocation = properties.getProperty("DriverLocation");

		if (browser.equalsIgnoreCase("chrome")) {
			logger.info("launching chrome browser");
			System.setProperty("webdriver.chrome.driver", driverLocation);
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("Firefox")) {
			logger.info("launching Firefox browser");
			System.setProperty("webdriver.gecko.driver", driverLocation);
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		logger.info("Navigating to application");
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		return driver;
	}

	@AfterSuite
	public void tearDown() {
		// TODO Auto-generated method stub
		logger.info("Closing the browser");
		driver.quit();
	}

}
