package page_objects;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.*;


import utils.PropertiesFileReader;

public class CommonClass extends BaseClass{
	
	static PropertiesFileReader prreader = new PropertiesFileReader();
	
	public CommonClass(WebDriver driver) {
		super(driver);
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
	


}
