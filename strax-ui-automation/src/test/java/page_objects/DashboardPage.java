package page_objects;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.PropertiesFileReader;

public class DashboardPage
{
	RemoteWebDriver driver;
	static PropertiesFileReader prreader = new PropertiesFileReader();
	public DashboardPage(RemoteWebDriver driver)
	{
		this.driver=driver;

	}

	/**
	 * verify if navigation is successful to Dashboard page.
	 * 
	 * @return
	 */
	public boolean verifyDashboardNavigation()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prreader.getPropertyvalues("mainMenuButton"))));
		if (driver.findElement(By.id(prreader.getPropertyvalues("mainMenuButton"))).isDisplayed())
		{
			return true;
		} else
		{
			return false;
		}

	}
	public UsersPage navigateToUsersPage() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.findElement(By.id(prreader.getPropertyvalues("mainMenuButton"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prreader.getPropertyvalues("usersMenuLink"))));
		driver.findElement(By.id(prreader.getPropertyvalues("usersMenuLink"))).click();
		return new UsersPage(driver);
		
	}
	public UASsPage navigateToUASsPage()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("activeEventsLabel"))));
		driver.findElement(By.id(prreader.getPropertyvalues("mainMenuButton"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prreader.getPropertyvalues("uasMenuLink"))));
		driver.findElement(By.id(prreader.getPropertyvalues("uasMenuLink"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prreader.getPropertyvalues("listOfUASText"))));
		return new UASsPage(driver);
		
	}
	public MapLayersPage navigateToMapLayersPage() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.findElement(By.id(prreader.getPropertyvalues("mainMenuButton"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prreader.getPropertyvalues("mapLayersMenuLink"))));
		driver.findElement(By.id(prreader.getPropertyvalues("mapLayersMenuLink"))).click();
		return new MapLayersPage(driver);
		
	}

	public VideoFeedsPage navigateToVideoFeedsPage() {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("activeEventsLabel"))));
		driver.findElement(By.id(prreader.getPropertyvalues("mainMenuButton"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prreader.getPropertyvalues("videoFeedsMenuLink"))));
		driver.findElement(By.id(prreader.getPropertyvalues("videoFeedsMenuLink"))).click();
		return new VideoFeedsPage(driver);
		
	}
	public EventPage navigateToEventsPage() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.findElement(By.id(prreader.getPropertyvalues("mainMenuButton"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prreader.getPropertyvalues("eventsMenuLink"))));
		driver.findElement(By.id(prreader.getPropertyvalues("eventsMenuLink"))).click();
		Thread.sleep(1000);
		return new EventPage(driver);
		
	}
	
	

}
