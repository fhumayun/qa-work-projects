package step_definitions;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.Scenario;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import page_objects.BaseClass;
import page_objects.CommonClass;
import page_objects.DashboardPage;
import page_objects.LoginPage;
import page_objects.UsersPage;
import utils.DatabaseConnection;

public class STRAXUsersStepDefinition {
	// RemoteWebDriver driver;
	Scenario scenario;
	private BaseClass base;
	

	public STRAXUsersStepDefinition(BaseClass base) {
		this.base = base;

	}
	//CommonClass commonClass=new CommonClass(base.driver);
	
	@Given("^The STRAX application is loggedin with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void Login_with(String userName, String password) {
		LoginPage loginpage = new LoginPage(base.driver);
		loginpage.openLoginPage();
		loginpage.loginAsUser(userName, password);

	}

	@When("^User navigates to Users menu$")
	public void navigateUserPage() throws InterruptedException {
		DashboardPage dboard = new DashboardPage(base.driver);
		WebDriverWait wait = new WebDriverWait(base.driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//md-tab-item[text()='Active Events']")));
		dboard.navigateToUsersPage();

	}

	@Then("^User Should be able to search for a user \"([^\"]*)\"$")
	public void user_should_be_able_to_search(String user) throws InterruptedException {
		UsersPage uPage = new UsersPage(base.driver);

		Assert.assertEquals(true, uPage.searchUser(user));

	}
	
	
	@When("^clicks on Add New User button$")
	public void clicks_on_add_new_user() {
		UsersPage uPage = new UsersPage(base.driver);
		uPage.navigateToAddEditUserPage();
	}
	@When("^User Enters valid values for the following fields and click save button:$")
	public void User_Enters_valid_values_for_the_following_fields_and_click_save_button(Map<String, String> tableData)throws Throwable {
		UsersPage uPage = new UsersPage(base.driver);
		uPage.addUser(tableData.get("Email"), tableData.get("FirstName"), tableData.get("LastName"), tableData.get("NewPassword"), tableData.get("Role"), tableData.get("Color"), tableData.get("Device"));
	}
	
	@Then("^Then User should get created successfully with email \"([^\"]*)\"$")
	public void Then_User_should_get_created_successfully_with_email(String email) throws InterruptedException {
		UsersPage uPage = new UsersPage(base.driver);
		Assert.assertTrue(uPage.isUserCreatedSuccessfully());

	}
	@When("^Selects user to edit \"([^\"]*)\"$")
	public void Selects_user_to_edit(String user) throws InterruptedException {
		UsersPage uPage = new UsersPage(base.driver);
		//Assert.assertEquals(true, uPage.searchUser(user));

	}
	@When("^User Enters valid values for the following fields and click update button:$")
	public void User_Enters_valid_values_for_the_following_fields_and_click_update_button(Map<String, String> tableData)throws Throwable {
		UsersPage uPage = new UsersPage(base.driver);
		uPage.updateUser(tableData.get("Email"), tableData.get("FirstName"), tableData.get("LastName"), tableData.get("Role"), tableData.get("Color"), tableData.get("Device"));
	}
	
	@Then("^Then User should get updated successfully$")
	public void Then_User_should_get_updated_successfully() throws InterruptedException {
		UsersPage uPage = new UsersPage(base.driver);
		Assert.assertTrue(uPage.isUserUpdatedSuccessfully());
		

	}
	@And("^Selects user to archive \"([^\"]*)\"$")
	public void Selects_user_to_archive(String user) throws InterruptedException {
		UsersPage uPage = new UsersPage(base.driver);
		uPage.archiveUser(user);

	}
	@Then("^Then User \"([^\"]*)\" should get archived successfully$")
	public void Then_User_should_get_archived_successfully(String user) throws InterruptedException {
		UsersPage uPage = new UsersPage(base.driver);
		String deletedUser="";
		DatabaseConnection conn = new DatabaseConnection();
		
		if(!uPage.searchUser(user))
		{
			deletedUser = conn.mongodbOperation(user);
			
		}
		Assert.assertEquals(user, deletedUser);

	}
	@And("User enters value for \"([^\"]*)\" field")
	public void User_enters_value_for_password_field(String userpassword) {
		UsersPage uPage = new UsersPage(base.driver);
		uPage.addUser(userpassword);

	}
	@Then("^following \"([^\"]*)\" should be displayed")
	public void following_error_should_be_displayed(String errorMessage) {
		UsersPage uPage = new UsersPage(base.driver);
		Assert.assertEquals(errorMessage, uPage.getErrorMessage());

	}
	
	@When("^User clicks on Username header field$")
	public void user_clicks_on_Username_header_field() throws Throwable {
		CommonClass commonClass=new CommonClass(base.driver);
		 commonClass.navigateToUserNameHeader();
	    
	}

	@When("^then clicks on the Change password menu$")
	public void then_clicks_on_the_Change_password_menu() throws Throwable {
	   CommonClass commonClass=new CommonClass(base.driver);
	   commonClass.navigateToPasswordChange();
	  
	}

	@When("^User enters valid \"([^\"]*)\" compliant with Strax security policy$")
	public void user_enters_valid_compliant_with_Strax_security_policy(String newpassword) throws Throwable {
		CommonClass commonClass=new CommonClass(base.driver);
		commonClass.changePassword(newpassword);
	    
	}

	@Then("^Password change should be successful$")
	public void password_change_should_be_successful() throws Throwable {
		CommonClass commonClass=new CommonClass(base.driver);
	    String PasswordChangeSuccessMessage=commonClass.isPasswordChangesSuccesfuly();
	    Assert.assertEquals("Password saved!", PasswordChangeSuccessMessage);
	  
	}

}
