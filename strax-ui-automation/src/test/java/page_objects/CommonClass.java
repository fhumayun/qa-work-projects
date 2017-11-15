package page_objects;

import java.util.ArrayList;
import java.util.List;


import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.PropertiesFileReader;

public class CommonClass{
	
	static PropertiesFileReader prreader = new PropertiesFileReader();
	RemoteWebDriver driver;
	public CommonClass(RemoteWebDriver driver) {
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}

	public LoginPage logOut() throws InterruptedException
	{
		
		WebDriverWait wait = new WebDriverWait(driver,15);
		//wait.until(ExpectedConditions.elementToBeClickable(By.id(prreader.getPropertyvalues("mainMenuButton"))));
		driver.findElement(By.id(prreader.getPropertyvalues("mainMenuButton"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prreader.getPropertyvalues("accountMenu"))));
		driver.findElement(By.id(prreader.getPropertyvalues("accountMenu"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prreader.getPropertyvalues("logoutButton"))));
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
		//WebDriverWait wait = new WebDriverWait(driver,10);
		//wait.until(ExpectedConditions.visi)
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
