package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utills.ExcelReader;
import utills.TestContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class Hooks {
	private static WebDriver driver;
	private static final Logger logger = LogManager.getLogger(Hooks.class);
	private static Iterator<String[]> dataIterator;

	@Before
	public void setup() throws InterruptedException {
		logger.info("Launching browser...");
		
		driver = new ChromeDriver();
		

		if (dataIterator == null) {
            List<String[]> loginData = ExcelReader.getLoginData("C:\\Users\\NM116396\\Downloads\\demoshopnew 1\\DemoWebShop\\TestData\\LoginTest 1.xlsx");
            dataIterator = loginData.iterator();
        }
	 
	        if (dataIterator.hasNext()) {
	            String[] credentials = dataIterator.next();
	            TestContext.setUsername(credentials[0]);
	            TestContext.setPassword(credentials[1]);
	        } else {
	            throw new RuntimeException("No more test data available.");
	        }
	 

	}

	@After
	public void teardown(Scenario scenario) {
		if (scenario.isFailed()) {
			logger.error("Scenario failed: " + scenario.getName());

			try {

				final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", "Failed Screenshot");

			} catch (Exception e) {
				logger.error("Failed to save screenshot: ", e);
			}
		} else {
			logger.info("Scenario passed: " + scenario.getName());
		}

		if (driver != null) {
			driver.quit();
			logger.info("Browser closed.");
		}
		TestContext.clear();
	}

	public static WebDriver getDriver() {
		return driver;
	}
}
