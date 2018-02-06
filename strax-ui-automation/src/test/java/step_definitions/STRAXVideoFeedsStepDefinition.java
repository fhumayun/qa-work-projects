package step_definitions;

import java.util.Map;

import org.junit.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import page_objects.BaseClass;
import page_objects.DashboardPage;
import page_objects.VideoFeedsPage;

public class STRAXVideoFeedsStepDefinition {
private BaseClass base;
VideoFeedsPage vfPage;
DashboardPage dboard;
	
	public STRAXVideoFeedsStepDefinition(BaseClass base)
	{
		this.base = base;
		vfPage =  new VideoFeedsPage(base.driver);

	}
	@And("^User navigates to Video Feeds menu$")
	public void User_navigates_to_videofeeds_menu() throws InterruptedException {
		dboard = new DashboardPage(base.driver);
		dboard.navigateToVideoFeedsPage();
		
	}
	@And("^User should have access to create a new video feed$")
	public void User_should_have_access_to_create_a_new_video_feed() throws InterruptedException {
		VideoFeedsPage vPage =  new VideoFeedsPage(base.driver);
		vPage.isaddFeedButtonPresent();
		
	}
	@And("^User navigates to Fixed camera tab$")
	public void User_navigates_to_Fixed_camera_tab() throws InterruptedException {
		DashboardPage dPage =  new DashboardPage(base.driver);
		vfPage.navigateToFixedCamTab();
		
	}
	@And("^User selects the fixed camera \"([^\"]*)\" from the list$")
	public void User_selects_the_fixed_camera_from_the_list(String feed) throws InterruptedException   {
		vfPage.searchFeed(feed);
		vfPage.displayFixedCam();
		
	}
	@Then("^User navigates to video simulation page$")
	public void User_navigates_to_video_simulation_page() throws InterruptedException {
		
		
	}
	@Then("^User should be able to play the fixed camera video$")
	public void User_should_be_able_to_play_the_fixed_camera_video()  {
		
		Assert.assertTrue(vfPage.isVideoDisplaying());
	}
	@And("^Clicks on Add new button and enter details for following fields$")
	public void Clicks_on_Add_new_button_and_enter_details_for_following_fields(Map<String, String> tableData) throws Exception   {
		vfPage.addNewUAV(tableData.get("Name"), tableData.get("Account"), tableData.get("CameraType"), tableData.get("WowzaPort"), tableData.get("KlvPort"), tableData.get("FrameRate"), tableData.get("FeedVideo"));
		
	}
	@Then("^New video feed should get created \"([^\"]*)\"$")
	public void New_video_feed_should_get_created(String feed) throws InterruptedException  {
		
		Assert.assertEquals(true, vfPage.startFeed(feed));
	}
	@And("^User select the video feed \"([^\"]*)\" to delete$")
	public void User_select_the_video_feed_to_delete(String feed) throws InterruptedException  {
		
		//vfPage.deleteFeed(feed);
	}
	@Then("^User should be able to delete the video feed \"([^\"]*)\" successfully$")
	public void User_should_be_able_to_delete_the_video_feed_successfully(String feed) throws InterruptedException  {
		vfPage.deleteFeed(feed);
		Assert.assertEquals(false , vfPage.searchFeed(feed));
	}
	@And("^enter details for following fields to update the feed$")
	public void enter_details_for_following_fields_to_update_the_feed(Map<String, String> tableData) throws Exception  {
		
		vfPage.updateFeed(tableData.get("Name"), tableData.get("Account"), tableData.get("CameraType"), tableData.get("WowzaPort"), tableData.get("KlvPort"), tableData.get("FrameRate"), tableData.get("FeedVideo"));
	}
	@And("^User select the video feed \"([^\"]*)\" to edit$")
	public void User_select_the_video_feed_to_edit(String feed) throws InterruptedException  {
		
		
	}
	@Then("^video feed should get updated successfully \"([^\"]*)\"$")
	public void video_feed_should_get_updated_successfully(String feed) throws InterruptedException  {
		Assert.assertEquals(true , vfPage.searchFeed(feed));
	}
}
