package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.PropertiesFileReader;

public class UASsPage {
	
	RemoteWebDriver driver;
	static PropertiesFileReader prreader = new PropertiesFileReader();
	CommonClass cClass = new CommonClass(driver);
	public UASsPage(RemoteWebDriver driver) {
		this.driver = driver;

	}
	
	public boolean isAddUasButtonPresent()
	{
		if(driver.findElement(By.id(prreader.getPropertyvalues("UASAddButton"))).isDisplayed())
		{
			return true;
		}
		else	
		
		return false;		
	}
	
	public void addNewUAS(String name, String feed, String make, String serialNumber, String model, String status, String type) throws InterruptedException
	{
		driver.findElement(By.id(prreader.getPropertyvalues("fidgetNameTextBox"))).sendKeys(name);
		WebElement selectFeed = driver.findElement(By.id(prreader.getPropertyvalues("fidgetFeedList")));
		cClass.selectDropdownOption(selectFeed, feed);
		WebElement selectMake = driver.findElement(By.id(prreader.getPropertyvalues("fidgetMakeList")));
		cClass.selectDropdownOption(selectMake, make);
		driver.findElement(By.id(prreader.getPropertyvalues("fidgetSerialNumberTextBox"))).sendKeys(serialNumber);
		WebElement selectModel = driver.findElement(By.id(prreader.getPropertyvalues("fidgetModelList")));
		cClass.selectDropdownOption(selectModel, model);
		WebElement selectStatus = driver.findElement(By.id(prreader.getPropertyvalues("fidgetUasStatusList")));
		cClass.selectDropdownOption(selectStatus, status);
		WebElement selectType = driver.findElement(By.id(prreader.getPropertyvalues("fidgetUasTypeList")));
		cClass.selectDropdownOption(selectType, type);
		driver.findElement(By.id(prreader.getPropertyvalues("saveButton"))).click();
	}
	

}
