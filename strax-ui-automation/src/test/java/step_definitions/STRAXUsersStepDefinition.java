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
	UsersPage uPage;
	CommonClass commonClass;
	DashboardPage dPage;
	public STRAXUsersStepDefinition(BaseClass base) {
		this.base = base;
		uPage = new UsersPage(base.driver);
		dPage = new DashboardPage(base.driver);

	}
	// CommonClass commonClass=new CommonClass(base.driver);

	@Given("^The STRAX application is loggedin with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void Login_with(String userName, String password) {
		LoginPage loginpage = new LoginPage(base.driver);
		loginpage.openLoginPage();
		loginpage.loginAsUser(userName, password);

	}

	@When("^User navigates to Users menu$")
	public void navigateUserPage() throws InterruptedException {
		DashboardPage dboard = new DashboardPage(base.driver);
		dboard.navigateToUsersPage();

	}

	@Then("^User Should be able to search for a user \"([^\"]*)\"$")
	public void user_should_be_able_to_search(String user) throws InterruptedException {

		Assert.assertEquals(true, uPage.searchUser(user));

	}

	@When("^clicks on Add New User button$")
	public void clicks_on_add_new_user() {
		uPage.navigateToAddEditUserPage();
	}

	@When("^User Enters valid values for the following fields and click save button:$")
	public void User_Enters_valid_values_for_the_following_fields_and_click_save_button(Map<String, String> tableData)
			throws Throwable {
		uPage.addUser(tableData.get("Email"), tableData.get("FirstName"), tableData.get("LastName"),
				tableData.get("NewPassword"), tableData.get("Role"), tableData.get("Color"), tableData.get("Device"));
	}

	@Then("^User should get created successfully with email \"([^\"]*)\"$")
	public void Then_User_should_get_created_successfully_with_email(String email) throws InterruptedException {
		Assert.assertTrue(uPage.isUserCreatedSuccessfully());

	}

	@When("^Selects user to edit \"([^\"]*)\"$")
	public void Selects_user_to_edit(String user) throws InterruptedException {
		// Assert.assertEquals(true, uPage.searchUser(user));

	}

	@When("^User Enters valid values for the following fields and click update button:$")
	public void User_Enters_valid_values_for_the_following_fields_and_click_update_button(Map<String, String> tableData)
			throws Throwable {
		uPage.updateUser(tableData.get("Email"), tableData.get("FirstName"), tableData.get("LastName"),
				tableData.get("Role"), tableData.get("Color"), tableData.get("Device"));
	}

	@Then("^Then User should get updated successfully$")
	public void Then_User_should_get_updated_successfully() throws InterruptedException {
		Assert.assertTrue(uPage.isUserUpdatedSuccessfully());

	}

	@And("^Selects user to archive \"([^\"]*)\"$")
	public void Selects_user_to_archive(String user) throws InterruptedException {
		uPage.archiveUser(user);

	}

	@Then("^Then User \"([^\"]*)\" should get archived successfully$")
	public void Then_User_should_get_archived_successfully(String user) throws InterruptedException {
		String deletedUser = "";
		DatabaseConnection conn = new DatabaseConnection();

		if (!uPage.searchUser(user)) {
			deletedUser = conn.deleteUser(user);

		}
		Assert.assertEquals(user, deletedUser);

	}

	@And("User enters value for \"([^\"]*)\" field")
	public void User_enters_value_for_password_field(String userpassword) {
		uPage.addUser(userpassword);

	}

	@Then("^following \"([^\"]*)\" should be displayed")
	public void following_error_should_be_displayed(String errorMessage) {
		Assert.assertEquals(errorMessage, uPage.getErrorMessage());

	}

	@When("^User clicks on Account menu$")
	public void User_clicks_on_Account_menu() throws Throwable {
		
		dPage.navigateToAccountMenu();

	}

	@When("^then clicks on the Change password menu$")
	public void then_clicks_on_the_Change_password_menu() throws Throwable {
		dPage.navigateToPasswordChange();

	}

	@When("^User enters valid \"([^\"]*)\" compliant with Strax security policy$")
	public void user_enters_valid_compliant_with_Strax_security_policy(String newpassword) throws Throwable {
		dPage.changePassword(newpassword);

	}

	@Then("^Password change should be successful$")
	public void password_change_should_be_successful() throws Throwable {
		String PasswordChangeSuccessMessage = dPage.isPasswordChangesSuccesfuly();
		Assert.assertEquals("Password saved!", PasswordChangeSuccessMessage);

	}
	@And("^Clean up test user \"([^\"]*)\"$")
	public void Clean_up_test_user(String user) {
		Assert.assertEquals(user, uPage.deleteUserFromDB(user));

	}

}
