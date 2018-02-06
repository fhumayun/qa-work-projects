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
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackMessage;
import page_objects.BaseClass;
import step_definitions.CucumberHooks;
import utils.PropertiesFileReader;


@RunWith(Cucumber.class) 
@CucumberOptions(
format = {"progress", "html:target/cucumber"},
plugin={"com.cucumber.listener.ExtentCucumberFormatter:output/report.html","rerun:rerun.txt"},
features = {"src/test/resources/features/"},
glue = {"step_definitions"},
tags = {"~@ignore"})
public class runCukesTest extends CucumberHooks{
	public runCukesTest(BaseClass base) {
		super(base);
		// TODO Auto-generated constructor stub
	}
	static ExtentReports extent;
	static ExtentXReporter extentxReporter;
	static PropertiesFileReader prreader = new PropertiesFileReader();
	int percentage;
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
        slackTestCoverageMatrix();
        
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
	
	public static void slackTestCoverageMatrix()
	{
		srmTestCoverage =  (float)srmCount/scenarioTotalCount*100;
		int srmTestCoverage1 = (int) srmTestCoverage;
		sacTestCoverage =  (float)sacCount/scenarioTotalCount*100;
		int sacTestCoverage1 = (int) sacTestCoverage;
		playbackTestCoverage = (float)playbackCount/scenarioTotalCount*100;
		int totalTestCoverage = scenarioTotalCount/100*100;
		int playbackTestCoverage1 = (int) playbackTestCoverage;
		int srmPassPercentage = srmPassCount/srmCount*100;
		int sacPassPercentage = sacPassCount/sacCount*100;
		int pbPassPercentage = pbPassCount/playbackCount*100;
        SlackApi api = new SlackApi("https://hooks.slack.com/services/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");
        api.call(new SlackMessage("#strax-nightlies", null, 	"*`STRAX Module Wise Test Coverage`*\n"+
        		"```SRM Test Coverage - "+srmTestCoverage1+" %"+"("+srmCount+ " Scenario)  Health Grade "+getGrade(srmPassPercentage)+" ("+srmPassPercentage+"% Passing) ```\n" + 
        	    "```SAC Test Coverage - "+sacTestCoverage1+" %"+"("+sacCount+ " Scenario)  Health Grade "+getGrade(sacPassPercentage)+" ("+sacPassPercentage+"% Passing) ```\n" +
        		"```PlayBack Test Coverage - "+playbackTestCoverage1+" %"+"("+playbackCount+ " Scenario)  Health Grade "+getGrade(pbPassPercentage)+" ("+pbPassPercentage+"% Passing)```\n"+
        		"```Total Automation Test Coverage - "+totalTestCoverage+"("+scenarioTotalCount+ " Scenario)```" ));
            	
    	

	}
	
	public static String getGrade(int percentage)
	{
		if (percentage>=90)
		{
			return "A";
		}
		else if(percentage>=80 && percentage<90)
		{
			return "B";
		}
		else 
			return "C";
	}
	
}

