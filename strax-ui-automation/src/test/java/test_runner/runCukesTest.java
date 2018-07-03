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

@RunWith(Cucumber.class)
@CucumberOptions(format = { "progress", "html:target/cucumber" }, plugin = {
		"ru.yandex.qatools.allure.cucumberjvm.AllureReporter"}, features = {
				"src/test/resources/features/" }, glue = { "step_definitions" }, tags = { "~@ignore" })
public class runCukesTest extends CucumberHooks {
	public runCukesTest(BaseClass base) {
		super(base);
	}

	static PropertiesFileReader prreader = new PropertiesFileReader();
	int percentage;

	@AfterClass
	public static void tearDown() {
		slackTestCoverageMatrix();

	}

	@BeforeClass
	public static void setUp() {
	}

	/// ******* This methos is due for refactoring ******//////////
	public static void slackTestCoverageMatrix() {
		srmTestCoverage = (float) srmCount / scenarioTotalCount * 100;
		int srmTestCoverage1 = (int) srmTestCoverage;
		sacTestCoverage = (float) sacCount / scenarioTotalCount * 100;
		int sacTestCoverage1 = (int) sacTestCoverage;
		playbackTestCoverage = (float) playbackCount / scenarioTotalCount * 100;
		float totalTestCoverage = (float) scenarioTotalCount / 100 * 100;
		int totalTestCoverage1 = (int) totalTestCoverage;

		int playbackTestCoverage1 = (int) playbackTestCoverage;
		float srmPassPercentage = (float) srmPassCount / srmCount * 100;
		float sacPassPercentage = (float) sacPassCount / sacCount * 100;
		float pbPassPercentage = (float) pbPassCount / playbackCount * 100;
		int srmPassPercentage1 = (int) srmPassPercentage;
		int sacPassPercentage1 = (int) sacPassPercentage;
		int pbPassPercentage1 = (int) pbPassPercentage;
		SlackApi api = new SlackApi("https://hooks.slack.com/services/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");
		api.call(new SlackMessage("#strax-nightlies", null, "*`STRAX Module Wise Test Coverage`*\n" + "```SRM Test Coverage - "
				+ srmTestCoverage1 + "%" + "(" + srmCount + " Scenarios)  Health Grade " + getGrade(srmPassPercentage1)
				+ " (" + srmPassPercentage1 + "% Passing) ```\n" + "```SAC Test Coverage - " + sacTestCoverage1 + "%"
				+ "(" + sacCount + " Scenarios)  Health Grade " + getGrade(sacPassPercentage1) + " ("
				+ sacPassPercentage1 + "% Passing) ```\n" + "```PlayBack Test Coverage - " + playbackTestCoverage1 + "%"
				+ "(" + playbackCount + " Scenarios)  Health Grade " + getGrade(pbPassPercentage1) + " ("
				+ pbPassPercentage1 + "% Passing)```\n" + "```Total Automation Test Coverage - " + totalTestCoverage1
				+ "(" + scenarioTotalCount + " Scenarios)```"));

	}

	public static String getGrade(int percentage) {
		if (percentage >= 90) {
			return "A";
		} else if (percentage >= 80 && percentage < 90) {
			return "B";
		} else if (percentage >= 70 && percentage < 80) {
			return "C";
		} else if (percentage >= 60 && percentage < 70) {
			return "D";
		}	else 
			return "F";
	}

}
