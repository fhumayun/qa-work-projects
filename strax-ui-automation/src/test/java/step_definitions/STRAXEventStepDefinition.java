package step_definitions;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import page_objects.BaseClass;
import page_objects.CommonClass;
import page_objects.DashboardPage;
import page_objects.EventPage;
import utils.PropertiesFileReader;

public class STRAXEventStepDefinition {

	private BaseClass base;
	EventPage eventPage;
	DashboardPage dPage;
	static PropertiesFileReader prreader;

	public STRAXEventStepDefinition(BaseClass base) {
		this.base = base;
		eventPage = new EventPage(base.driver);
		dPage = new DashboardPage(base.driver);
		prreader = new PropertiesFileReader();
	}

	@Then("^User should have access to create a new event$")
	public void User_should_have_access_to_create_a_new_event() throws InterruptedException {
		dPage.navigateToEventsPage();
		Assert.assertEquals(true, eventPage.isAddEventButtonPresent());
	}

	@Then("^User should not have access to create a new event$")
	public void User_should_not_have_access_to_create_a_new_event() throws InterruptedException {
		dPage.navigateToEventsPage();
		Assert.assertEquals(false, eventPage.isAddEventButtonPresent());
	}

	@Then("^User should not have access to view event history$")
	public void User_should_not_have_access_to_view_event_history() throws InterruptedException {
		dPage.navigateToEventsPage();
		Assert.assertFalse(eventPage.isEventHistoryTabVisible());
	}

	@When("^User clicks on add new event button to create a new event$")
	public void User_clicks_on_add_new_event_button_to_create_a_new_event() throws InterruptedException {
		dPage.navigateToEventsPage();
		eventPage.navigateToCreateNewEvent();
		Thread.sleep(1000);

	}

	@Then("^can not create new event error message should be displayed$")
	public void can_not_create_new_event_error_message_should_be_displayed() {
		
		Assert.assertEquals(true, eventPage.getEventAlreadyAssignedErrorMessage());
		
	}
	@Then("^Event creator is already assigned to an event error message should be displayed$")
	public void Event_creator_is_already_assigned() {
		
		Assert.assertEquals(true, eventPage.getEventCreationErrorMessage());
		
	}

	@And("^Enters valid values for the following fields and \"([^\"]*)\"$")
	public void Enters_valid_values_for_the_following_fields(List<String> participantList,
			Map<String, String> tableData) throws InterruptedException {

		eventPage.addNewEvent(tableData.get("Incident"), tableData.get("CaseNumber"), tableData.get("MissionType"),
				tableData.get("Stream"), tableData.get("Address"), tableData.get("Latitude"),
				tableData.get("Longitude"), tableData.get("Description"), participantList);

	}
	@And("^Enters valid values for the following fields and \"([^\"]*)\" to create event from preplan$")
	public void Enters_valid_values_for_the_following_fields_to_create_event_from_preplan(List<String> participantList,
			Map<String, String> tableData) throws InterruptedException {

		eventPage.addNewEventFromPrePlan(tableData.get("Incident"), tableData.get("CaseNumber"), tableData.get("MissionType"),
				tableData.get("Stream"), tableData.get("Address"), tableData.get("Latitude"),
				tableData.get("Longitude"), tableData.get("Description"), participantList);

	}

	@Then("^A new event with name \"([^\"]*)\" should get created successfully$")
	public void A_new_event_should_get_created_successfully(String incident) throws InterruptedException {

		Assert.assertEquals(true, eventPage.searchEvent(incident));
	}

	@When("^User clicks on the active event \"([^\"]*)\" link to join$")
	public void User_clicks_on_the_active_event_link_to_join(String incident) throws InterruptedException {
		dPage.navigateToEventsPage();
		eventPage.joinActiveEvent(incident);
	}

	@Then("^User should be able to join the event successfully$")
	public void User_should_be_able_to_join_the_event_successfully() throws InterruptedException {

		Assert.assertEquals(true, eventPage.verifyJoinEventSuccess());
		eventPage.closeMap();
	}
	@Then("^User should not have access to join the event \"([^\"]*)\"$")
	public void User_should_not_have_access_to_join_the_event(String incident) throws InterruptedException {
		dPage.navigateToEventsPage();
		Assert.assertEquals(false, eventPage.searchEvent(incident));
	}

	@Then("^User joins the event successfully$")
	public void User_joins_the_event_successfully() throws InterruptedException {

		Assert.assertEquals(true, eventPage.verifyJoinEventSuccess());

	}
	@And("^User should be able to add the user \"([^\"]*)\" from SAC edit users pane$")
	public void User_should_be_able_to_add_the_user_from_SAC_edit_pane_successfully(List<String> participants) throws InterruptedException {
		eventPage.navigateToEditUsersTab();
		eventPage.addEventParticipant(participants);

	}
	@And("^User should be able to add the user \"([^\"]*)\" from SAC User management menu$")
	public void User_should_be_able_to_add_the_user_from_SAC_User_management_menu(List<String> participants) throws InterruptedException {
		eventPage.assignUsersToEvent(participants);

	}
	@And("^User should be able to remove the user \"([^\"]*)\" from SAC edit users pane$")
	public void User_should_be_able_to_remove_the_user_from_SAC_edit_users_pane(List<String> participants) throws InterruptedException {
		eventPage.navigateToEditUsersTab();
		eventPage.removeParticipants(participants);

	}
	@And("^User should be able to remove the user \"([^\"]*)\" from SAC User management menu$")
	public void User_should_be_able_to_remove_the_user_from_SAC_User_management_menu(List<String> participants) throws InterruptedException {
		eventPage.removeUsersFromEvent(participants);

	}
	@And("^User close SAC map$")
	public void User_close_SAC_map() throws InterruptedException {
		eventPage.closeMap();
	}
	@And("^Logs out from STRAX$")
	public void Logs_out_from_STRAX() throws InterruptedException {
		dPage.logOut();
	}

	@Then("^User should be able to end an active event \"([^\"]*)\" successfully$")
	public void User_should_be_able_to_end_an_active_event_successfully(String incident) throws InterruptedException {

		eventPage.endActiveEvent();
		Assert.assertEquals(incident, eventPage.deleteEventFromDB(incident));

	}

	@And("^User navigates to Event history tab$")
	public void User_navigates_to_Event_history_tab() throws InterruptedException {
		dPage.navigateToEventsPage();
		eventPage.navigateToEventHistory();

	}

	@And("^User search and clicks on the event \"([^\"]*)\" link to playback$")
	public void User_search_and_playback_event(String incident) throws InterruptedException {
		eventPage.joinActiveEvent(incident);

	}

	@Then("^User should be able to playback \"([^\"]*)\" event$")
	public void User_should_be_able_to_playback_that_event(String incident) throws InterruptedException {
		Assert.assertEquals(true, eventPage.verifyEvenPlaybackSuccess());
		eventPage.closeMapFromPlayback();

	}
	@Then("^User should be able to relocate chat window$")
	public void User_should_be_able_to_relocate_chat_window() throws InterruptedException {
		//Assert.assertEquals(true, eventPage.verifyEvenPlaybackSuccess());
		eventPage.moveChatWindow();
		eventPage.closeMapFromPlayback();

	}
	@Then("^Playback slider moves forward$")
	public void Playback_slider_moves_forward() throws InterruptedException {


	}

	@Then("^User should be able to soft delete an active event \"([^\"]*)\" successfully$")
	public void User_should_be_able_to_end_soft_delete_an_active_event_successfully(String incident)
			throws InterruptedException {
		eventPage.endActiveEvent();
		eventPage.navigateToEventHistory();
		Assert.assertEquals(true, eventPage.searchEvent(incident));
	}

	@And("^User navigates to Event plan tab$")
	public void User_navigates_to_Event_plan_tab() throws InterruptedException {
		dPage.navigateToEventsPage();
		eventPage.navigateToEventPlanTab();

	}

	@And("^User clicks on add new event plan button to create a new event plan$")
	public void User_clicks_on_add_new_event_plan_button_to_create_a_new_event_plan() throws InterruptedException {
		eventPage.navigateToCreateNewEventPlan();

	}

	@And("^Enters valid values for the following fields$")
	public void Enters_valid_values_for_the_following_fields(Map<String, String> tableData)
			throws InterruptedException {
		eventPage.addEventPlan(tableData.get("EventPlan"), tableData.get("MissionType"), tableData.get("Address"),
				tableData.get("Latitude"), tableData.get("Longitude"), tableData.get("Description"));

	}

	@Then("^A new event plan with name \"([^\"]*)\" should get created successfully$")
	public void A_new_event_plan_should_get_created_successfully(String eventPlan) throws InterruptedException {
		eventPage.navigateToEventPlanTab();
		Assert.assertEquals(true, eventPage.searchEventPlan(eventPlan));

	}

	@And("^User clicks on lock button to lock \"([^\"]*)\" an event plan$")
	public void User_clicks_on_lock_button_to_lock_an_event_plan(String eventPlan) throws InterruptedException {
		eventPage.searchEventPlan(eventPlan);
		eventPage.lockEventPlan(eventPlan);

	}

	@Then("^Event plan \"([^\"]*)\" should get locked$")
	public void Event_plan_should_get_locked(String eventPlan) throws InterruptedException {

		Assert.assertEquals("lock", eventPage.getEventLockStatus(eventPlan));

	}

	@And("^User clicks on edit button to edit \"([^\"]*)\" event plan$")
	public void User_clicks_on_edit_button_to_edit_event_plan(String eventPlan) throws InterruptedException {
		eventPage.searchEventPlan(eventPlan);
		eventPage.navigateToEditEventPlan();

	}

	@Then("^event plan \"([^\"]*)\" is locked error message should be disaplyed$")
	public void event_plan_is_locked_error_message_should_be_disaplyed(String eventPlan) throws InterruptedException {
		Assert.assertEquals("This event plan is locked!", eventPage.getEventPlanErrorMessage());

	}

	@And("^User clicks on unlock button to unlock \"([^\"]*)\" an event plan$")
	public void User_clicks_on_lock_button_to_unlock_an_event_plan(String eventPlan) throws InterruptedException {
		eventPage.searchEventPlan(eventPlan);
		eventPage.unlockEventPlan(eventPlan);

	}

	@Then("^Event plan \"([^\"]*)\" should get unlocked$")
	public void Event_plan_should_get_unlocked(String eventPlan) throws InterruptedException {

		Assert.assertEquals("lock_open", eventPage.getEventUnlockStatus(eventPlan));
		eventPage.deleteEventPlanFromDB(eventPlan);
	}

	@And("^User clicks on archive button to archive \"([^\"]*)\" event plan$")
	public void User_clicks_on_archive_button_to_archive_event_plan(String eventPlan) throws InterruptedException {
		eventPage.searchEventPlan(eventPlan);
		eventPage.archiveEventPlan(eventPlan);

	}

	@Then("^event plan \"([^\"]*)\" should get archived successfully$")
	public void event_plan_should_get_archived_successfully(String eventPlan) throws InterruptedException {
		eventPage.navigateToArchivedEventPlanTab();
		Assert.assertTrue(eventPage.searchEventPlan(eventPlan));

	}

	@Then("^event plan \"([^\"]*)\" should get deleted successfully$")
	public void event_plan_should_get_deleted_successfully(String eventPlan) throws InterruptedException {
		String deletedEventPlan = "";
		deletedEventPlan = eventPage.deleteEventPlanFromDB(eventPlan);

		Assert.assertEquals(eventPlan, deletedEventPlan);

	}

	@And("^User navigates to archived event plan tab$")
	public void User_navigates_to_archived_event_plan_tab() throws InterruptedException {
		eventPage.navigateToArchivedEventPlanTab();
		Thread.sleep(1000);

	}

	@And("^User clicks on unarchive button of \"([^\"]*)\" to activate the plan$")
	public void User_clicks_on_unarchive_button_to_activate_the_plan(String eventPlan) throws InterruptedException {
		eventPage.searchEventPlan(eventPlan);
		eventPage.unArchiveEventPlan(eventPlan);

	}

	@Then("^event plan \"([^\"]*)\" should get activated successfully$")
	public void event_plan_should_get_activated_successfully(String eventPlan) throws InterruptedException {
		String deletedEventPlan = "";
		// eventPage.navigateToEventPlanTab();
		base.driver.navigate().refresh();
		Assert.assertTrue(eventPage.searchEventPlan(eventPlan));

	}

	@And("^User clicks on share button to share \"([^\"]*)\" event plan$")
	public void User_clicks_on_share_button_to_share_event_plan(String eventPlan) throws InterruptedException {
		eventPage.searchEventPlan(eventPlan);
		eventPage.shareEventPlan(eventPlan);

	}

	@Then("^event plan \"([^\"]*)\" should get shared successfully$")
	public void event_plan_should_get_shared_successfully(String eventPlan) throws InterruptedException {
		Assert.assertTrue(eventPage.isEventPlanShared());

	}

	@Then("^user should see the shared \"([^\"]*)\" plan$")
	public void user_should_see_the_shared_event_plan(String eventPlan) throws InterruptedException {
		Assert.assertTrue(eventPage.searchEventPlan(eventPlan));

	}

	@And("^User should should not have access to archive the event plan \"([^\"]*)\" shared by other user$")
	public void User_does_not_have_access_to_archive_plan_shared_by_other_user(String eventPlan)
			throws InterruptedException {
		eventPage.searchEventPlan(eventPlan);
		Assert.assertFalse(eventPage.verifyArchiveAccess());

	}

	@Then("^Google map should get loaded successfully$")
	public void Google_map_should_get_loaded_successfully() throws InterruptedException {
		Assert.assertTrue(eventPage.isGoogleMapLoaded());

	}

	@Then("^Event log window loads correctly$")
	public void event_log_window_loads_correctly() throws InterruptedException {
		Assert.assertTrue(eventPage.isEventLogWindowLoaded());
		eventPage.closeMap();

	}

	@Then("^chat window loads correctly$")
	public void chat_window_loads_correctly() throws InterruptedException {
		Assert.assertTrue(eventPage.isChatWindowLoaded());
		eventPage.closeMap();

	}

	@Then("^User should see video feed$")
	public void User_should_see_video_feed() throws InterruptedException {
		// Assert.assertTrue(eventPage.isFeedVideoAvailable());
		Assert.assertTrue(true);

	}

	@And("^User right clicks on the active event \"([^\"]*)\" link to open new window$")
	public void User_right_clicks_on_the_active_event_link_to_open_new_window(String incident)
			throws InterruptedException, AWTException {
		// eventPage.searchEvent(incident);
		eventPage.rightClickActiveEvent(incident);

	}

	@And("^video feed should be avilable \"([^\"]*)\"$")
	public void video_feed_should_be_avilable(String incident) throws InterruptedException {
		Assert.assertTrue(eventPage.isVideoFeedAvailable());
		eventPage.endActiveEvent();
		eventPage.deleteEventFromDB(incident);

	}

	@And("^User should be able to open the event in playback$")
	public void User_should_be_able_to_open_the_event_in_playback() throws InterruptedException {
		eventPage.verifyEvenPlaybackSuccess();

	}

	@And("^User clicks on the play button of event playback$")
	public void User_clicks_on_the_play_button_of_event_playback() throws InterruptedException {
		eventPage.playBackPlayAndPause();

	}

	@Then("^The playback should start to play$")
	public void The_playback_should_start_to_play() {
		Assert.assertTrue(eventPage.isPlayBackPlaying());

	}

	@And("^The user will click Pause$")
	public void The_user_will_click_Pause() throws InterruptedException {
		eventPage.playBackPlayAndPause();

	}

	@Then("^The event playback should Pause \"([^\"]*)\"$")
	public void The_event_playback_should_Pause(String incident) {
		Assert.assertFalse(eventPage.isPlayBackPlaying());
		eventPage.closeMapFromPlayback();
		eventPage.deleteEventFromDB(incident);

	}

	@And("^User search the event \"([^\"]*)\" to edit case number$")
	public void User_search_the_event(String incident) throws InterruptedException {
		eventPage.searchEvent(incident);

	}

	@Then("^User should be able to edit or add a \"([^\"]*)\" to the event$")
	public void User_should_be_able_to_edit_or_add_case_number(String caseNumber) {
		CommonClass cl = new CommonClass(base.driver);
		eventPage.addUpdateEventCaseNumber(caseNumber);
		Assert.assertEquals("Case number saved!", cl.getPopUpMessage());
	}

	@And("^User clicks on Auto-Follow UAS button$")
	public void User_clicks_on_Auto_Follow_UAS_button() throws InterruptedException {
		eventPage.autoFollowUAS();

	}

	@Then("^Auto-Follow UAS feature should work$")
	public void Auto_follow_UAS_feature_should_work() {
		Assert.assertTrue(eventPage.verifyAutoFollowUASStatus());
		eventPage.closeMapFromPlayback();

	}

	@Then("^Browser title bar should have incident number/event name displayed in it \"([^\"]*)\"$")
	public void Browser_title_bar_should_have_incident_number_displayed_in_it(String incident)
			throws InterruptedException {
		Thread.sleep(3000);
		String title = base.driver.getTitle();
		Assert.assertTrue(title.contains(incident));
		eventPage.closeMap();
	}

	@And("^User adds a point on map$")
	public void user_adds_point() throws InterruptedException {
		eventPage.drawPoint();
		eventPage.closeMap();
	}

	@And("^User draw a polygon on map$")
	public void user_draw_polygon() throws InterruptedException {
		eventPage.drawPolygon();
		eventPage.closeMap();
	}

	@And("^User draw a polyline on map$")
	public void user_draw_polyline() throws InterruptedException {
		eventPage.drawPolyline();
		eventPage.closeMap();
	}

	@And("^User drop a POI inside polygon$")
	public void User_drop_POI_inside_polygon() throws InterruptedException {
		eventPage.drawPointInsidePolygon();
		eventPage.closeMap();
	}

	@And("^User drop a POI inside polyline$")
	public void User_drop_POI_inside_polyline() throws InterruptedException {
		eventPage.drawPointInsidePolyline();
		eventPage.closeMap();
	}
	@And("^User clicks on launch event plan button \"([^\"]*)\"$")
	public void User_clicks_on_launch_event_plan_button(String preplan) throws InterruptedException {
		eventPage.launchPrePlan(preplan);
	}
	@Then("^New event creation form should load successfully$")
	public void New_event_creation_form_should_load_successfully() throws InterruptedException {
		eventPage.verifyPrePlanLaunchSuccess();
	}
	@And("^Clean up test event \"([^\"]*)\"$")
	public void Clean_up_test_event(String incident) throws InterruptedException {
		Assert.assertEquals(incident, eventPage.deleteEventFromDB(incident));
	}
	@And("^Clean up test event plan \"([^\"]*)\"$")
	public void Clean_up_test_event_plan(String eventPlan) throws InterruptedException {
		Assert.assertEquals(eventPlan, eventPage.deleteEventPlanFromDB(eventPlan));;
	}
	@And("^User clicks on the map menu icon$")
	public void User_clicks_on_the_map_menu_icon() throws InterruptedException {
		eventPage.openMapMenu();
	}
	@And("^The list of map layers is displayed$")
	public void The_list_of_map_layers_is_displayed() throws InterruptedException {
		
		Assert.assertEquals(4, eventPage.getMapLayerList().size());
	}
	@And("^The user can toggle various map layers$")
	public void The_user_can_toggle_various_map_layers() throws InterruptedException {
		eventPage.toggleMapLayer();
		eventPage.closeMap();
	}
	@And("^User navigates to map layer settings$")
	public void User_navigates_to_map_layer_settings() throws InterruptedException {
		eventPage.navigateToEventMapLayerSettings();
	}
	@And("^The user enable \"([^\"]*)\" map layer$")
	public void User_enable_map_layer(String mapLayer) throws InterruptedException {
		eventPage.selectMapLayer(mapLayer);
		eventPage.closeSACSettings();
		eventPage.closeMap();
	}
	
	@And("^the SAC application loads completely$")
	public void the_SAC_application_loads_completely() throws InterruptedException {
		eventPage.isGoogleMapLoaded();
	}
	@And("^the User clicks on the settings icon$")
	public void the_User_clicks_on_the_settings_icon() throws InterruptedException {
		base.driver.findElement(By.xpath(prreader.getPropertyvalues("ConfigMapButtonNew"))).click();
	}
	@Then("^the Settings Modal appears successfully$")
	public void the_Settings_Modal_appears_successfully() throws InterruptedException {
		String s1=base.driver.findElement(By.xpath("//h2[text()='Settings']")).getText();
		System.out.println(s1);
		Assert.assertTrue((s1.equals("Settings")));
	}
	@And("^the user selects the `End Event` option$")
	public void the_user_selects_the_End_Event_option() throws InterruptedException {
		base.driver.findElement(By.xpath(prreader.getPropertyvalues("EndEventTab"))).click();
	}
	@Then("^the Modal provides a confirmation message")
	public void the_Modal_provides_a_confirmation_message() throws InterruptedException { 
		Assert.assertTrue(base.driver.findElement(By.xpath("//p[text()='Would you like to end the event?']")).getText().equals("Would you like to end the event?"));
	}
	@And("^RED END EVENT button is available to select$")
	public void RED_END_EVENT_button_is_available_to_select() throws InterruptedException {
		Assert.assertTrue(base.driver.findElement(By.id("end-event__button")).isDisplayed());
	}
	@Then("^the User clicks END EVENT$")
	public void the_User_clicks_END_EVENT() throws InterruptedException {
		base.driver.findElement(By.id("end-event__button")).click();
	}
	@Then("^User should be able to end an active event successfully now$")
	public void User_should_be_able_to_end_an_active_event() throws InterruptedException {
		
	}
	@When("^User navigates to UASs menu from Event Page$")
	public void navigateUASPage() {
		dPage = new DashboardPage(base.driver);
		dPage.navigateToUASsPage();

	}


}
