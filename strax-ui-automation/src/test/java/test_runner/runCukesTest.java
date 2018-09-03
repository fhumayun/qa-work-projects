package test_runner;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import page_objects.BaseClass;
import utils.DatabaseConnection;
import utils.PropertiesFileReader;
import utils.UtilityMethods;

@RunWith(Cucumber.class)
@CucumberOptions(format = { "progress", "html:target/cucumber" }, plugin = {
		"ru.yandex.qatools.allure.cucumberjvm.AllureReporter"}, features = {
				"src/test/resources/features/" }, glue = { "step_definitions" }, tags = { "~@ignore" })
public class runCukesTest extends UtilityMethods {
	static DatabaseConnection con =  new DatabaseConnection();
	public runCukesTest(BaseClass base) {
		super(base);
	}

	static PropertiesFileReader prreader = new PropertiesFileReader();
	/*
	 * Trial code for suite level stale test data cleanup
	 */
	@AfterClass
	public static void suiteTearDown() {
		for(int i=1;i<4;i++)
		{
		con.deleteEvent("AutomationTestIncident"+i);
		}
		for(int i=1;i<4;i++)
		{
		con.deleteUser("z-automationtestuser"+i+"@ee.io");
		}
		for(int i=1;i<4;i++)
		{
		con.deleteEventPlan("AutomationTestEventPlan"+i);
		}
		con.deleteEventPlan("TestEventPlanFromPrePlan11");
		con.deleteEvent("AutomationTestEventFromEventPlan1");
		con.deleteUAS("QA-Automation-UAS");
		con.deleteVideoFeed("QA-Automation-Feed");
		System.out.println("Deleted test data");
	}


	@BeforeClass
	public static void setUp() {
		for(int i=1;i<4;i++)
		{
		con.deleteEvent("AutomationTestIncident"+i);
		}
		for(int i=1;i<4;i++)
		{
		con.deleteUser("z-automationtestuser"+i+"@ee.io");
		}
		for(int i=1;i<4;i++)
		{
		con.deleteEventPlan("AutomationTestEventPlan"+i);
		}
		con.deleteEventPlan("TestEventPlanFromPrePlan11");
		con.deleteEvent("AutomationTestEventFromEventPlan1");
		con.deleteUAS("QA-Automation-UAS");
		con.deleteVideoFeed("QA-Automation-Feed");
		System.out.println("Deleted test data");
	}

	

}
