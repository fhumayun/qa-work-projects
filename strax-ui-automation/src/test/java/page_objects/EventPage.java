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

	public void addNewEvent(String incident, String caseNumber, String missionType, String stream, String address,
			String latitude, String longitude, String description, List<String> participants)
			throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(prreader.getPropertyvalues("NewEventLabel"))));
			driver.findElement(By.xpath(prreader.getPropertyvalues("IncidentNameTextBox"))).sendKeys(incident);
			driver.findElement(By.id(prreader.getPropertyvalues("CaseNumberTextBox"))).sendKeys(caseNumber);
			WebElement selectMissionType = driver.findElement(By.id(prreader.getPropertyvalues("MissionTypeList")));
			selectDropdownOption(selectMissionType, missionType);
			WebElement selectStream = driver.findElement(By.id(prreader.getPropertyvalues("StreamList")));
			selectDropdownOption(selectStream, stream);
			driver.findElement(By.id(prreader.getPropertyvalues("AddressTextBox"))).sendKeys(address);
			//driver.findElement(By.id(prreader.getPropertyvalues("LatitudeTextBox"))).sendKeys(latitude);
			//driver.findElement(By.id(prreader.getPropertyvalues("LongitudeTextBox"))).sendKeys(longitude);
			driver.findElement(By.id(prreader.getPropertyvalues("DescriptionTextBox"))).sendKeys(description);
			int count = 0;
			for (String participant : participants) {
				count++;
				Pattern whitespace = Pattern.compile("\\s");
				Matcher matcher = whitespace.matcher(participant);
				String participantFullName = matcher.replaceAll(", ");
				if (count == 1) {
					driver.findElement(By.xpath(prreader.getPropertyvalues("SearchParticipantsButton"))).click();
				}

				driver.findElement(By.id(prreader.getPropertyvalues("SearchParticipantsTextBox"))).clear();
				driver.findElement(By.id(prreader.getPropertyvalues("SearchParticipantsTextBox")))
						.sendKeys(getNameToSearch(participant));
				Thread.sleep(1000);
				List<WebElement> participantLinks = driver
						.findElements(By.xpath(prreader.getPropertyvalues("participantList")));
				for (WebElement e : participantLinks) {

					String name = e.getText();
					String[] array1 = name.split("\n");
					String result = array1[1];
					if ((result).equals(participantFullName)) {
						e.click();
						Thread.sleep(1000);
						break;
					}
				}

			}
			wait.until(ExpectedConditions.elementToBeClickable(By.id(prreader.getPropertyvalues("EventSaveButton"))));
			driver.findElement(By.id(prreader.getPropertyvalues("EventSaveButton"))).click();
		} catch (Exception e) {
			System.out.println("Exception while creating a new event");
		}
	}
	
	////this method is temporary, this needs refactoring..
	public void addNewEventFromPrePlan(String incident, String caseNumber, String missionType, String stream, String address,
			String latitude, String longitude, String description, List<String> participants)
			throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(prreader.getPropertyvalues("NewEventLabel"))));
			driver.findElement(By.id(prreader.getPropertyvalues("IncidentNameTextBoxFromPrePlan"))).sendKeys(incident);
			driver.findElement(By.id(prreader.getPropertyvalues("IncidentNameTextBoxFromPrePlan"))).sendKeys(Keys.ENTER);
			//driver.findElement(By.id(prreader.getPropertyvalues("CaseNumberTextBox"))).sendKeys(caseNumber);
			WebElement selectMissionType = driver.findElement(By.id(prreader.getPropertyvalues("MissionTypeList")));
			selectDropdownOption(selectMissionType, missionType);
			WebElement selectStream = driver.findElement(By.id(prreader.getPropertyvalues("StreamList")));
			selectDropdownOption(selectStream, stream);
			driver.findElement(By.id(prreader.getPropertyvalues("AddressTextBox"))).sendKeys(address);
			//driver.findElement(By.id(prreader.getPropertyvalues("LatitudeTextBox"))).sendKeys(latitude);
			//driver.findElement(By.id(prreader.getPropertyvalues("LongitudeTextBox"))).sendKeys(longitude);
			driver.findElement(By.id(prreader.getPropertyvalues("DescriptionTextBox"))).sendKeys(description);
			int count = 0;
			for (String participant : participants) {
				count++;
				Pattern whitespace = Pattern.compile("\\s");
				Matcher matcher = whitespace.matcher(participant);
				String participantFullName = matcher.replaceAll(", ");
				if (count == 1) {
					driver.findElement(By.xpath(prreader.getPropertyvalues("SearchParticipantsButton"))).click();
				}

				driver.findElement(By.id(prreader.getPropertyvalues("SearchParticipantsTextBox"))).clear();
				driver.findElement(By.id(prreader.getPropertyvalues("SearchParticipantsTextBox")))
						.sendKeys(getNameToSearch(participant));
				Thread.sleep(1000);
				List<WebElement> participantLinks = driver
						.findElements(By.xpath(prreader.getPropertyvalues("participantList")));
				for (WebElement e : participantLinks) {

					String name = e.getText();
					String[] array1 = name.split("\n");
					String result = array1[1];
					if ((result).equals(participantFullName)) {
						e.click();
						Thread.sleep(1000);
						break;
					}
				}

			}
			wait.until(ExpectedConditions.elementToBeClickable(By.id(prreader.getPropertyvalues("EventSaveButton"))));
			driver.findElement(By.id(prreader.getPropertyvalues("EventSaveButton"))).click();
		} catch (Exception e) {
			System.out.println("Exception while creating a new event"+e);
		}
	}

	public boolean isAddEventButtonPresent() {
		boolean state = false;
		try {
			if (driver.findElement(By.id(prreader.getPropertyvalues("EventAddButton"))).isDisplayed()) {
				state = true;
			} else

				state = false;
		} catch (Exception e) {
			System.out.println("Exception while finding the event add button");
		}
		return state;

	}

	public void navigateToCreateNewEvent() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(By.id(prreader.getPropertyvalues("EventAddButton"))));
			driver.findElement(By.id(prreader.getPropertyvalues("EventAddButton"))).click();
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
		} catch (Exception e) {
			System.out.println("Exception while navigating to create new event page");
		}

	}

	public boolean getErrorMessage() {
		boolean state = false;
		try {
			if ((driver.findElement(By.xpath(prreader.getPropertyvalues("ConfirmLeaveEventMsg"))).getText()).equals("Event creator is already assigned to an event. Do you want to leave other event?")) {
				state = true;
				driver.findElement(By.id(prreader.getPropertyvalues("DialogClose"))).click();
			} else
				state = false;
		} catch (Exception e) {
			System.out.println("Exception while getting event creation error message");
		}
		return state;

	}

	public String getNameToSearch(String participant) {
		String participantFirstName = "", participantLastName = "";
		String[] names = participant.split(" ");
		participantFirstName = names[0];
		participantLastName = names[1];
		return participantFirstName;

	}

	public void selectDropdownOption(WebElement dropdown, String optionToSelect) throws InterruptedException {
		try {
			dropdown.click();
			Thread.sleep(1000);
			List<WebElement> dropdownOptions = driver.findElements(By.tagName("md-option"));
			for (WebElement option : dropdownOptions) {
				if ((option.getText()).equals(optionToSelect)) {
					option.click();
					Thread.sleep(1000);
					break;

				}
			}
		} catch (Exception e) {
			System.out.println("3");
			throw e;
		}

	}

	public boolean searchEvent(String incident) throws InterruptedException {
		boolean state = false;
		try {	
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
			driver.findElement(By.id(prreader.getPropertyvalues("EventSearchButton"))).click();
			driver.findElement(By.id(prreader.getPropertyvalues("EventSearchInputBox")))
					.sendKeys(incident.toLowerCase());
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOfAllElements(
					driver.findElements(By.xpath(prreader.getPropertyvalues("EventListTableRow")))));
			List<WebElement> trList = driver.findElements(By.xpath(prreader.getPropertyvalues("EventListTableRow")));

			for (WebElement tr : trList) {

				WebElement td = tr.findElement(By.xpath("//td[1]/a"));
				if ((td.getText()).equals(incident)) {
					state = true;
				} else {
					state = false;
				}

			}

		} catch (Exception e) {
			return false;
		}

		return state;

	}

	public void joinActiveEvent(String incident) throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
			driver.findElement(By.id(prreader.getPropertyvalues("EventSearchButton"))).click();
			driver.findElement(By.id(prreader.getPropertyvalues("EventSearchInputBox")))
					.sendKeys(incident.toLowerCase());
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOfAllElements(
					driver.findElements(By.xpath(prreader.getPropertyvalues("EventListTableRow")))));
			List<WebElement> trList = driver.findElements(By.xpath(prreader.getPropertyvalues("EventListTableRow")));
			for (WebElement tr : trList) {
				WebElement td = tr.findElement(By.xpath("//td[1]/a"));
				if ((td.getText()).equals(incident)) {
					td.click();
					break;
				}

			}
		} catch (Exception e) {
			System.out.println("Exception while joining the event");
		}

	}

	public boolean verifyJoinEventSuccess() throws InterruptedException {
		boolean state = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			Thread.sleep(1000);
			state = wait
					.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("LeaveEvent"))))
					.isDisplayed();

			if (state) {
				state = true;
			} else
				state = false;
		} catch (Exception e) {
			System.out.println("Exception while exiting from SAC");
		}

		return state;

	}

	public boolean verifyEvenPlaybackSuccess() throws InterruptedException {
		boolean state = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			Thread.sleep(1000);
			state = wait
					.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("GoBackFromMap-Playback"))))
					.isDisplayed();

			if (state) {
				state = true;
			} else
				state = false;
		} catch (Exception e) {
			System.out.println("Exception in event playback");
		}

		return state;

	}
	
	public void closeMap() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
			driver.findElement(By.xpath(prreader.getPropertyvalues("SACMapCloseButton"))).click();
		    } catch (Exception e) {
			System.out.println("Exception while closing the map from SAC");
		}

	}

	public void leaveEvent() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
			driver.findElement(By.xpath(prreader.getPropertyvalues("GoBackFromMap"))).click();
			driver.findElement(By.id(prreader.getPropertyvalues("ConfirmMapCloseDialog"))).click();
		} catch (Exception e) {
			System.out.println("Exception while leaving the event");
		}

	}

	public void closeMapFromPlayback() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(prreader.getPropertyvalues("GoBackFromMap-Playback"))));
			driver.findElement(By.xpath(prreader.getPropertyvalues("GoBackFromMap-Playback"))).click();
		} catch (Exception e) {
			System.out.println("Exception while closing the map from playback");
		}

	}

	public void endActiveEvent() throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			driver.findElement(By.xpath(prreader.getPropertyvalues("ConfigMapButtonNew"))).click();
			driver.findElement(By.xpath(prreader.getPropertyvalues("EndEventTab"))).click();
			Thread.sleep(2000);
			driver.findElement(By.id(prreader.getPropertyvalues("EndEventButton"))).click();
		} catch (Exception e) {
			System.out.println("Exception while ending the event");
		}
	}
	public void navigateToEventMapLayerSettings() throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			driver.findElement(By.xpath(prreader.getPropertyvalues("ConfigMapButtonNew"))).click();
		} catch (Exception e) {
			System.out.println("Exception while navigating to map layer tab");
		}
	}
	
	public void selectMapLayer(String mapLayer)
	{
		try {
			List<WebElement> layerList =  driver.findElements(By.xpath(prreader.getPropertyvalues("KLVMapLayerList")));
			for(WebElement layer : layerList)
			{
				if((layer.getText()).equals(mapLayer))
				{
					layer.click();
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Exception while selecting map layer");
		}
	}
	public void closeSACSettings()
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			driver.findElement(By.id(prreader.getPropertyvalues("SACSettingsClose"))).click();
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
		} catch (Exception e) {
			System.out.println("Exception while closing to SAC settings");
		}
	}

	public void navigateToEventHistory() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(prreader.getPropertyvalues("EventHistoryTab"))));
			driver.findElement(By.xpath(prreader.getPropertyvalues("EventHistoryTab"))).click();
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
		} catch (Exception e) {
			System.out.println("Exception while navigating to the event history page");
		}
	}

	public void navigateToEventPlanTab() throws InterruptedException {
		try {

			driver.findElement(By.xpath(prreader.getPropertyvalues("EventPlanTab"))).click();
		} catch (org.openqa.selenium.StaleElementReferenceException ex) {

			driver.findElement(By.xpath(prreader.getPropertyvalues("EventPlanTab"))).click();
		}

	}

	public void navigateToCreateNewEventPlan() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions
					.elementToBeClickable(By.id(prreader.getPropertyvalues("AddNewEventPlanButton"))));
			driver.findElement(By.id(prreader.getPropertyvalues("AddNewEventPlanButton"))).click();
		} catch (Exception e) {
			System.out.println("Exception while navigating to event plan page");
		}
	}

	public String deleteEventFromDB(String incident) {
		String deletedEvent = "";
		try {
			DatabaseConnection conn = new DatabaseConnection();
			deletedEvent = conn.deleteEvent(incident);
		}

		catch (Exception e) {
			System.out.println("Exception while communicating to MongoDB");
		}
		return deletedEvent;

	}

	public String deleteEventPlanFromDB(String eventPlan) {
		String deletedEventPlan = "";
		try {
			DatabaseConnection conn = new DatabaseConnection();
			deletedEventPlan = conn.deleteEventPlan(eventPlan);
		} catch (Exception e) {
			System.out.println("Exception while communicating to MongoDB");
		}
		return deletedEventPlan;

	}

	public void addEventPlan(String incident, String missionType, String address, String latitude, String longitude,
			String description) throws InterruptedException {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
			wait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath(prreader.getPropertyvalues("NewEventPlanLabel"))));
			driver.findElement(By.id(prreader.getPropertyvalues("EventPlanNameTextBox"))).sendKeys(incident);
			WebElement selectMissionType = driver.findElement(By.id(prreader.getPropertyvalues("EventPlanTypeList")));
			selectDropdownOption(selectMissionType, missionType);
			driver.findElement(By.id(prreader.getPropertyvalues("EventPlanAddressTextBox"))).sendKeys(address);
			//driver.findElement(By.id(prreader.getPropertyvalues("EventPlanLatitudeTextBox"))).sendKeys(latitude);
			//driver.findElement(By.id(prreader.getPropertyvalues("EventPlanLongitudeTextBox"))).sendKeys(longitude);
			driver.findElement(By.id(prreader.getPropertyvalues("EventPlanDescriptionTextBox"))).sendKeys(description);
			wait.until(ExpectedConditions
					.visibilityOf(driver.findElement(By.id(prreader.getPropertyvalues("EventPlanSaveButton")))));
			driver.findElement(By.id(prreader.getPropertyvalues("EventPlanSaveButton"))).click();
		} catch (Exception e) {
			System.out.println("Exception while creating a new event plan");
		}
	}

	public boolean searchEventPlan(String eventPlan) throws InterruptedException {
		boolean state = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
			driver.findElement(By.id(prreader.getPropertyvalues("EventPlanSearchButton"))).click();
			driver.findElement(By.id(prreader.getPropertyvalues("EventPlanSearchInputTextBox"))).sendKeys(eventPlan);
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOfAllElements(
					driver.findElements(By.xpath(prreader.getPropertyvalues("EventListTableRow")))));
			List<WebElement> trList = driver.findElements(By.xpath(prreader.getPropertyvalues("EventListTableRow")));

			for (WebElement tr : trList) {

				WebElement td = tr.findElement(By.xpath("//td[1]/a"));
				if ((td.getText()).equals(eventPlan)) {
					state = true;
				} else {
					state = false;
				}

			}

		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			driver.findElement(By.id(prreader.getPropertyvalues("EventSearchButton"))).click();

		}

		return state;

	}

	public void lockEventPlan(String eventPlan) {
		driver.findElement(By.xpath(prreader.getPropertyvalues("EventPlanLockButton"))).click();
	}

	public void unlockEventPlan(String eventPlan) {
		driver.findElement(By.xpath(prreader.getPropertyvalues("EventPlanUnlockButton"))).click();
	}

	public String getEventLockStatus(String eventPlan) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
		driver.navigate().refresh();
		searchEventPlan(eventPlan);
		String status = driver.findElement(By.xpath(prreader.getPropertyvalues("EventPlanUnlockButton"))).getText();
		return status;

	}

	public String getEventUnlockStatus(String eventPlan) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
		driver.navigate().refresh();
		searchEventPlan(eventPlan);
		String status = driver.findElement(By.xpath(prreader.getPropertyvalues("EventPlanLockButton"))).getText();
		return status;

	}

	public void navigateToEditEventPlan() {
		try {
			driver.findElement(By.xpath(prreader.getPropertyvalues("EventPlanEditButton"))).click();
		} catch (Exception e) {
			System.out.println("Exception while navigating to edit event plan form");
		}
	}

	public String getEventPlanErrorMessage() {
		String ErrorMessage = "";
		try {
			ErrorMessage = driver.findElement(By.xpath(prreader.getPropertyvalues("EventplanLockedErrorMessage")))
					.getText();
		} catch (Exception e) {
			System.out.println("Exception while getting the error message");
		}
		return ErrorMessage;

	}

	public void archiveEventPlan(String eventPlan) throws InterruptedException {
		try {
			driver.findElement(By.xpath(prreader.getPropertyvalues("EventPlanArchiveButton"))).click();
			driver.findElement(By.id(prreader.getPropertyvalues("EventPlanArchiveConfirmButton"))).click();
			Thread.sleep(1000);
			driver.navigate().refresh();
		} catch (Exception e) {
			System.out.println("Exception while archiving the event plan");
		}

	}

	public void navigateToArchivedEventPlanTab() {
		try {
			driver.findElement(By.id(prreader.getPropertyvalues("ShowArchivedEventPlanButton"))).click();
		} catch (Exception e) {
			System.out.println("Exception navigating to archived event plan tab");
		}

	}

	public void unArchiveEventPlan(String eventPlan) throws InterruptedException {
		try {
			driver.findElement(By.xpath(prreader.getPropertyvalues("EventPlanUnarchiveButton"))).click();
			driver.findElement(By.id(prreader.getPropertyvalues("EventPlanArchiveConfirmButton"))).click();
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("Exception while unarchiving the event plan");
		}

	}

	public void shareEventPlan(String eventPlan) {
		try {
			driver.findElement(By.xpath(prreader.getPropertyvalues("EventPlanShareButton"))).click();
		} catch (Exception e) {
			System.out.println("Exception while sharing the event plan");
		}

	}

	public boolean isEventPlanShared() {
		boolean state = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
			String attributeValue = driver.findElement(By.xpath(prreader.getPropertyvalues("EventPlanShareButton")))
					.getAttribute("class");
			if (attributeValue.equals(prreader.getPropertyvalues("EventPlanSharedBlueClassName"))) {
				state = true;
			} else

				state = false;
		} catch (Exception e) {
			System.out.println("Exception while while finding the event plan sared status");
		}
		return state;
	}

	public boolean isGoogleMapLoaded() {
		boolean mapState = false;
		try {
			mapState = driver.findElement(By.xpath(prreader.getPropertyvalues("EventGoogleMapImage"))).isDisplayed();
			closeMap();
		} catch (Exception e) {
			System.out.println("Exception getting the SAC google map load status");
		}
		return mapState;
	}

	public boolean isEventLogWindowLoaded() {
		boolean eventLogWindowState = false;
		try {
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath(prreader.getPropertyvalues("EventLogWindowButton"))))
					.build().perform();
			eventLogWindowState = driver.findElement(By.xpath(prreader.getPropertyvalues("EventLogWindow")))
					.isDisplayed();
		} catch (Exception e) {
			System.out.println("Exception while finding the event log window");
		}
		return eventLogWindowState;
	}

	public boolean isVideoFeedAvailable() {
		boolean state = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
			if (driver.findElement(By.id(prreader.getPropertyvalues("EventFeedVideoStatusGreen"))).isDisplayed()) {
				state = true;
			}
		} catch (Exception e) {
			System.out.println("Exception while finding the video feed stream status");
		}
		return state;
	}

	public void rightClickOnEventAndOpenInNewWindow(String incident) {
		Actions action = new Actions(driver);
		action.contextClick().sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).build().perform();

	}

	public void rightClickActiveEvent(String incident) throws InterruptedException, AWTException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
		driver.findElement(By.id(prreader.getPropertyvalues("EventSearchButton"))).click();
		driver.findElement(By.id(prreader.getPropertyvalues("EventSearchInputBox"))).sendKeys(incident.toLowerCase());
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfAllElements(
				driver.findElements(By.xpath(prreader.getPropertyvalues("EventListTableRow")))));
		List<WebElement> trList = driver.findElements(By.xpath(prreader.getPropertyvalues("EventListTableRow")));
		for (WebElement tr : trList) {
			WebElement td = tr.findElement(By.xpath("//td[1]/a"));
			if ((td.getText()).equals(incident)) {
				Actions action = new Actions(driver);
				action.keyDown(Keys.SHIFT).click(td).keyUp(Keys.SHIFT).build().perform();

			}
		}
	}

	public void playBackPlayAndPause() {
		try {
			driver.findElement(By.xpath(prreader.getPropertyvalues("PlaybackPlayPauseButton"))).click();
		} catch (Exception e) {
			System.out.println("Exception while play/pause historical event");
		}
	}

	public boolean isPlayBackPlaying() {
		boolean state = false;
		try {
			WebElement playBackButton = driver
					.findElement(By.xpath(prreader.getPropertyvalues("PlaybackPlayPauseButton")));
			if (playBackButton.getAttribute("aria-label").equals("pause")) {
				state = true;
			} else
				state = false;
		} catch (Exception e) {
			System.out.println("Exception in getting the playback state");
		}
		return state;

	}

	public void addUpdateEventCaseNumber(String caseNumber) {
		WebElement caseNumberField = driver.findElement(By.xpath(prreader.getPropertyvalues("EventCaseNumber")));
		caseNumberField.clear();
		caseNumberField.sendKeys(caseNumber);
		caseNumberField.sendKeys(Keys.ENTER);

	}

	public void autoFollowUAS() throws InterruptedException {
		try {
			driver.findElement(By.id(prreader.getPropertyvalues("FollowUASOption"))).click();
			Thread.sleep(1000);
			driver.findElement(By.id(prreader.getPropertyvalues("FollowUASToggleButton"))).click();
		} catch (Exception e) {
			System.out.println("Exception while clicking on Auto Follow button");
		}
	}

	public boolean verifyAutoFollowUASStatus() {
		boolean state = false;
		try {
			WebElement uasStatus = driver.findElement(By.xpath(prreader.getPropertyvalues("FollowUASStatus")));
			if (uasStatus.getAttribute("class").equals("follow-uas-menu-icon ng-scope blue")) {
				state = true;
			} else
				state = false;
		} catch (Exception e) {
			System.out.println("Exception while clicking on Auto Follow button");
		}
		return state;
	}

	public boolean isChatWindowLoaded() throws InterruptedException {
		boolean state = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
			Thread.sleep(1000);
			state = driver.findElement(By.xpath(prreader.getPropertyvalues("EventChatWindow"))).isDisplayed();
		} catch (Exception e) {
			System.out.println("Exception while finding the chat window");
		}
		return state;

	}

	public void drawPolygon() throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
			driver.findElement(By.id(prreader.getPropertyvalues("MapDrawMenu"))).click();
			Thread.sleep(1000);
			driver.findElement(By.id(prreader.getPropertyvalues("MapDrawPolygon"))).click();
			Actions a = new Actions(driver);
			a.click().perform();
			a.moveByOffset(100, 0).click().perform();
			a.moveByOffset(50, -100);
			a.click().doubleClick().build().perform();
			driver.findElement(By.id(prreader.getPropertyvalues("MapDrawingLabel"))).sendKeys("Polygontest");
			driver.findElement(By.id(prreader.getPropertyvalues("MapDrawingShareButton"))).click();
		} catch (Exception e) {
			System.out.println("Exception while drawing a polygon on SAC Map");
		}
	}

	public void drawPoint() throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
			driver.findElement(By.id(prreader.getPropertyvalues("MapDrawMenu"))).click();
			Thread.sleep(1000);
			driver.findElement(By.id(prreader.getPropertyvalues("MapDrawPoint"))).click();
			Actions a = new Actions(driver);
			a.moveByOffset(200, 0).click().perform();
			driver.findElement(By.id(prreader.getPropertyvalues("MapDrawingLabel"))).sendKeys("PointTest");
			driver.findElement(By.id(prreader.getPropertyvalues("MapDrawingShareButton"))).click();
		} catch (Exception e) {
			System.out.println("Exception while drawing a point on SAC map");
		}
	}

	public void drawPointInsidePolygon() throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
			driver.findElement(By.id(prreader.getPropertyvalues("MapDrawMenu"))).click();
			Thread.sleep(1000);
			driver.findElement(By.id(prreader.getPropertyvalues("MapDrawPoint"))).click();
			Actions a = new Actions(driver);
			a.moveByOffset(100, 0).click().perform();
			driver.findElement(By.id(prreader.getPropertyvalues("MapDrawingLabel"))).sendKeys("PointInsidePolygon");
			driver.findElement(By.id(prreader.getPropertyvalues("MapDrawingShareButton"))).click();
		} catch (Exception e) {
			System.out.println("Exception while drawing a point inside polygon on SAC map");
		}

	}

	public void drawPolyline() throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
			driver.findElement(By.id(prreader.getPropertyvalues("MapDrawMenu"))).click();
			Thread.sleep(1000);
			driver.findElement(By.id(prreader.getPropertyvalues("MapDrawPolyline"))).click();
			Actions a = new Actions(driver);
			a.click().perform();
			a.moveByOffset(300, 0).click().perform();
			a.moveByOffset(50, -200).click().perform();
			a.moveByOffset(-250, 300);
			a.click().doubleClick().build().perform();
			driver.findElement(By.id(prreader.getPropertyvalues("MapDrawingLabel"))).sendKeys("Polylinetest");
			driver.findElement(By.id(prreader.getPropertyvalues("MapDrawingShareButton"))).click();
		} catch (Exception e) {
			System.out.println("Exception while drawing a polyline on SAC map");
		}
	}

	public void drawPointInsidePolyline() throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
			driver.findElement(By.id(prreader.getPropertyvalues("MapDrawMenu"))).click();
			Thread.sleep(1000);
			driver.findElement(By.id(prreader.getPropertyvalues("MapDrawPoint"))).click();
			Actions a = new Actions(driver);
			a.moveByOffset(300, 50).click().perform();
			driver.findElement(By.id(prreader.getPropertyvalues("MapDrawingLabel"))).sendKeys("PointInsidePolyline");
			driver.findElement(By.id(prreader.getPropertyvalues("MapDrawingShareButton"))).click();
		} catch (Exception e) {
			System.out.println("Exception while drawing a point inside polyline on SAC map");
		}

	}

	public void drawFreehand() throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
			driver.findElement(By.id(prreader.getPropertyvalues("MapDrawMenu"))).click();
			Thread.sleep(1000);
			driver.findElement(By.id(prreader.getPropertyvalues("MapDrawFreehandLine"))).click();
			Actions a = new Actions(driver);
			// a.click().perform();
			WebElement e = driver.findElement(By.xpath("//map/area"));
			a.clickAndHold().moveByOffset(100, 400).release().build().perform();
			// a.dragAndDrop(50, 50);
			driver.findElement(By.id(prreader.getPropertyvalues("MapDrawingLabel"))).sendKeys("freehand");
			driver.findElement(By.id(prreader.getPropertyvalues("MapDrawingShareButton"))).click();
		} catch (Exception e) {
			System.out.println("Exception while drawing a freehand on SAC map");
		}
	}

	public boolean verifyArchiveAccess() {

		boolean state = false;
		try {
			if (driver.findElement(By.xpath(prreader.getPropertyvalues("EventPlanArchiveButton"))).isDisplayed()) {
				state = true;
			}
		} catch (Exception e) {
			state = false;
		}
		return state;

	}

	public boolean isEventHistoryTabVisible() {
		boolean state = false;
		try {
			if (driver.findElement(By.xpath(prreader.getPropertyvalues("EventHistoryTab"))).isDisplayed()) {
				state = true;
			}
		} catch (Exception e) {
			state = false;
		}
		return state;
	}
	public void launchPrePlan(String preplan) throws InterruptedException
	{
		try {
			System.out.print("in launch method....");
			driver.findElement(By.xpath(prreader.getPropertyvalues("LaunchEventPlanButton"))).click();
			}
 
		catch (Exception e) {
			System.out.println("Exception while launching the preplan");
			
		}

	}

	public boolean verifyPrePlanLaunchSuccess() {
		boolean state = false;
		try {
			if (driver.findElement(By.xpath(prreader.getPropertyvalues("NewEventLabel"))).isDisplayed()) {
				state = true;
			}
		} catch (Exception e) {
			state = false;
		}
		return state;
	}

	public void openMapMenu() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
			driver.findElement(By.id(prreader.getPropertyvalues("MapMenu"))).click();
	
			}
		 catch (Exception e) {
			 System.out.println("Could not click on map menu");
			
		}
		
	}

	public List<WebElement> getMapLayerList() {
		Actions action = new Actions(driver);
		List<WebElement> mapLayerList = null;
		try {
		mapLayerList = driver.findElements(By.xpath(prreader.getPropertyvalues("MapLayerList")));
		action.sendKeys(Keys.ESCAPE).perform();
		
			}
		 catch (Exception e) {
			 System.out.println("Could not get map layer list");
			
		}
		return mapLayerList;
		
	}

	public void toggleMapLayer() {

		List<WebElement> mapLayerList = getMapLayerList();
		int layerCount = mapLayerList.size();
		int i =1;
		for(WebElement layer : mapLayerList)
		{		
			   openMapMenu();
				layer.click();
					
		}


	}
	
	public void navigateToEditUsersTab()
	{
		try {
			driver.findElement(By.xpath(prreader.getPropertyvalues("ConfigMapButtonNew"))).click();
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(prreader.getPropertyvalues("EditUsersTab"))));
			driver.findElement(By.xpath(prreader.getPropertyvalues("EditUsersTab"))).click();
			} 
		catch (Exception e) {
			System.out.println("Exception while navigating to Edit users tab");
		}
	}
	
	public void addEventParticipant(List<String> participants) throws InterruptedException
	{
		try {
		int count = 0;
		for (String participant : participants) {
			count++;
			Pattern whitespace = Pattern.compile("\\s");
			Matcher matcher = whitespace.matcher(participant);
			String participantFullName = matcher.replaceAll(", ");
			if (count == 1) {
				Thread.sleep(1000);
				driver.findElement(By.xpath(prreader.getPropertyvalues("SearchParticipantsButton"))).click();
			}

			driver.findElement(By.id(prreader.getPropertyvalues("SearchParticipantsTextBox"))).clear();
			driver.findElement(By.id(prreader.getPropertyvalues("SearchParticipantsTextBox")))
					.sendKeys(getNameToSearch(participant));
			Thread.sleep(1000);
			List<WebElement> participantLinks = driver
					.findElements(By.xpath(prreader.getPropertyvalues("participantList")));
			for (WebElement e : participantLinks) {

				String name = e.getText();
				String[] array1 = name.split("\n");
				String result = array1[1];
				if ((result).equals(participantFullName)) {
					e.click();
					Thread.sleep(1000);
					break;
				}
			}

		}
		driver.findElement(By.id(prreader.getPropertyvalues("EventUpdateButton"))).click();
		driver.findElement(By.id(prreader.getPropertyvalues("SACSettingsClose"))).click();
		}
		catch (Exception e) {
			System.out.println("Exception while adding the participants");
		}
		
	}
	
	public void removeParticipants(List<String> participants)
	{
		try {
		List<WebElement> assignedParticipantList = driver.findElements(By.xpath(prreader.getPropertyvalues("AssignedParticipantList")));
		for(String participant : participants)
		{		
			
				for(int i=0; i< assignedParticipantList.size();i++){
		            WebElement user = assignedParticipantList.get(i).findElement(By.xpath(".//div/div//p"));
		           String user1 =  user.getText().replace(",", "");
					 if(participant.equals(user1))
					 {
						assignedParticipantList.get(i).click();
						 break;
					 }
							
				}

							
		}
		driver.findElement(By.id(prreader.getPropertyvalues("EventUpdateButton"))).click();
		driver.findElement(By.id(prreader.getPropertyvalues("SACSettingsClose"))).click();
		}
		catch(Exception e)
		{
			System.out.println("Wxcwption while removing the participants");
		}
	}
	
	public void assignUsersToEvent(List<String> participants)
	{
		try {
			driver.findElement(By.xpath(prreader.getPropertyvalues("SACUsersMenu"))).click();
			driver.findElement(By.id(prreader.getPropertyvalues("LoadAvailableUsers"))).click();
			List<WebElement> availableUsers = driver.findElements(By.xpath(prreader.getPropertyvalues("AvailableUsers")));
			
			for(String participant : participants)
			{		
				for(int i=0; i< availableUsers.size();i++){
		            WebElement user = availableUsers.get(i).findElement(By.xpath(".//div[1]"));
					 if(participant.equals(user.getText()))
					 {
						 WebElement user1 = availableUsers.get(i).findElement(By.xpath(".//div[2]"));
						 user1.findElement(By.xpath(".//button")).click();
						 break;
					 }
							
				}
						
			}

			}
			catch(Exception e)
			{
				System.out.println("Exception while assigning the users");
			}
		}
	public void removeUsersFromEvent(List<String> participants)
	{
		try {
			driver.findElement(By.xpath(prreader.getPropertyvalues("SACUsersMenu"))).click();
			List<WebElement> assignedUsers = driver.findElements(By.xpath(prreader.getPropertyvalues("AssignedUsers")));
			
			for(String participant : participants)
			{		
				for(int i=0; i< assignedUsers.size();i++){
		            WebElement user = assignedUsers.get(i).findElement(By.xpath(".//div[1]"));
					 if(participant.equals(user.getText()))
					 {
						 WebElement user1 = assignedUsers.get(i).findElement(By.xpath(".//div[2]"));
						 user1.findElement(By.xpath(".//button")).click();
						 break;
					 }
							
				}
						
			}

			}
			catch(Exception e)
			{
				System.out.println("Exception while assigning the users");
			}
		}

	public String  getUsersEditedSuccessMessage() {
		String msg = driver.findElement(By.xpath(prreader.getPropertyvalues("GeneralPopUpMessage"))).getText();
		return msg;
		
	}

	public void moveChatWindow() throws InterruptedException {
		Actions action = new Actions(driver);
		WebElement source = driver.findElement(By.xpath(prreader.getPropertyvalues("PlaybackChatWindow")));
		WebElement target = driver.findElement(By.xpath(prreader.getPropertyvalues("PlaybackMapWindow")));
		action.dragAndDrop(source, target).build().perform();
		Thread.sleep(3000);
		
	}

}
