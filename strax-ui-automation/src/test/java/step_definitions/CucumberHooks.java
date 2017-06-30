package step_definitions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import page_objects.BaseClass;
import utils.APIException;
import utils.PropertiesFileReader;
import utils.SouceUtils;
import utils.TestrailResultUpload;

public class CucumberHooks extends BaseClass{
	
	private BaseClass base;
	public CucumberHooks(BaseClass base) {
		this.base = base;
		
	}
	//LoginPage loginpage;
	//DashboardPage dashboardpage;
	//UsersPage uPage;
	//RemoteWebDriver driver;
	DesiredCapabilities capabilities;
	public String jobName;
	public String sessionId;
	static PropertiesFileReader prreader = new PropertiesFileReader();
	TestrailResultUpload testresult = new TestrailResultUpload();
	//CommonClass commonClass = new CommonClass(driver);
	//reads Sauce username & access key from property file
	public static final String USERNAME = prreader.getPropertyvalues("SauceUserName");
	public static final String ACCESS_KEY = prreader.getPropertyvalues("SauceAccessKey");
	public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
	
	@Before
	public void setUp(Scenario scenario) throws MalformedURLException
	{
		capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability("browserName", prreader.getPropertyvalues("BrowserName"));
		capabilities.setCapability("platform", prreader.getPropertyvalues("Platform"));
		capabilities.setCapability("version",prreader.getPropertyvalues("Version"));
		jobName = scenario.getName();
		capabilities.setCapability("name", jobName);
		base.driver = new RemoteWebDriver(new URL(URL), capabilities);
		
		//******* comment the above line and uncomment the below line if you want to use the selenium grid, replace with correct hub URL*********
		//base.driver = new RemoteWebDriver(new URL("http://192.168.0.104:4444/wd/hub"), capabilities);
		base.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		base.driver.manage().window().maximize();
		sessionId = (((RemoteWebDriver) base.driver).getSessionId()).toString();
		
		

	}
	@After
	public void tearDown(Scenario scenario) throws JSONException, IOException, APIException
	{
		base.driver.quit();
		SouceUtils.UpdateResults(USERNAME, ACCESS_KEY, !scenario.isFailed(), sessionId, jobName);
		//System.out.println("SauceOnDemandSessionID=" + sessionId + "job-name=" + jobName);
		testresult.uploadResult(scenario);
		
	}
}
