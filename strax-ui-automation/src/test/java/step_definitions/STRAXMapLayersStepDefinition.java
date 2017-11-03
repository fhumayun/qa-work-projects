package step_definitions;

import org.junit.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import page_objects.BaseClass;
import page_objects.DashboardPage;
import page_objects.MapLayersPage;

public class STRAXMapLayersStepDefinition {
private BaseClass base;
	
	public STRAXMapLayersStepDefinition(BaseClass base)
	{
		this.base = base;
	}

	@Then("^User should have access to create a new map layer$")
	public void User_should_have_access_to_create_a_new_event() {
		MapLayersPage mapLayerPage = new MapLayersPage(base.driver);
		Assert.assertEquals(true , mapLayerPage.isMapLayerButtonPresent());
	}
	@And("^User navigates to MapLayers menu$")
	public void User_navigates_to_MapLayer_menu() throws InterruptedException {
		DashboardPage dPage =  new DashboardPage(base.driver);
		dPage.navigateToMapLayersPage();
		
	}

}
