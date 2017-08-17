package step_definitions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.APIException;
import utils.PropertiesFileReader;
import utils.TestrailResultUpload;
import utils.SouceUtils;
import page_objects.BaseClass;
import page_objects.CommonClass;
import page_objects.DashboardPage;
import page_objects.LoginPage;
import page_objects.UsersPage;
import cucumber.api.Scenario;
import cucumber.api.java.*;
import cucumber.api.java.en.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.junit.Assert;


public class STRAXLoginStepDefinition
{
	//RemoteWebDriver driver;
	Scenario scenario;

	private BaseClass base;
	public STRAXLoginStepDefinition(BaseClass base) {
		this.base = base;
		
	}


	
	
	DashboardPage dashboardpage;
	UsersPage uPage;
	static PropertiesFileReader prreader = new PropertiesFileReader();
	TestrailResultUpload testresult = new TestrailResultUpload();
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
	
	@Given("^The STRAX Application login page is open$")
	public void openLoginPage()
	{

		LoginPage loginpage = new LoginPage(base.driver);
		loginpage.openLoginPage();

	}
	@Then("^The title bar should contain STRAX$")
	public void The_title_bar_should_contain_STRAX()
	{
		LoginPage loginpage = new LoginPage(base.driver);
		Assert.assertEquals("", "STRAX™", loginpage.getLoginPageTitle());
		
	}
	@Then("The footer copyright should contain \"([^\"]*)\"$")
	public void The_footer_copyright_should_contain_String(String capyright)
	{
		LoginPage loginpage = new LoginPage(base.driver);
		Assert.assertEquals("", capyright, loginpage.getFooterCopyrightInfo());
	}

	@When("^User Enters Valid \"([^\"]*)\" and \"([^\"]*)\"$")
	public void User_Enters_Valid_username_and_password(String username, String password)
	{
		LoginPage loginpage = new LoginPage(base.driver);
		loginpage = new LoginPage(base.driver);
		loginpage.loginAsUser(username, password);
	}

	@Then("^The Login should be successful$")
	public void The_Login_should_be_successful()
	{
		dashboardpage = new DashboardPage(base.driver);
		Assert.assertTrue(dashboardpage.verifyDashboardNavigation());
	}

	@When("^User Enters invalid \"([^\"]*)\" and \"([^\"]*)\"$")
	public void User_Enters_invalid_username_and_password(String username, String password)
	{
		LoginPage loginpage = new LoginPage(base.driver);
		loginpage = new LoginPage(base.driver);
		loginpage.loginAsUser(username, password);
	}

	@Then("^The Login should be failed$")
	public void The_Login_should_be_failed()
	{
		LoginPage loginpage = new LoginPage(base.driver);
		loginpage = new LoginPage(base.driver);
		Assert.assertEquals("", "Invalid Credentials", loginpage.getLoginFailedError());
	}
	@Then("^The Login page should reject the credentials$")
	public void The_Login_page_should_reject_the_credentials()
	{
		LoginPage loginpage = new LoginPage(base.driver);
		loginpage = new LoginPage(base.driver);
		String malformedUserEmailError="Please enter a valid email address from your agency.";
		String malformedUserPasswordError="You must enter a password.";
		//Assert.assertEquals("", "You must enter a password.", loginpage.getInvalidPasswordError());
		System.out.println(loginpage.getInvalidPasswordError());
		Assert.assertTrue(malformedUserEmailError.equals(loginpage.getInvalidEmailError()) || malformedUserPasswordError.equals(loginpage.getInvalidPasswordError()));

		
	}
	@When("^user navigates to useremail field using tab key and enters \"([^\"]*)\"$")
	public void user_navigates_to_useremail_field_using_tab_key_and_enters(String userEmail)
	{
		base.driver.findElement(By.id(prreader.getPropertyvalues("userEmail"))).sendKeys(Keys.TAB);
		base.driver.findElement(By.id(prreader.getPropertyvalues("userEmail"))).sendKeys(userEmail);
				
	}
	@When("^user navigates to password field using tab key and enters \"([^\"]*)\"$")
	public void user_navigates_to_password_field_using_tab_key_and_enters(String userPassword)
	{
		base.driver.findElement(By.id(prreader.getPropertyvalues("userPassword"))).sendKeys(Keys.TAB);
		base.driver.findElement(By.id(prreader.getPropertyvalues("userPassword"))).sendKeys(userPassword);
				
	}
	@When("^user hits Enter key$")
	public void user_hits_enter_key()
	{
		base.driver.findElement(By.id(prreader.getPropertyvalues("loginButton"))).sendKeys(Keys.ENTER);
						
	}
	@Then("^The user should have access to these \"([^\"]*)\"$")
	public void The_user_should_have_access_to_these(List<String> expectedMenu)
	{
		CommonClass cl = new CommonClass(base.driver);
		List<String> actualMenu = cl.getMenuAccessList();
		Assert.assertEquals("", expectedMenu, actualMenu);				
	}
	
	
	@Then("^The user should have access to account settings menu$")
	public void The_user_should_have_access_to_account_settings()
	{
		CommonClass cl = new CommonClass(base.driver);
		Assert.assertEquals(true, cl.isAccountSettingsAccessible());				
	}
	@Then("^The user should not have access to account settings menu$")
	public void The_user_should_not_have_access_to_account_settings()
	{
		CommonClass cl = new CommonClass(base.driver);
		Assert.assertEquals(false, cl.isAccountSettingsAccessible());
		
		
		
	}


}
