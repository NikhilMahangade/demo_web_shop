package utills;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
 
public class ExtentReportManager {
    private static ExtentReports extent;
    private static ExtentTest test;
 
    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter("target/ManualExtentReport.html");
            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }
 
    public static ExtentTest createTest(String testName) {
        test = getInstance().createTest(testName);
        return test;
    }
 
    public static ExtentTest getTest() {
        return test;
    }
}
 