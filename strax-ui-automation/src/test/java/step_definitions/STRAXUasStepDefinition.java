package step_definitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import page_objects.BaseClass;
import page_objects.DashboardPage;
import page_objects.UASsPage;

public class STRAXUasStepDefinition {
	private BaseClass base;
	
	public STRAXUasStepDefinition(BaseClass base)
	{
		this.base = base;
	}
	
	@When("^User navigates to UASs menu$")
	public void navigateUserPage() {
		DashboardPage dboard = new DashboardPage(base.driver);
		dboard.navigateToUASsPage();

	}
	
	@Then("^User should have access to create a new UAS$")
	public void User_should_have_access_to_create_a_new_UAS() {
		UASsPage uasPage = new UASsPage(base.driver);
		Assert.assertEquals(true , uasPage.isAddUasButtonPresent());
	}
	@Then("^User should not have access to create a new UAS$")
	public void User_should_not_have_access_to_create_a_new_UAS() {
		UASsPage uasPage = new UASsPage(base.driver);
		Assert.assertEquals(false, uasPage.isAddUasButtonPresent());
	}
	

}
