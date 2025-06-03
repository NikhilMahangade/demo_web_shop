package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utills.ExcelReader;
import utills.ExtentReportManager;
import utills.TestContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.Status;

import org.openqa.selenium.edge.EdgeDriver;

import java.io.InputStream;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class Hooks {
    private static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(Hooks.class);
    private static Iterator<String[]> dataIterator;
    private static Properties config;

    static {
        config = new Properties();
        try (InputStream input = Hooks.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.err.println("config.properties not found in classpath!");
                throw new RuntimeException("config.properties not found in classpath!");
            }
            config.load(input);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    @Before
    public void setup(Scenario scenario) throws InterruptedException {
        logger.info("Launching browser...");
        Thread.sleep(2000);
        ExtentReportManager.createTest(scenario.getName());

        String browser = config.getProperty("browser", "chrome").toLowerCase();
        switch (browser) {
            case "chrome":
                String chromeDriverPath = config.getProperty("chrome.driver.path", "");
                if (!chromeDriverPath.isEmpty()) {
                    System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                }
                driver = new ChromeDriver();
                break;
            case "firefox":
                String firefoxDriverPath = config.getProperty("firefox.driver.path", "");
                if (!firefoxDriverPath.isEmpty()) {
                    System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
                }
                driver = new FirefoxDriver();
                break;
            case "edge":
                String edgeDriverPath = config.getProperty("edge.driver.path", "");
                if (!edgeDriverPath.isEmpty()) {
                    System.setProperty("webdriver.edge.driver", edgeDriverPath);
                }
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Unsupported browser: " + browser + ". Please check config.properties.");
        }

        driver.manage().timeouts().implicitlyWait(
            Duration.ofSeconds(Integer.parseInt(config.getProperty("implicit.wait", "10")))
        );

        if (dataIterator == null) {
            List<String[]> loginData = ExcelReader.getLoginData(config.getProperty("excel.file.path"));
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
                ExtentReportManager.getTest().log(Status.FAIL, "Scenario failed");
                
            } catch (Exception e) {
                logger.error("Failed to save screenshot: ", e);
            }
        } else {
            logger.info("Scenario passed: " + scenario.getName());
            ExtentReportManager.getTest().log(Status.PASS, "Scenario passed");
        }

        if (driver != null) {
            driver.quit();
            logger.info("Browser closed.");
        }
        ExtentReportManager.getInstance().flush();
        TestContext.clear();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}