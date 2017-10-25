package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.PropertiesFileReader;

public class MapLayersPage extends BaseClass {

	RemoteWebDriver driver;
	static PropertiesFileReader prreader = new PropertiesFileReader();
	CommonClass cClass = new CommonClass(driver);
	public MapLayersPage(RemoteWebDriver driver) {
		this.driver = driver;

	}
	
	public boolean isMapLayerButtonPresent()
	{
		if(driver.findElement(By.id(prreader.getPropertyvalues("MapLayerAddButton"))).isDisplayed())
		{
			return true;
		}
		else	
		
		return false;
		
	}

}
