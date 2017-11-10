package step_definitions;

import cucumber.api.java.en.And;
import page_objects.BaseClass;
import page_objects.DashboardPage;
import page_objects.VideoFeedsPage;

public class STRAXVideoFeedsStepDefinition {
private BaseClass base;
	
	public STRAXVideoFeedsStepDefinition(BaseClass base)
	{
		this.base = base;
	}
	@And("^User navigates to Video Feeds menu$")
	public void User_navigates_to_videofeeds_menu() throws InterruptedException {
		DashboardPage dPage =  new DashboardPage(base.driver);
		dPage.navigateToVideoFeedsPage();
		
	}
	@And("^User should have access to create a new video feed$")
	public void User_should_have_access_to_create_a_new_video_feed() throws InterruptedException {
		VideoFeedsPage vPage =  new VideoFeedsPage(base.driver);
		vPage.isaddFeedButtonPresent();
		
	}
}
