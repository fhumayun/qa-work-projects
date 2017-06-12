package page_objects;

import org.openqa.selenium.*;

import utils.PropertiesFileReader;

public class UpdateAccount extends BaseClass{
	
	static PropertiesFileReader prreader = new PropertiesFileReader();
	
	public UpdateAccount(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void updateAccount(String name, String website, String email, String phone, String language, String description )
	{
		try{
		driver.findElement(By.id(prreader.getPropertyvalues("accountName"))).sendKeys(name);
		driver.findElement(By.id(prreader.getPropertyvalues("accountWebsite"))).sendKeys(website);
		driver.findElement(By.id(prreader.getPropertyvalues("accountEmail"))).sendKeys(email);
		driver.findElement(By.id(prreader.getPropertyvalues("accountPhone"))).sendKeys(phone);
		driver.findElement(By.id(prreader.getPropertyvalues("accountLanguage"))).sendKeys(language);
		driver.findElement(By.id(prreader.getPropertyvalues("accountDescription"))).sendKeys(description);
		driver.findElement(By.id(prreader.getPropertyvalues("accountSaveButton"))).click();
		}
		catch(Exception e)
		{
			throw e;
		}
		
	}

}
