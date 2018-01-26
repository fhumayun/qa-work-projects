package step_definitions;

import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import page_objects.BaseClass;
import page_objects.DashboardPage;
import page_objects.UASsPage;

public class STRAXUasStepDefinition {
	private BaseClass base;
	UASsPage uasPage;
	DashboardPage dboard;
	public STRAXUasStepDefinition(BaseClass base)
	{
		this.base = base;
		uasPage = new UASsPage(base.driver);
	}
	
	@When("^User navigates to UASs menu$")
	public void navigateUASPage() {
		dboard = new DashboardPage(base.driver);
		dboard.navigateToUASsPage();

	}
	
	@Then("^User should have access to create a new UAS$")
	public void User_should_have_access_to_create_a_new_UAS() {
		
		Assert.assertEquals(true , uasPage.isAddUasButtonPresent());
	}
	@Then("^User should not have access to create a new UAS$")
	public void User_should_not_have_access_to_create_a_new_UAS() {
		Assert.assertEquals(false, uasPage.isAddUasButtonPresent());
	}
	
	@And("^user enters valid values for following UAS fields$")
	public void user_enters_valid_values_for_following_UAS_fields(Map<String, String> tableData) throws InterruptedException {
		System.out.println(".........in straxuas");
		uasPage.addNewUAS(tableData.get("Name"), tableData.get("Feed"), tableData.get("Make"), tableData.get("SerialNumber"), tableData.get("Model"), tableData.get("Status"), tableData.get("Type"));
	}
	@Then("^UAS should get added in the list \"([^\"]*)\"$")
	public void UAS_should_get_added_in_the_list(String uas) throws InterruptedException {
		Assert.assertEquals(true, uasPage.searchUAS(uas));
	}
	@And("^User navigates to Events page$")
	public void User_navigates_to_Events_page() throws InterruptedException {
		dboard.navigateToEventsPage();
	
	}
	@Then("^UAS should get deleted from DB \"([^\"]*)\"$")
	public void UAS_should_get_deleted_from_DB(String uas) {
		Assert.assertEquals(uas, uasPage.deleteUASFromDB(uas));
	}

}
