package page_objects;

import java.util.ArrayList;
import java.util.List;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.PropertiesFileReader;

public class CommonClass{
	
	static PropertiesFileReader prreader = new PropertiesFileReader();
	RemoteWebDriver driver;
	public CommonClass(RemoteWebDriver driver) {
		this.driver = driver;
		
	}
	//Object creation for Wrapper uitility class- Class created to have multiple utility method to use in framework
	//WrapperUtility util=new WrapperUtility(driver);
	public LoginPage logOut() throws InterruptedException
	{
		try {
		WebDriverWait wait = new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prreader.getPropertyvalues("mainMenuButton"))));
		driver.findElement(By.id(prreader.getPropertyvalues("mainMenuButton"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prreader.getPropertyvalues("accountMenu"))));
		driver.findElement(By.id(prreader.getPropertyvalues("accountMenu"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prreader.getPropertyvalues("logoutButton"))));
		driver.findElement(By.id(prreader.getPropertyvalues("logoutButton"))).click();
		//return new LoginPage(driver);
		}
		catch(Exception e)
		{
			System.out.println("Exception occured while logging out from the STRAX application.");
		}
		return new LoginPage(driver);
	}
	
	public List<String> getMenuAccessList()
	{   
		List<String> accessSet=null;
		try {
		WebDriverWait wait = new WebDriverWait(driver,15);
		driver.findElement(By.id(prreader.getPropertyvalues("mainMenuButton"))).click();
		List<WebElement> accessList = driver.findElements(By.xpath(prreader.getPropertyvalues("accessList")));
		accessSet = new ArrayList<String>();
		for(WebElement element:accessList )
		{
			if(element.getText().isEmpty())
			continue;
			accessSet.add(element.getText());
		}
		driver.findElement(By.id(prreader.getPropertyvalues("mainMenuButtonClose"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(prreader.getPropertyvalues("EventAddButton"))));
	}
	catch(Exception e)
	{
		System.out.println("Exception occured while getting the menu list");
	}
		return accessSet;
		
	}
	
	public boolean isAccountSettingsAccessible()
	{
		boolean state = false;
		try {
		driver.findElement(By.id(prreader.getPropertyvalues("mainMenuButton"))).click();
		driver.findElement(By.id(prreader.getPropertyvalues("accountMenu"))).click();
		state = driver.findElement(By.id(prreader.getPropertyvalues("accountSettings"))).isDisplayed();
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
		action.sendKeys(Keys.ESCAPE).build().perform();
		}
		catch(Exception e)
		{
			System.out.println("Exception occured while accessing the Account Settings menu");
		}
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
			System.out.println("Exception occured while accessing the dropdown list");
		}
		
	}
	
	public void navigateToUserNameHeader()
	{ 
		try {
	WebDriverWait wait = new WebDriverWait(driver, 10);
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
	driver.findElement(By.id(prreader.getPropertyvalues("mainMenuButton"))).click();
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prreader.getPropertyvalues("accountMenu"))));
	driver.findElement(By.id(prreader.getPropertyvalues("accountMenu"))).click();
		}
		catch(Exception e)
		{
			System.out.println("Exception occured while navigating to User Name Header");
		}
		
	}
	public void navigateToPasswordChange()
	{ 
		try {
	WebDriverWait wait = new WebDriverWait(driver, 10);
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prreader.getPropertyvalues("mainMenuChangePassword"))));
	driver.findElement(By.id(prreader.getPropertyvalues("mainMenuChangePassword"))).click();
		}
		catch(Exception e)
		{
			System.out.println("Exception occured while navigating to password change menu");
		}
	}

	public void changePassword(String newPassword) throws InterruptedException
	{ 
		try {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(prreader.getPropertyvalues("changePasswordField_1"))));
		driver.findElement(By.id(prreader.getPropertyvalues("changePasswordField_1"))).sendKeys(newPassword);
		driver.findElement(By.id(prreader.getPropertyvalues("changePasswordField_2"))).sendKeys(newPassword);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prreader.getPropertyvalues("SavePassword"))));
		driver.findElement(By.id(prreader.getPropertyvalues("SavePassword"))).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
		}
		catch(Exception e)
		{
			System.out.println("Exception occured while updating the password");
		}
	
	}
	
	public String isPasswordChangesSuccesfuly()
	{ 
		try {
	WebDriverWait wait = new WebDriverWait(driver, 10);
	
   wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(prreader.getPropertyvalues("PasswordChangeSuccessMessage"))));
	
	//util.waitForVisibilityOfAllElements(By.xpath(prreader.getPropertyvalues("PasswordChangeSuccessMessage")), 10);
		}
		catch(Exception e)
		{
			System.out.println("could not get the password change success message");
		}
	return driver.findElement(By.xpath(prreader.getPropertyvalues("PasswordChangeSuccessMessage"))).getText();
	
	}
	
	
	//Method to call common search utility
	
	public boolean searchElement(String User, By searchButtonLocator, By SearchInputBoxLocator, By SearchListLocator) throws Exception
	{
		boolean state = false;
		try {
		driver.findElement(searchButtonLocator).click();
		driver.findElement(SearchInputBoxLocator).sendKeys(User.toLowerCase());
		Thread.sleep(3000);
		List<WebElement> trList = driver.findElements(SearchListLocator);
		for (WebElement tr : trList) {

			WebElement td = tr.findElement(By.xpath("//td[1]"));
			if ((td.getText()).equals(User.toLowerCase())) {
				state = true;
			} else {
				state = false;
			}

		}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return state;
		
	}
	
	public String getPopUpMessage()
	{
		return(driver.findElement(By.xpath(prreader.getPropertyvalues("GeneralPopUpMessage"))).getText());
	}
	
	
	
}


