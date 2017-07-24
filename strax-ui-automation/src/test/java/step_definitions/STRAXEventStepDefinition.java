package step_definitions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
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
	@When("^User clicks on add new event button to create a new event$")
	public void User_clicks_on_add_new_event_button_to_create_a_new_event() {
		EventPage eventPage = new EventPage(base.driver);
		eventPage.navigateToCreateNewEvent();
		
	}
	@Then("^can not create new event error message should be displayed$")
	public void can_not_create_new_event_error_message_should_be_displayed() {
		EventPage eventPage = new EventPage(base.driver);
		Assert.assertEquals(true , eventPage.getErrorMessage());
	}
	@And("^Enters valid values for the following fields and \"([^\"]*)\"$")
	public void Enters_valid_values_for_the_following_fields(List<String> participantList, Map<String, String> tableData) throws InterruptedException{
	EventPage eventPage = new EventPage(base.driver);
		eventPage.addNewEvent(tableData.get("Incident"),tableData.get("MissionType"), tableData.get("Stream"),tableData.get("Address"), tableData.get("AptSuiteUnit"),tableData.get("ZipCode") , tableData.get("City"), tableData.get("State"), tableData.get("Latitude"), 
				tableData.get("Longitude"), tableData.get("Description"), participantList);
		
	}
	@Then("^A new event with name \"([^\"]*)\" should get created successfully$")
	public void A_new_event_should_get_created_successfully(String incident) throws InterruptedException {
		EventPage eventPage = new EventPage(base.driver);
		Assert.assertEquals(true , eventPage.searchEvent(incident));
	}
	@When("^User clicks on the active event \"([^\"]*)\" link to join$")
	public void User_clicks_on_the_active_event_link_to_join(String incident) throws InterruptedException {
		EventPage eventPage = new EventPage(base.driver);
		eventPage.joinActiveEvent(incident);
	}
	@Then("^User should be able to join the event successfully$")
	public void User_should_be_able_to_join_the_event_successfully() throws InterruptedException {
		EventPage eventPage = new EventPage(base.driver);
		Assert.assertEquals(true , eventPage.verifyJoinEventSuccess());
		eventPage.closeMap();
		
	}
	@Then("^User joins the event successfully$")
	public void User_joins_the_event_successfully() throws InterruptedException {
		EventPage eventPage = new EventPage(base.driver);
		Assert.assertEquals(true , eventPage.verifyJoinEventSuccess());
		
	}
	@Then("^User should be able to end an active event \"([^\"]*)\" successfully$")
	public void User_should_be_able_to_end_an_active_event_successfully(String incident) throws InterruptedException {
		EventPage eventPage = new EventPage(base.driver);
		eventPage.endActiveEvent();
		Assert.assertEquals(incident , eventPage.deleteEventFromDB(incident));
		
	}
	@And("^User navigates to Event history tab$")
	public void User_navigates_to_Event_history_tab() throws InterruptedException {
		EventPage eventPage = new EventPage(base.driver);
		eventPage.navigateToEventHistory();
		
	}
	@And("^User search and clicks on the event \"([^\"]*)\" link to playback$")
	public void User_search_and_playback_event(String incident) throws InterruptedException {
		EventPage eventPage = new EventPage(base.driver);
		eventPage.joinActiveEvent(incident);
		
	}
	@Then("^User should be able to playback \"([^\"]*)\" event$")
	public void User_should_be_able_to_playback_that_event(String incident) throws InterruptedException {
		EventPage eventPage = new EventPage(base.driver);
		Assert.assertEquals(true , eventPage.verifyJoinEventSuccess());
		eventPage.closeMap();
		eventPage.deleteEventFromDB(incident);
		
		
	}
	@Then("^User should be able to soft delete an active event \"([^\"]*)\" successfully$")
	public void User_should_be_able_to_end_soft_delete_an_active_event_successfully(String incident) throws InterruptedException {
		EventPage eventPage = new EventPage(base.driver);
		eventPage.endActiveEvent();
		eventPage.navigateToEventHistory();
		Assert.assertEquals(true , eventPage.searchEvent(incident));	
	}
	

}
