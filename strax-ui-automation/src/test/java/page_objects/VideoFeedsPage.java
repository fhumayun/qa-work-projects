package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.PropertiesFileReader;

public class VideoFeedsPage extends BaseClass{
	WebDriver driver;
	static PropertiesFileReader prreader = new PropertiesFileReader();
	CommonClass cClass = new CommonClass(driver);
	public VideoFeedsPage(WebDriver driver) {
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
