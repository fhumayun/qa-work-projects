package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.PropertiesFileReader;

public class EventPage extends BaseClass {

	RemoteWebDriver driver;
	static PropertiesFileReader prreader = new PropertiesFileReader();
	CommonClass cClass = new CommonClass(driver);
	public EventPage(RemoteWebDriver driver) {
		this.driver = driver;

	}
	
	public void createNewEvent()
	{
		
	}
	
	public boolean isAddEventButtonPresent()
	{
		if(driver.findElement(By.id(prreader.getPropertyvalues("EventAddButton"))).isDisplayed())
		{
			return true;
		}
		else	
		
		return false;
		
	}
	
}
