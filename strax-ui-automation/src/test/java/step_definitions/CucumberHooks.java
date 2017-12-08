package step_definitions;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
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

public class CucumberHooks extends BaseClass{

	private BaseClass base;
	public CucumberHooks(BaseClass base) {
		this.base = base;

	}
	static DesiredCapabilities capabilities;
	public String jobName;
	public String sessionId;
	static PropertiesFileReader prreader = new PropertiesFileReader();
	TestrailResultUpload testresult = new TestrailResultUpload();
	public static final String USERNAME = prreader.getPropertyvalues("SauceUserName");
	public static final String ACCESS_KEY = prreader.getPropertyvalues("SauceAccessKey");
	public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

	@Before
	public void setUp(Scenario scenario) throws MalformedURLException
	{

		//reads browser from Jenkins parameters with Sauce Ondemand jenkin plugin

		capabilities = DesiredCapabilities.chrome();
		capabilities.setBrowserName(System.getenv("SELENIUM_BROWSER"));
		capabilities.setCapability(CapabilityType.PLATFORM,System.getenv("SELENIUM_PLATFORM"));
		capabilities.setVersion(System.getenv("SELENIUM_VERSION"));



		// uncomment to read the browser,platform values from config file
		
/*		capabilities = DesiredCapabilities.chrome();
		capabilities.setBrowserName(prreader.getPropertyvalues("SELENIUM_BROWSER"));
		
		capabilities.setCapability(CapabilityType.PLATFORM, prreader.getPropertyvalues("SELENIUM_PLATFORM"));
		capabilities.setVersion(prreader.getPropertyvalues("SELENIUM_VERSION"));*/
		
		jobName = scenario.getName();
		capabilities.setCapability("name", jobName);
		base.driver = new RemoteWebDriver(new URL(URL), capabilities);

		//******* comment the above line and uncomment the below line if you want to use the selenium grid, replace with correct hub URL*********
		//base.driver = new RemoteWebDriver(new URL("http://192.168.101.29:4444/wd/hub"), capabilities);
		
		base.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		base.driver.manage().window().maximize();
		
		sessionId = (((RemoteWebDriver) base.driver).getSessionId()).toString();


	}
	@After
	public void tearDown(Scenario scenario) throws JSONException, IOException, APIException, InterruptedException
	{
		CommonClass cClass = new CommonClass(base.driver);

		Collection<String> tagnames=scenario.getSourceTagNames();
		if(tagnames.contains("@NoLogout"))
		{
			base.driver.close();
			base.driver.quit();
		}
		else {
			cClass.logOut();
			base.driver.close();
			base.driver.quit();
		}

		SauceUtils.UpdateResults(USERNAME, ACCESS_KEY, !scenario.isFailed(), sessionId, jobName);
		testresult.uploadResult(scenario);
	}


}
