package page_objects;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.DatabaseConnection;
import utils.PropertiesFileReader;

public class EventPage extends BaseClass {

	RemoteWebDriver driver;
	static PropertiesFileReader prreader = new PropertiesFileReader();
	CommonClass cClass = new CommonClass(driver);
	public EventPage(RemoteWebDriver driver) {
		this.driver = driver;

	}
	
	public void addNewEvent(String incident, String missionType, String stream, String address,String aptSuiteUnit,
			String zipCode, String city, String state, String latitude, String longitude, String description, List<String> participants ) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prreader.getPropertyvalues("NewEventLabel"))));
		driver.findElement(By.id(prreader.getPropertyvalues("IncidentNameTextBox"))).sendKeys(incident);
		WebElement selectMissionType = driver.findElement(By.id(prreader.getPropertyvalues("MissionTypeList")));
		selectDropdownOption(selectMissionType, missionType);
		WebElement selectStream = driver.findElement(By.id(prreader.getPropertyvalues("StreamList")));
		selectDropdownOption(selectStream, stream);
		driver.findElement(By.id(prreader.getPropertyvalues("AddressTextBox"))).sendKeys(address);
		driver.findElement(By.id(prreader.getPropertyvalues("AptSuiteUnitTextBox"))).sendKeys(aptSuiteUnit);
		driver.findElement(By.id(prreader.getPropertyvalues("ZipCodeTextBox"))).sendKeys(String.valueOf(zipCode));
		driver.findElement(By.id(prreader.getPropertyvalues("CityTextBox"))).sendKeys(city);
		driver.findElement(By.id(prreader.getPropertyvalues("StateList"))).sendKeys(state);
		driver.findElement(By.id(prreader.getPropertyvalues("LatitudeTextBox"))).sendKeys(latitude);
		driver.findElement(By.id(prreader.getPropertyvalues("LongitudeTextBox"))).sendKeys(longitude);
		driver.findElement(By.id(prreader.getPropertyvalues("DescriptionTextBox"))).sendKeys(description);
		int count=0;
		for(String participant : participants)
		{
			count++;
			Pattern whitespace = Pattern.compile("\\s");
			Matcher matcher = whitespace.matcher(participant);
			String participantFullName = matcher.replaceAll(", ");
			if(count==1)
			{
					driver.findElement(By.xpath(prreader.getPropertyvalues("SearchParticipantsButton"))).click();
			}
			
			driver.findElement(By.id(prreader.getPropertyvalues("SearchParticipantsTextBox"))).clear();
			driver.findElement(By.id(prreader.getPropertyvalues("SearchParticipantsTextBox"))).sendKeys(getNameToSearch(participant));
			Thread.sleep(1000);
			List<WebElement> participantLinks =driver.findElements(By.xpath(prreader.getPropertyvalues("participantList")));
			for(WebElement e:participantLinks)
			{
				
				String name = e.getText();
				String[] array1 = name.split("\n");
				String result = array1[1];
				if((result).equals(participantFullName))
				{
				e.click();
				Thread.sleep(1000);
				break;
				}
			}
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prreader.getPropertyvalues("EventSaveButton"))));
		driver.findElement(By.id(prreader.getPropertyvalues("EventSaveButton"))).click();

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
	public void navigateToCreateNewEvent()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prreader.getPropertyvalues("EventAddButton"))));
		driver.findElement(By.id(prreader.getPropertyvalues("EventAddButton"))).click();
	}
	public boolean getErrorMessage()
	{
		if(driver.findElement(By.xpath(prreader.getPropertyvalues("EventCreationError"))).isDisplayed())
		{
			return true;
		}
		else
		return false;
		
	}
	public String getNameToSearch(String participant)
	{
		String participantFirstName="", participantLastName="";
		String[] names = participant.split(" ");
		participantFirstName = names[0];
		participantLastName = names[1];
		return participantFirstName;
		
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
				Thread.sleep(1000);
				break;
			}
			//Thread.sleep(1000);
		}
		}
		catch(Exception e)
		{
			throw e;
		}
		
	}
	
	public boolean searchEvent(String incident) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
		driver.findElement(By.id(prreader.getPropertyvalues("EventSearchButton"))).click();
		driver.findElement(By.id(prreader.getPropertyvalues("EventSearchInputBox"))).sendKeys(incident);
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath(prreader.getPropertyvalues("EventListTableRow")))));
		List<WebElement> trList = driver.findElements(By.xpath(prreader.getPropertyvalues("EventListTableRow")));
		boolean state = false;

		for (WebElement tr : trList) {

			WebElement td = tr.findElement(By.xpath("//td[1]/a"));
			if ((td.getText()).equals(incident)) {
				state = true;
			} else {
				state = false;
			}

		}
		return state;
		
	}
	public void joinActiveEvent(String incident)
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
		driver.findElement(By.id(prreader.getPropertyvalues("EventSearchButton"))).click();
		driver.findElement(By.id(prreader.getPropertyvalues("EventSearchInputBox"))).sendKeys(incident);
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath(prreader.getPropertyvalues("EventListTableRow")))));
		List<WebElement> trList = driver.findElements(By.xpath(prreader.getPropertyvalues("EventListTableRow")));
		for (WebElement tr : trList) {
			WebElement td = tr.findElement(By.xpath("//td[1]/a"));
			if ((td.getText()).equals(incident)) {
				td.click();
				break;
			} 

		}
		
		
	}
	public boolean verifyJoinEventSuccess()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		boolean state = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prreader.getPropertyvalues("ConfigMapButton")))).isDisplayed();
		if(state)
		{
			return true;
		}
		else
			return false; 
				
	}
	public void closeMap()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prreader.getPropertyvalues("MapCloseButton")))).click();
	}
	public void endActiveEvent()
	{
		driver.findElement(By.id(prreader.getPropertyvalues("ConfigMapButton"))).click();
		driver.findElement(By.xpath(prreader.getPropertyvalues("EndEventTab"))).click();
		driver.findElement(By.id(prreader.getPropertyvalues("EndEventButton"))).click();
		
		
	}
	public void navigateToEventHistory()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
		driver.findElement(By.xpath(prreader.getPropertyvalues("EventHistoryTab"))).click();
	}
	public String deleteEventFromDB(String incident)
	{
		DatabaseConnection conn = new DatabaseConnection();
		String deletedEvent = conn.deleteEvent(incident);
		
		return deletedEvent;
		
	}
	
	
}
