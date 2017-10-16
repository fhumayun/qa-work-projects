package test_runner;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentXReporter;
import com.cucumber.listener.ExtentProperties;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import utils.PropertiesFileReader;


@RunWith(Cucumber.class) 
@CucumberOptions(
format = {"progress", "html:target/cucumber"},
plugin={"com.cucumber.listener.ExtentCucumberFormatter:output/report.html","rerun:target/rerun.txt"},
features = {"src/test/resources/features/"},
glue = {"step_definitions"},
tags = {"~@ignore"})
public class runCukesTest {
	static ExtentReports extent;
	static ExtentXReporter extentxReporter;
	static PropertiesFileReader prreader = new PropertiesFileReader();
	@AfterClass
	public static void tearDown()
	{
		//****** Extent Report setup configuration *****
        Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
        Reporter.setSystemInfo("user", System.getProperty("user.name"));
        Reporter.setSystemInfo("Executed On Operating System", prreader.getPropertyvalues("Platform"));
        Reporter.setTestRunnerOutput("Sample test runner output message");    
        extent = Reporter.getExtentReport();
        extent.setSystemInfo("Executed On Environment", prreader.getPropertyvalues("STRAXUrl"));
        extent.setSystemInfo("Browser Used", prreader.getPropertyvalues("BrowserName"));
        extent.setSystemInfo("Browser Version", prreader.getPropertyvalues("Version"));
 
    }
	@BeforeClass
	public static void setUp()
	{
        //****** ExtentX Server setup configuration *****
       ExtentProperties extentProperties = ExtentProperties.INSTANCE;
        extentProperties.setReportPath("output/report.html");
        //extentProperties.setExtentXServerUrl("http://localhost:1337");
       // extentProperties.setProjectName("STRAX QAT");
	}
		
	
}

