package page_objects;

import java.util.ArrayList;
import java.util.List;


import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.PropertiesFileReader;

public class CommonClass{
	
	static PropertiesFileReader prreader = new PropertiesFileReader();
	RemoteWebDriver driver;
	public CommonClass(RemoteWebDriver driver) {
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}

	public LoginPage logOut()
	{
		driver.findElement(By.id(prreader.getPropertyvalues("mainMenuButton"))).click();
		driver.findElement(By.id(prreader.getPropertyvalues("accountMenu"))).click();
		driver.findElement(By.id(prreader.getPropertyvalues("logoutButton"))).click();
		return new LoginPage(driver);
	}
	
	public List<String> getMenuAccessList()
	{
		driver.findElement(By.id(prreader.getPropertyvalues("mainMenuButton"))).click();
		List<WebElement> accessList = driver.findElements(By.xpath(prreader.getPropertyvalues("accessList")));
		List<String> accessSet = new ArrayList<String>();
		for(WebElement element:accessList )
		{
			if(element.getText().isEmpty())
			continue;
			accessSet.add(element.getText());
		}
		return accessSet;
		
	}
	
	public boolean isAccountSettingsAccessible()
	{
		driver.findElement(By.id(prreader.getPropertyvalues("mainMenuButton"))).click();
		driver.findElement(By.id(prreader.getPropertyvalues("accountMenu"))).click();
		boolean state = driver.findElement(By.id(prreader.getPropertyvalues("accountSettings"))).isDisplayed();
		
		return state;
		
	}
	
	public void selectDropdownOption(WebElement dropdown, String optionToSelect) throws InterruptedException
	{
		try{
		dropdown.click();
		List<WebElement> dropdownOptions = driver.findElements(By.tagName("md-option"));
		for(WebElement option: dropdownOptions)
		{
			if((option.getText()).equals(optionToSelect))
			{
				option.click();
				break;
			}
			Thread.sleep(1000);
		}
		}
		catch(Exception e)
		{
			throw e;
		}
		
	}
	


}
