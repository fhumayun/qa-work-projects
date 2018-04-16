package page_objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.DatabaseConnection;
import utils.PropertiesFileReader;

public class VideoFeedsPage extends BaseClass {
	RemoteWebDriver driver;
	static PropertiesFileReader prreader = new PropertiesFileReader();
	CommonClass cClass = new CommonClass(driver);

	public VideoFeedsPage(RemoteWebDriver driver) {
		this.driver = driver;

	}

	public boolean isaddFeedButtonPresent() {
		boolean state = false;
		try {
			if (driver.findElement(By.id(prreader.getPropertyvalues("VideoFeedsAddButton"))).isDisplayed()) {
				state = true;
			} else

				state = false;

		} catch (Exception e) {
			System.out.println("Exception while finding add new feed button");
		}
		return state;
	}

	public void navigateToFixedCamTab() {
		try {
			driver.findElement(By.id(prreader.getPropertyvalues("FixedCameraTab"))).click();
		} catch (Exception e) {
			System.out.println("Exception while finding add new feed button");
		}

	}

	public boolean searchFeed(String feed) throws InterruptedException {
		boolean state = false;
		try {
			Thread.sleep(1000);
			driver.findElement(By.id(prreader.getPropertyvalues("FixedCamSearchButton"))).click();
			driver.findElement(By.id(prreader.getPropertyvalues("FixedCamSearchInput"))).sendKeys(feed.toLowerCase());
			Thread.sleep(1000);
			List<WebElement> trList = driver.findElements(By.xpath(prreader.getPropertyvalues("FixedCamTableRowList")));
			for (WebElement tr : trList) {

				WebElement td = tr.findElement(By.xpath("//td[1]"));
				if ((td.getText()).equals(feed)) {
					state = true;
					break;

				} else {
					state = false;
				}

			}

		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			driver.findElement(By.id(prreader.getPropertyvalues("FixedCamSearchButton"))).click();

		}

		return state;

	}

	public boolean startFeed(String feed) throws InterruptedException {
		boolean state = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
			driver.findElement(By.id(prreader.getPropertyvalues("FixedCamSearchButton"))).click();
			driver.findElement(By.id(prreader.getPropertyvalues("FixedCamSearchInput"))).sendKeys(feed.toLowerCase());
			Thread.sleep(1000);
			// wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath(prreader.getPropertyvalues("FixedCamTableRowList")))));
			List<WebElement> trList = driver.findElements(By.xpath(prreader.getPropertyvalues("FixedCamTableRowList")));

			for (WebElement tr : trList) {

				WebElement td = tr.findElement(By.xpath("//td[1]"));
				if ((td.getText()).equals(feed)) {
					state = true;
					tr.findElement(By.xpath(prreader.getPropertyvalues("FeedStartLink"))).click();
					break;

				}

			}

		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			driver.findElement(By.id(prreader.getPropertyvalues("FixedCamSearchButton"))).click();

		}

		return state;

	}

	public void deleteFeed(String feed) throws InterruptedException {
		try {
			searchFeed(feed);
			driver.findElement(By.xpath(prreader.getPropertyvalues("FeedDeleteLink"))).click();
			Thread.sleep(1000);
			driver.findElement(By.id(prreader.getPropertyvalues("ConfirmDialogButton"))).click();
		} catch (Exception e) {
			System.out.println("Exception while deleting a feed");
		}

	}

	public void displayFixedCam() {
		try {
			driver.findElement(By.id(prreader.getPropertyvalues("FixedCamDisplayLink"))).click();
		} catch (Exception e) {
			System.out.println("Exception while clicking on cam");
		}
	}

	public boolean isVideoDisplaying() {
		boolean state = false;
		try {
			if (driver.findElement(By.xpath(prreader.getPropertyvalues("VideoFeedStream"))).isDisplayed()) {
				state = true;
			} else
				state = false;
		}

		catch (Exception e) {
			System.out.println("Exception while finding add new feed button");
		}
		return state;
	}

	public void addNewUAV(String name, String account, String cameraType, String wowzaPort, String klvPort,
			String frameRate, String videoName) throws Exception {
		try {
			driver.findElement(By.id(prreader.getPropertyvalues("FeedsAddButton"))).click();
			driver.findElement(By.id(prreader.getPropertyvalues("FeedName"))).sendKeys(name);
			driver.findElement(By.id(prreader.getPropertyvalues("AccountName"))).sendKeys(account);
			driver.findElement(By.id(prreader.getPropertyvalues("FeedCameraType"))).sendKeys(cameraType);
			driver.findElement(By.id(prreader.getPropertyvalues("WowzaStreamPort"))).sendKeys(wowzaPort);
			driver.findElement(By.id(prreader.getPropertyvalues("KLVPort"))).sendKeys(klvPort);
			driver.findElement(By.id(prreader.getPropertyvalues("FrameRate"))).sendKeys(frameRate);
			selectSimulationVideo(videoName);
			driver.findElement(By.id(prreader.getPropertyvalues("FeedSaveButton"))).click();
		} catch (Exception e) {
			System.out.println("Exception while adding a new UAV");
		}
	}

	public void selectSimulationVideo(String video) throws Exception {
		try {
			Thread.sleep(1000);

			WebElement slider = driver.findElement(By.xpath(prreader.getPropertyvalues("FeedSimulationMode")));
			Actions move = new Actions(driver);
			Action action = (Action) move.dragAndDropBy(slider, 30, 0).build();
			action.perform();
			List<WebElement> trList = driver
					.findElements(By.xpath(prreader.getPropertyvalues("SimulationVideoListrow")));
			//driver.findElement(By.xpath("//md-input-container[@class='md-block flex-gt-sm']//tbody/tr[2]/td[1]")).click();
			for (WebElement tr : trList) {
									String va = tr.getText();
									System.out.println(va);
				WebElement td1 = tr.findElement(By.xpath("//td[1]"));
				WebElement td2 = tr.findElement(By.xpath("//td[2]"));
									String val = td2.getText();
									System.out.println(val);
				
				if ((tr.getText()).contains(video)) {
					tr.click();
				}
			}
			


		} catch (Exception e) {
			System.out.println("Exception while selecting a simulation video");
		}
	}

	public void updateFeed(String name, String account, String cameraType, String wowzaPort, String klvPort,
			String frameRate, String videoName) throws Exception {
		// TODO Auto-generated method stub
		try {
			searchFeed(name);
			driver.findElement(By.xpath(prreader.getPropertyvalues("FeedEditLink"))).click();
			// driver.findElement(By.id(prreader.getPropertyvalues("FeedName"))).sendKeys(name);
			driver.findElement(By.id(prreader.getPropertyvalues("AccountName"))).sendKeys(account);
			driver.findElement(By.id(prreader.getPropertyvalues("FeedCameraType"))).sendKeys(cameraType);
			driver.findElement(By.id(prreader.getPropertyvalues("WowzaStreamPort"))).clear();
			driver.findElement(By.id(prreader.getPropertyvalues("WowzaStreamPort"))).sendKeys(wowzaPort);
			driver.findElement(By.id(prreader.getPropertyvalues("KLVPort"))).clear();
			driver.findElement(By.id(prreader.getPropertyvalues("KLVPort"))).sendKeys(klvPort);
			driver.findElement(By.id(prreader.getPropertyvalues("FrameRate"))).clear();
			driver.findElement(By.id(prreader.getPropertyvalues("FrameRate"))).sendKeys(frameRate);
			// selectSimulationVideo(videoName);
			driver.findElement(By.id(prreader.getPropertyvalues("FeedSaveButton"))).click();
		} catch (Exception e) {
			System.out.println("Exception while updating a feed");
		}

	}

}
