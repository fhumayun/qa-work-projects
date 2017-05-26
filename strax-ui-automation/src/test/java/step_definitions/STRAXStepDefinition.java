package step_definitions;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.PropertiesFileReader;
import utils.SouceUtils;
import utils.TestrailResultUpload;
import page_objects.DashboardPage;
import page_objects.LoginPage;
import cucumber.api.Scenario;
import cucumber.api.java.*;
import cucumber.api.java.en.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;


public class STRAXStepDefinition
{

	// WebDriver driver=null;
	LoginPage loginpage;
	DashboardPage dashboardpage;
	RemoteWebDriver driver;
	DesiredCapabilities capabilities;
	public String jobName;
	public String sessionId;
	static PropertiesFileReader prreader = new PropertiesFileReader();
	//TestrailResultUpload testresult = new TestrailResultUpload();
	
	//reads Sauce username & access key from property file
	public static final String USERNAME = prreader.getPropertyvalues("SauceUserName");
	public static final String ACCESS_KEY = prreader.getPropertyvalues("SauceAccessKey");
	public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

	/**
	 *
	 * setUp method sets desired capabilities for Saucelab execution
	 * * @param scenario
	 * @throws MalformedURLException
	 */
	@Before
	public void setUp(Scenario scenario) throws MalformedURLException
	{
		capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability("browserName", prreader.getPropertyvalues("BrowserName"));
		capabilities.setCapability("platform", prreader.getPropertyvalues("Platform"));
		capabilities.setCapability("version",prreader.getPropertyvalues("Version"));
		jobName = scenario.getName();
		capabilities.setCapability("name", jobName);
		driver = new RemoteWebDriver(new URL(URL), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//******* comment the above line and uncomment the below line if you want to use the selenium grid, replace with correct hub URL*********
		//driver = new RemoteWebDriver(new URL("http://192.168.101.32:4444/wd/hub"), capabilities);
		sessionId = (((RemoteWebDriver) driver).getSessionId()).toString();
		

	}

	@Given("^The STRAX Application login page is open$")
	public void openLoginPage()
	{

		loginpage = new LoginPage(driver);
		loginpage.openLoginPage();

	}
	@Then("^The title bar should contain STRAX$")
	public void The_title_bar_should_contain_STRAX()
	{
		Assert.assertEquals("", "STRAX™", loginpage.getLoginPageTitle());
		
	}
	@Then("The footer copyright should contain \"([^\"]*)\"$")
	public void The_footer_copyright_should_contain_String(String capyright)
	{
		Assert.assertEquals("", capyright, loginpage.getFooterCopyrightInfo());
	}

	@When("^User Enters Valid \"([^\"]*)\" and \"([^\"]*)\"$")
	public void User_Enters_Valid_username_and_password(String username, String password)
	{
		loginpage = new LoginPage(driver);
		loginpage.loginAsUser(username, password);
	}

	@Then("^The Login should be successful$")
	public void The_Login_should_be_successful()
	{
		dashboardpage = new DashboardPage(driver);
		Assert.assertTrue(dashboardpage.verifyDashboardNavigation());
	}

	@When("^User Enters invalid \"([^\"]*)\" and \"([^\"]*)\"$")
	public void User_Enters_invalid_username_and_password(String username, String password)
	{
		loginpage = new LoginPage(driver);
		loginpage.loginAsUser(username, password);
	}

	@Then("^The Login should be failed$")
	public void The_Login_should_be_failed()
	{
		loginpage = new LoginPage(driver);
		Assert.assertEquals("", "Unauthorized", loginpage.getLoginFailedError());
	}

	@After
	public void tearDown(Scenario scenario) throws JSONException, IOException, APIException
	{
		driver.quit();
		SouceUtils.UpdateResults(USERNAME, ACCESS_KEY, !scenario.isFailed(), sessionId, jobName);
		System.out.println("SauceOnDemandSessionID=" + sessionId + "job-name=" + jobName);
		//testresult.uploadResult(scenario);
		System.out.println(scenario.getStatus()+"  yogi status");
		System.out.println(scenario.getSourceTagNames()+ "  yogi tag names");
	/*	for()
		{
		System.out.println(result+ " --list iterator");
		}*/
		
	}

}
