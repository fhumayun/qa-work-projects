package step_definitions;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import page_objects.BaseClass;
import page_objects.CommonClass;
import utils.APIException;
import utils.PropertiesFileReader;
import utils.SauceUtils;
import utils.TestrailResultUpload;

public class CucumberHooks extends BaseClass {

	private BaseClass base;

	public CucumberHooks(BaseClass base) {
		this.base = base;

	}

	static DesiredCapabilities capabilities;
	public String jobName;
	public String sessionId;
	static PropertiesFileReader prreader = new PropertiesFileReader();
	public static int scenarioTotalCount = 0, srmCount = 0, sacCount = 0, playbackCount = 0, srmPassCount = 0,
			sacPassCount = 0, pbPassCount = 0;
	public static float srmTestCoverage = 0, sacTestCoverage = 0, playbackTestCoverage = 0;
	TestrailResultUpload testresult = new TestrailResultUpload();
	public static final String USERNAME = prreader.getPropertyvalues("SauceUserName");
	public static final String ACCESS_KEY = prreader.getPropertyvalues("SauceAccessKey");
	public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

	@Before
	public void setUp(Scenario scenario) throws MalformedURLException {

		// reads browser from Jenkins parameters with Sauce Ondemand jenkin plugin
		  capabilities = DesiredCapabilities.chrome();
		  capabilities.setBrowserName(System.getenv("SELENIUM_BROWSER"));
		  capabilities.setCapability(CapabilityType.PLATFORM_NAME,System.getenv("SELENIUM_PLATFORM"));
		  capabilities.setVersion(System.getenv("SELENIUM_VERSION"));
		 

		// uncomment to read the browser,platform values from config file
/*		 System.setProperty("webdriver.chrome.driver","D:\\Driver\\chromedriver.exe");
		 capabilities.setBrowserName(prreader.getPropertyvalues("SELENIUM_BROWSER"));
		 capabilities.setCapability(CapabilityType.PLATFORM,prreader.getPropertyvalues("SELENIUM_PLATFORM"));
		 capabilities.setVersion(prreader.getPropertyvalues("SELENIUM_VERSION"));*/
		jobName = scenario.getName();
		capabilities.setCapability("name", jobName);
		base.driver = new RemoteWebDriver(new URL(URL), capabilities);

		// ******* comment the above line and uncomment the below line if you want to
		// use the selenium grid, replace with correct hub URL*********

		//base.driver = new RemoteWebDriver(new URL("http://192.168.101.169:4444/wd/hub"), new ChromeOptions());

		base.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		base.driver.manage().window().maximize();

		sessionId = (((RemoteWebDriver) base.driver).getSessionId()).toString();

	}

	@After
	public void tearDown(Scenario scenario) throws JSONException, IOException, APIException, InterruptedException {
		scenarioTotalCount = scenarioTotalCount + 1;

		CommonClass cClass = new CommonClass(base.driver);
		////// Test coverage percentage code starts
		Collection<String> tags = scenario.getSourceTagNames();
		if (tags.contains("@SRM")) {

			srmCount = srmCount + 1;
			if (scenario.getStatus().equalsIgnoreCase("passed")) {
				srmPassCount = srmPassCount + 1;
			}
		} else if (tags.contains("@SAC")) {
			sacCount = sacCount + 1;
			if (scenario.getStatus().equalsIgnoreCase("passed")) {
				sacPassCount = sacPassCount + 1;
			}
		} else if (tags.contains("@PlayBack")) {
			playbackCount = playbackCount + 1;
			if (scenario.getStatus().equalsIgnoreCase("passed")) {
				pbPassCount = pbPassCount + 1;
			}
		}

		///// Test coverage percentage code ends
		Collection<String> tagnames = scenario.getSourceTagNames();
		if (tagnames.contains("@NoLogout")) {
			base.driver.close();
			base.driver.quit();
		} else {
			cClass.logOut();
			base.driver.close();
			base.driver.quit();
		}

		 SauceUtils.UpdateResults(USERNAME, ACCESS_KEY, !scenario.isFailed(),sessionId, jobName);
		 testresult.uploadResult(scenario);
	}

}
