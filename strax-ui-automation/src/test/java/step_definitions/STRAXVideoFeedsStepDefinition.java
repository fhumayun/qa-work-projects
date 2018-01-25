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
	public void User_selects_the_fixed_camera_from_the_list(String fixedcamName) throws InterruptedException   {
		vfPage.searchFixedCam(fixedcamName);
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
	public void Clicks_on_Add_new_button_and_enter_details_for_following_fields(Map<String, String> tableData) throws InterruptedException   {
		vfPage.addNewUAV(tableData.get("Name"), tableData.get("Account"), tableData.get("CameraType"), tableData.get("WowzaPort"), tableData.get("KlvPort"), tableData.get("FrameRate"));
		
	}
	@Then("^New video feed should get created$")
	public void New_video_feed_should_get_created()  {
		
		Assert.assertTrue(true);
	}
}
