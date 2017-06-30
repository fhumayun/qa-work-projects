package page_objects;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.DatabaseConnection;
import utils.PropertiesFileReader;

public class UsersPage {

	RemoteWebDriver driver;

	public UsersPage(RemoteWebDriver driver) {
		this.driver = driver;

	}

	static PropertiesFileReader prreader = new PropertiesFileReader();

	public void addUser(String email, String fName, String lName, String userpassword, String role, String color,
			String device) throws InterruptedException {
		try{
		driver.findElement(By.id(prreader.getPropertyvalues("participantLoginIDTxtBox"))).sendKeys(email);
		driver.findElement(By.id(prreader.getPropertyvalues("participantFirstNameTxtBox"))).sendKeys(fName);
		driver.findElement(By.id(prreader.getPropertyvalues("participantLastNameTxtBox"))).sendKeys(lName);
		driver.findElement(By.id(prreader.getPropertyvalues("participantPasswordTxtBox"))).sendKeys(userpassword);
		driver.findElement(By.id(prreader.getPropertyvalues("participantConfirmPasswordTxtBox"))).sendKeys(userpassword);
		WebElement selectRole = driver.findElement(By.id(prreader.getPropertyvalues("participantRoleList")));
		WebElement selectColor = driver.findElement(By.id(prreader.getPropertyvalues("participantColorList")));
		WebElement selectDevice = driver.findElement(By.id(prreader.getPropertyvalues("participantDeviceList")));
		selectDropdownOption(selectRole, role);
		selectDropdownOption(selectColor, color);
		//selectDropdownOption(selectDevice, device);
		driver.findElement(By.id(prreader.getPropertyvalues("participantSaveButton"))).click();
		}
		catch(Exception e)
		{
			throw e;
		}
	}

	public void updateUser(String email, String fName, String lName,String role, String color,
			String device) throws InterruptedException {
		driver.findElement(By.id(prreader.getPropertyvalues("participantSearchButton"))).click();
		driver.findElement(By.id(prreader.getPropertyvalues("participantSearchInput"))).sendKeys(email);
		Thread.sleep(3000);
		List<WebElement> trList = driver.findElements(By.xpath("//tr[@class='md-row ng-scope']"));
		for (WebElement tr : trList) {
		WebElement td = tr.findElement(By.xpath("//td[1]"));
			if ((td.getText()).equals(email)) {
				tr.findElement(By.xpath("//td//*[contains(@id, 'participant__link--edit')]")).click();
			} 

		}
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prreader.getPropertyvalues("participantLoginIDTxtBox"))));
		Thread.sleep(1000);
		driver.findElement(By.id(prreader.getPropertyvalues("participantLoginIDTxtBox"))).clear();
		
		driver.findElement(By.id(prreader.getPropertyvalues("participantLoginIDTxtBox"))).sendKeys(email);
		driver.findElement(By.id(prreader.getPropertyvalues("participantFirstNameTxtBox"))).clear();
		driver.findElement(By.id(prreader.getPropertyvalues("participantFirstNameTxtBox"))).sendKeys(fName);
		driver.findElement(By.id(prreader.getPropertyvalues("participantLastNameTxtBox"))).clear();
		driver.findElement(By.id(prreader.getPropertyvalues("participantLastNameTxtBox"))).sendKeys(lName);
		WebElement selectRole = driver.findElement(By.id(prreader.getPropertyvalues("participantRoleList")));
		WebElement selectColor = driver.findElement(By.id(prreader.getPropertyvalues("participantColorList")));
		WebElement selectDevice = driver.findElement(By.id(prreader.getPropertyvalues("participantDeviceList")));
		selectDropdownOption(selectRole, role);
		selectDropdownOption(selectColor, color);
		selectDropdownOption(selectDevice, device);
		driver.findElement(By.id(prreader.getPropertyvalues("participantSaveButton"))).click();

	}

	public boolean searchUser(String User) throws InterruptedException {
		driver.findElement(By.id(prreader.getPropertyvalues("participantSearchButton"))).click();
		driver.findElement(By.id(prreader.getPropertyvalues("participantSearchInput"))).sendKeys(User);
		Thread.sleep(3000);
		List<WebElement> trList = driver.findElements(By.xpath("//tr[@class='md-row ng-scope']"));
		boolean state = false;

		for (WebElement tr : trList) {

			WebElement td = tr.findElement(By.xpath("//td[1]"));
			if ((td.getText()).equals(User)) {
				state = true;
			} else {
				state = false;
			}

		}
		return state;

	}
	
	public void navigateToAddEditUserPage()
	{
		driver.findElement(By.id(prreader.getPropertyvalues("participantAddButton"))).click();
		
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
	public void archiveUser(String user) throws InterruptedException
	{

			WebDriverWait wait = new WebDriverWait(driver, 10);
			driver.findElement(By.id(prreader.getPropertyvalues("participantSearchButton"))).click();
			driver.findElement(By.id(prreader.getPropertyvalues("participantSearchInput"))).sendKeys(user);
			Thread.sleep(3000);
			List<WebElement> trList = driver.findElements(By.xpath("//tr[@class='md-row ng-scope']"));
			for (WebElement tr : trList) {
			WebElement td = tr.findElement(By.xpath("//td[1]"));
				if ((td.getText()).equals(user)) {
					tr.findElement(By.xpath("//td//*[contains(@id, 'participant__link--archive')]")).click();
					wait.until(ExpectedConditions.elementToBeClickable(By.id("confirm-dialog__button--save"))).click();
					Thread.sleep(2000);
				} 

			}

			driver.navigate().refresh();
			wait.until(ExpectedConditions.elementToBeClickable(By.id(prreader.getPropertyvalues("participantSearchButton"))));
			
	}
	
	public boolean isUserCreatedSuccessfully()
	{
		String expected = "User saved!";
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='md-toast-text ng-binding']")));
		String actual = driver.findElement(By.xpath("//span[@class='md-toast-text ng-binding']")).getText();
		if(actual.equals(expected))
		{
			return true;
		}
		else		
		return false;
		
	}
	public boolean isUserUpdatedSuccessfully()
	{
		String expected = "User updated!";
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='md-toast-text ng-binding']")));
		String actual = driver.findElement(By.xpath("//span[@class='md-toast-text ng-binding']")).getText();
		if(actual.equals(expected))
		{
			return true;
		}
		else		
		return false;
		
	}
	
	

}
