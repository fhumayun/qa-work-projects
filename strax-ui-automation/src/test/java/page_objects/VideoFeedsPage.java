package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.PropertiesFileReader;

public class VideoFeedsPage extends BaseClass{
	RemoteWebDriver driver;
	static PropertiesFileReader prreader = new PropertiesFileReader();
	CommonClass cClass = new CommonClass(driver);
	public VideoFeedsPage(RemoteWebDriver driver) {
		this.driver = driver;

	}
	public boolean isaddFeedButtonPresent()
	{
		if(driver.findElement(By.id(prreader.getPropertyvalues("VideoFeedsAddButton"))).isDisplayed())
		{
			return true;
		}
		else	
		
		return false;
		
	}

}
