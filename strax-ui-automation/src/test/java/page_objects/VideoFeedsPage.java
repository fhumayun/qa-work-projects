package page_objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.PropertiesFileReader;

public class VideoFeedsPage extends BaseClass{
	RemoteWebDriver driver;
	static PropertiesFileReader prreader = new PropertiesFileReader();
	CommonClass cClass = new CommonClass(driver);
	public VideoFeedsPage(RemoteWebDriver driver) {
		this.driver = driver;

	}
	public boolean isaddFeedButtonPresent()
	{
		if(driver.findElement(By.id(prreader.getPropertyvalues("VideoFeedsAddButton"))).isDisplayed())
		{
			return true;
		}
		else	
		
		return false;
		
	}
	
	public void navigateToFixedCamTab()
	{
		driver.findElement(By.id(prreader.getPropertyvalues("FixedCameraTab"))).click();
		
	}
	public boolean searchFixedCam(String fixedcam) throws InterruptedException
	{
		boolean state = false;
		try{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
		driver.findElement(By.id(prreader.getPropertyvalues("FixedCamSearchButton"))).click();
		driver.findElement(By.id(prreader.getPropertyvalues("FixedCamSearchInput"))).sendKeys(fixedcam.toLowerCase());
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath(prreader.getPropertyvalues("FixedCamTableRowList")))));
		List<WebElement> trList = driver.findElements(By.xpath(prreader.getPropertyvalues("FixedCamTableRowList")));
		

		for (WebElement tr : trList) {

			WebElement td = tr.findElement(By.xpath("//td[1]"));
			if ((td.getText()).equals(fixedcam)) {
				
			} else {
				state = false;
			}

		}
		
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			driver.findElement(By.id(prreader.getPropertyvalues("FixedCamSearchButton"))).click();
			
		}
		
		return state;
		
		
	}
	public void displayFixedCam()
	{
		driver.findElement(By.id(prreader.getPropertyvalues("FixedCamDisplayLink"))).click();
	}
	public boolean isVideoDisplaying()
	{
		if(driver.findElement(By.xpath(prreader.getPropertyvalues("VideoFeedStream"))).isDisplayed())
		{
			return true;
		}
		else		
		return false;
		
	}
	public void addNewUAV(String name, String account, String cameraType, String wowzaPort, String klvPort, String frameRate)
	{
		driver.findElement(By.id(prreader.getPropertyvalues("FeedsAddButton"))).click();
		driver.findElement(By.id(prreader.getPropertyvalues("FeedName"))).sendKeys(name);
		driver.findElement(By.id(prreader.getPropertyvalues("AccountName"))).sendKeys(account);
		driver.findElement(By.id(prreader.getPropertyvalues("FeedCameraType"))).sendKeys(cameraType);
		driver.findElement(By.id(prreader.getPropertyvalues("WowzaStreamPort"))).sendKeys(wowzaPort);
		driver.findElement(By.id(prreader.getPropertyvalues("KLVPort"))).sendKeys(klvPort);
		driver.findElement(By.id(prreader.getPropertyvalues("FrameRate"))).sendKeys(frameRate);
		driver.findElement(By.id(prreader.getPropertyvalues("FeedSaveButton"))).click();
	}

}
