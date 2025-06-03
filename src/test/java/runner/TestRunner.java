package runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features/Registration.feature",
glue = {"stepdefs"},
plugin = {"pretty","html:target/cucumber-reports.html",
		"json:target/cucumber-reports.json"},
		 
monochrome = true,
publish = true
//tags="@register"	
)
public class TestRunner extends AbstractTestNGCucumberTests {
	
	@Override
	@DataProvider()
	public Object[][] scenarios(){
		return super.scenarios();
	}
}
