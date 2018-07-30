package test_runner;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackMessage;
import page_objects.BaseClass;
import step_definitions.CucumberHooks;
import utils.PropertiesFileReader;
import utils.UtilityMethods;

@RunWith(Cucumber.class)
@CucumberOptions(format = { "progress", "html:target/cucumber" }, plugin = {
		"ru.yandex.qatools.allure.cucumberjvm.AllureReporter"}, features = {
				"src/test/resources/features/" }, glue = { "step_definitions" }, tags = { "~@ignore" })
public class runCukesTest extends UtilityMethods {
	public runCukesTest(BaseClass base) {
		super(base);
	}

	static PropertiesFileReader prreader = new PropertiesFileReader();
	
	int percentage;

	@AfterClass
	public static void tearDown() {
	

	}

	@BeforeClass
	public static void setUp() {
	}

	

}
