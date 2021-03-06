package step_definitions;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.junit.AfterClass;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import page_objects.BaseClass;
import page_objects.DashboardPage;
import utils.APIException;
import utils.DatabaseConnection;
import utils.PropertiesFileReader;
import utils.SauceUtils;
import utils.TestrailResultUpload;

public class CucumberHooks extends BaseClass {

	private BaseClass base;

	public CucumberHooks(BaseClass base) {
		this.base = base;

	}
	public String jobName;
	public String sessionId;
	static PropertiesFileReader prreader = new PropertiesFileReader();
	TestrailResultUpload testresult = new TestrailResultUpload();
	DatabaseConnection con =  new DatabaseConnection();
	public static final String USERNAME = prreader.getPropertyvalues("SauceUserName");
	public static final String ACCESS_KEY = prreader.getPropertyvalues("SauceAccessKey");
	public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

	@Before
	public void setUp(Scenario scenario) throws MalformedURLException {

		// reads browser from Jenkins parameters with Sauce Ondemand jenkin plugin
		ChromeOptions options = new ChromeOptions();
		options.setCapability(CapabilityType.BROWSER_NAME, System.getenv("SELENIUM_BROWSER"));
		options.setCapability(CapabilityType.PLATFORM, System.getenv("SELENIUM_PLATFORM"));
		options.setCapability(CapabilityType.VERSION, System.getenv("SELENIUM_VERSION"));
	 

		// uncomment to read the browser,platform values from config file
/*		System.setProperty("webdriver.chrome.driver","C:\\Users\\msys\\Desktop\\Driver");
		options.setCapability(CapabilityType.BROWSER_NAME, prreader.getPropertyvalues("SELENIUM_BROWSER"));
		options.setCapability(CapabilityType.PLATFORM_NAME, prreader.getPropertyvalues("SELENIUM_PLATFORM"));
		options.setCapability(CapabilityType.VERSION, prreader.getPropertyvalues("SELENIUM_VERSION"));*/
		jobName = scenario.getName();
		options.setCapability("name", jobName);

		base.driver = new RemoteWebDriver(new URL(URL), options);

		// ******* comment the above line and uncomment the below line if you want to
		// use the selenium grid, replace with correct hub URL*********

		//base.driver = new RemoteWebDriver(new URL("http://192.168.0.103:4444/wd/hub"), options);

		base.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		base.driver.manage().window().maximize();

		sessionId = (((RemoteWebDriver) base.driver).getSessionId()).toString();

	}

	@After
	public void tearDown(Scenario scenario) throws JSONException, IOException, APIException, InterruptedException {
		DashboardPage dPage = new DashboardPage(base.driver);
		Collection<String> tagnames = scenario.getSourceTagNames();
		if (tagnames.contains("@NoLogout")) {
			base.driver.close();
			base.driver.quit();
		} else {
			dPage.logOut();
			base.driver.close();
			base.driver.quit();
		}

		SauceUtils.UpdateResults(USERNAME, ACCESS_KEY, !scenario.isFailed(),sessionId, jobName);
		testresult.uploadResult(scenario);
	}
	
}
