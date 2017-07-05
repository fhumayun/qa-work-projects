package step_definitions;

import org.junit.Assert;

import cucumber.api.java.en.Then;
import page_objects.BaseClass;
import page_objects.EventPage;

public class STRAXEventStepDefinition {
	
	private BaseClass base;
	
	public STRAXEventStepDefinition(BaseClass base)
	{
		this.base = base;
	}
	
	@Then("^User should have access to create a new event$")
	public void User_should_have_access_to_create_a_new_event() {
		EventPage eventPage = new EventPage(base.driver);
		Assert.assertEquals(true , eventPage.isAddEventButtonPresent());
	}
	@Then("^User should not have access to create a new event$")
	public void User_should_not_have_access_to_create_a_new_event() {
		EventPage eventPage = new EventPage(base.driver);
		Assert.assertEquals(false , eventPage.isAddEventButtonPresent());
	}

}
