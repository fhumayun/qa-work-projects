package page_objects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
	
	public void addNewEvent(String incident,String caseNumber, String missionType, String stream, String address, String latitude, String longitude, String description, List<String> participants ) throws InterruptedException
	{
	
		WebDriverWait wait = new WebDriverWait(driver, 15);
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prreader.getPropertyvalues("NewEventLabel"))));
		driver.findElement(By.id(prreader.getPropertyvalues("IncidentNameTextBox"))).sendKeys(incident);
		driver.findElement(By.id(prreader.getPropertyvalues("CaseNumberTextBox"))).sendKeys(caseNumber);
		WebElement selectMissionType = driver.findElement(By.id(prreader.getPropertyvalues("MissionTypeList")));
		selectDropdownOption(selectMissionType, missionType);
		WebElement selectStream = driver.findElement(By.id(prreader.getPropertyvalues("StreamList")));
		selectDropdownOption(selectStream, stream);
		driver.findElement(By.id(prreader.getPropertyvalues("AddressTextBox"))).sendKeys(address);
		//driver.findElement(By.id(prreader.getPropertyvalues("AptSuiteUnitTextBox"))).sendKeys(aptSuiteUnit);
		//driver.findElement(By.id(prreader.getPropertyvalues("ZipCodeTextBox"))).sendKeys(String.valueOf(zipCode));
		//driver.findElement(By.id(prreader.getPropertyvalues("CityTextBox"))).sendKeys(city);
		//driver.findElement(By.id(prreader.getPropertyvalues("StateList"))).sendKeys(state);
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
		Thread.sleep(1000);
		List<WebElement> dropdownOptions = driver.findElements(By.tagName("md-option"));
		for(WebElement option: dropdownOptions)
		{
			if((option.getText()).equals(optionToSelect))
			{   
				option.click();
				Thread.sleep(1000);
				break;
				
			}
		}
		}
		catch(Exception e)
		{System.out.println("3");
			throw e;
		}
		
	}
	
	public boolean searchEvent(String incident) throws InterruptedException
	{
		boolean state = false;
		try{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
		driver.findElement(By.id(prreader.getPropertyvalues("EventSearchButton"))).click();
		driver.findElement(By.id(prreader.getPropertyvalues("EventSearchInputBox"))).sendKeys(incident.toLowerCase());
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath(prreader.getPropertyvalues("EventListTableRow")))));
		List<WebElement> trList = driver.findElements(By.xpath(prreader.getPropertyvalues("EventListTableRow")));
		

		for (WebElement tr : trList) {

			WebElement td = tr.findElement(By.xpath("//td[1]/a"));
			if ((td.getText()).equals(incident)) {
				state = true;
			} else {
				state = false;
			}

		}
		
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			driver.findElement(By.id(prreader.getPropertyvalues("EventSearchButton"))).click();
			
		}
		
		return state;
		
		
	}
	public void joinActiveEvent(String incident) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
		driver.findElement(By.id(prreader.getPropertyvalues("EventSearchButton"))).click();
		driver.findElement(By.id(prreader.getPropertyvalues("EventSearchInputBox"))).sendKeys(incident.toLowerCase());
		Thread.sleep(1000);
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
	public boolean verifyJoinEventSuccess() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		//boolean state = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prreader.getPropertyvalues("ConfigMapButton")))).isDisplayed();
		Thread.sleep(1000);
		boolean state = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("GoBackFromMap")))).isDisplayed();

		if(state)
		{
			return true;
		}
		else
			return false; 
				
	}
	public boolean verifyEvenPlaybackSuccess() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		//boolean state = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prreader.getPropertyvalues("ConfigMapButton")))).isDisplayed();
		Thread.sleep(1000);
		boolean state = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("GoBackFromMap-Playback")))).isDisplayed();

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
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
		driver.findElement(By.xpath(prreader.getPropertyvalues("GoBackFromMap"))).click();
		
        
	}
	public void closeMapFromPlayback()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prreader.getPropertyvalues("GoBackFromMap-Playback"))));
		driver.findElement(By.xpath(prreader.getPropertyvalues("GoBackFromMap-Playback"))).click();
		
        
	}
	public void endActiveEvent() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, 15);
		driver.findElement(By.xpath(prreader.getPropertyvalues("ConfigMapButtonNew"))).click();
		driver.findElement(By.xpath(prreader.getPropertyvalues("EndEventTab"))).click();
		Thread.sleep(2000);
		driver.findElement(By.id(prreader.getPropertyvalues("EndEventButton"))).click();
		
	}
	public void navigateToEventHistory()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prreader.getPropertyvalues("EventHistoryTab"))));
		driver.findElement(By.xpath(prreader.getPropertyvalues("EventHistoryTab"))).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
		
	}
	public void navigateToEventPlanTab() throws InterruptedException
	{
		try{
		
		driver.findElement(By.xpath(prreader.getPropertyvalues("EventPlanTab"))).click();
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			
			driver.findElement(By.xpath(prreader.getPropertyvalues("EventPlanTab"))).click();
		}
		
	}
	public void navigateToCreateNewEventPlan()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prreader.getPropertyvalues("AddNewEventPlanButton"))));
		driver.findElement(By.id(prreader.getPropertyvalues("AddNewEventPlanButton"))).click();
	}
	public String deleteEventFromDB(String incident)
	{
		DatabaseConnection conn = new DatabaseConnection();
		String deletedEvent = conn.deleteEvent(incident);
		
		return deletedEvent;
		
	}
	public String deleteEventPlanFromDB(String eventPlan)
	{
		DatabaseConnection conn = new DatabaseConnection();
		String deletedEventPlan = conn.deleteEventPlan(eventPlan);
		
		return deletedEventPlan;
		
	}
	
	public void addEventPlan(String incident, String missionType, String address, String latitude, String longitude, String description ) throws InterruptedException
	{
	
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prreader.getPropertyvalues("NewEventPlanLabel"))));
		driver.findElement(By.id(prreader.getPropertyvalues("EventPlanNameTextBox"))).sendKeys(incident);
		WebElement selectMissionType = driver.findElement(By.id(prreader.getPropertyvalues("EventPlanTypeList")));
		selectDropdownOption(selectMissionType, missionType);
		driver.findElement(By.id(prreader.getPropertyvalues("EventPlanAddressTextBox"))).sendKeys(address);
		driver.findElement(By.id(prreader.getPropertyvalues("EventPlanLatitudeTextBox"))).sendKeys(latitude);
		driver.findElement(By.id(prreader.getPropertyvalues("EventPlanLongitudeTextBox"))).sendKeys(longitude);
		driver.findElement(By.id(prreader.getPropertyvalues("EventPlanDescriptionTextBox"))).sendKeys(description);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(prreader.getPropertyvalues("EventPlanSaveButton")))));
		driver.findElement(By.id(prreader.getPropertyvalues("EventPlanSaveButton"))).click();
	}
	public boolean searchEventPlan(String incident) throws InterruptedException
	{
		boolean state = false;
		try{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
		driver.findElement(By.id(prreader.getPropertyvalues("EventPlanSearchButton"))).click();
		driver.findElement(By.id(prreader.getPropertyvalues("EventPlanSearchInputTextBox"))).sendKeys(incident);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath(prreader.getPropertyvalues("EventListTableRow")))));
		List<WebElement> trList = driver.findElements(By.xpath(prreader.getPropertyvalues("EventListTableRow")));
		

		for (WebElement tr : trList) {

			WebElement td = tr.findElement(By.xpath("//td[1]/a"));
			if ((td.getText()).equals(incident)) {
				state = true;
			} else {
				state = false;
			}

		}
		
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			driver.findElement(By.id(prreader.getPropertyvalues("EventSearchButton"))).click();
			
		}
		
		return state;
		
		
	}
	
	public void lockEventPlan(String eventPlan)
	{
		driver.findElement(By.xpath(prreader.getPropertyvalues("EventPlanLockButton"))).click();
	}
	public void unlockEventPlan(String eventPlan)
	{
		driver.findElement(By.xpath(prreader.getPropertyvalues("EventPlanUnlockButton"))).click();
	}
	public String getEventLockStatus(String eventPlan) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
		driver.navigate().refresh();
		searchEventPlan(eventPlan);
		String status = driver.findElement(By.xpath(prreader.getPropertyvalues("EventPlanUnlockButton"))).getText();
		return status;
		
	}
	public String getEventUnlockStatus(String eventPlan) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
		driver.navigate().refresh();
		searchEventPlan(eventPlan);
		String status = driver.findElement(By.xpath(prreader.getPropertyvalues("EventPlanLockButton"))).getText();
		return status;
		
	}
	
	public void navigateToEditEventPlan()
	{
		driver.findElement(By.xpath(prreader.getPropertyvalues("EventPlanEditButton"))).click();
	}
	public String getEventPlanErrorMessage()
	{
		String ErrorMessage = driver.findElement(By.xpath(prreader.getPropertyvalues("EventplanLockedErrorMessage"))).getText();
		return ErrorMessage;
		
	}

	public void archiveEventPlan(String eventPlan) throws InterruptedException {
	
			driver.findElement(By.xpath(prreader.getPropertyvalues("EventPlanArchiveButton"))).click();
			driver.findElement(By.id(prreader.getPropertyvalues("EventPlanArchiveConfirmButton"))).click();
			Thread.sleep(1000);
			driver.navigate().refresh();
		
	}
	
	public void navigateToArchivedEventPlanTab()
	{
		driver.findElement(By.id(prreader.getPropertyvalues("ShowArchivedEventPlanButton"))).click();
		
	}
	public void unArchiveEventPlan(String eventPlan) throws InterruptedException {
		
		driver.findElement(By.xpath(prreader.getPropertyvalues("EventPlanUnarchiveButton"))).click();
		driver.findElement(By.id(prreader.getPropertyvalues("EventPlanArchiveConfirmButton"))).click();
		Thread.sleep(1000);
		
	
}
	public void shareEventPlan(String eventPlan) {
		
		driver.findElement(By.xpath(prreader.getPropertyvalues("EventPlanShareButton"))).click();
		
	
}
	public boolean isEventPlanShared()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
		String attributeValue = driver.findElement(By.xpath(prreader.getPropertyvalues("EventPlanShareButton"))).getAttribute("class");
		if(attributeValue.equals(prreader.getPropertyvalues("EventPlanSharedBlueClassName")))
		{
			return true;
		}
		else
			
		return false;
		
	}
	public boolean isGoogleMapLoaded()
	{
		boolean mapState = driver.findElement(By.xpath(prreader.getPropertyvalues("EventGoogleMapImage"))).isDisplayed();
		closeMap();
		return mapState;
	}
	public boolean isEventLogWindowLoaded()
	{
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath(prreader.getPropertyvalues("EventLogWindowButton")))).build().perform();
		boolean eventLogWindowState = driver.findElement(By.xpath(prreader.getPropertyvalues("EventLogWindow"))).isDisplayed();
		return eventLogWindowState;
	}
	public boolean isVideoFeedAvailable()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
		if(driver.findElement(By.id(prreader.getPropertyvalues("EventFeedVideoStatusGreen"))).isDisplayed())
		{
			return true;
		}
			return false;
	
	}
	public void rightClickOnEventAndOpenInNewWindow(String incident)
	{
		Actions action = new Actions(driver);
		action.contextClick().sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).build().perform();
		
	}
	public void rightClickActiveEvent(String incident) throws InterruptedException, AWTException
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
		driver.findElement(By.id(prreader.getPropertyvalues("EventSearchButton"))).click();
		driver.findElement(By.id(prreader.getPropertyvalues("EventSearchInputBox"))).sendKeys(incident.toLowerCase());
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath(prreader.getPropertyvalues("EventListTableRow")))));
		List<WebElement> trList = driver.findElements(By.xpath(prreader.getPropertyvalues("EventListTableRow")));
		for (WebElement tr : trList) {
			WebElement td = tr.findElement(By.xpath("//td[1]/a"));
			if ((td.getText()).equals(incident))
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.SHIFT).click(td).keyUp(Keys.SHIFT).build().perform();
				
			}
				
				


		}
		
		
	}
	public void playBackPlayAndPause()
	{
		driver.findElement(By.xpath(prreader.getPropertyvalues("PlaybackPlayPauseButton"))).click();
	}
	public boolean isPlayBackPlaying()
	{
		WebElement playBackButton = driver.findElement(By.xpath(prreader.getPropertyvalues("PlaybackPlayPauseButton")));
		if(playBackButton.getAttribute("aria-label").equals("pause"))
		{
			return true;
		}
		else
			return false;
		
	}
	public void addUpdateEventCaseNumber(String caseNumber)
	{
		WebElement caseNumberField = driver.findElement(By.xpath(prreader.getPropertyvalues("EventCaseNumber")));
		caseNumberField.clear();
		caseNumberField.sendKeys(caseNumber);
		caseNumberField.sendKeys(Keys.ENTER);
		
	}


}
