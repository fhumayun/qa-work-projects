package test_runner;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentXReporter;
import com.cucumber.listener.ExtentCucumberFormatter;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class) 
@CucumberOptions(
format = {"pretty", "html:target/cucumber"},
plugin={"com.cucumber.listener.ExtentCucumberFormatter:output/report.html"},
features = {"src/test/resources/features/STRAXLogin.feature"},
glue = {"step_definitions"})
public class runCukesTest {
	static ExtentReports extent;
	static ExtentXReporter extentxReporter;
	@AfterClass
	public static void setUp()
	{
        Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
        Reporter.setSystemInfo("user", System.getProperty("user.name"));
        Reporter.setSystemInfo("os", "Windows 8");
        Reporter.setTestRunnerOutput("Sample test runner output message");
        
        extent = Reporter.getExtentReport();
        extent.setSystemInfo("Environment", "Development/QA");
        
        //****** ExtentX setup configuration for future use*****
        //extentxReporter = new ExtentXReporter("localhost", 27017);
        //extent.attachReporter(extentxReporter);
        
    }
		
	
}

//@Cucumber.Options (features = "src/test/java/Cucumber/CucumberJava.feature")