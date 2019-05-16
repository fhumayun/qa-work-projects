package page_objects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.PropertiesFileReader;

public class DashboardPage {
	RemoteWebDriver driver;
	static PropertiesFileReader prreader = new PropertiesFileReader();

	public DashboardPage(RemoteWebDriver driver) {
		this.driver = driver;

	}

	/**
	 * verify if navigation is successful to Dashboard page.
	 * 
	 * @return
	 */
	public boolean verifyDashboardNavigation() {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("DashboardLabel"))));
		if ((driver.findElement(By.xpath(prreader.getPropertyvalues("DashboardLabel"))).getText()).equals("Dashboard")) {
			return true;
		} else {
			return false;
		}

	}

	public UsersPage navigateToUsersPage() throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			//driver.findElement(By.id(prreader.getPropertyvalues("mainMenuButton"))).click();
			//wait.until(ExpectedConditions.elementToBeClickable(By.id(prreader.getPropertyvalues("usersMenuLink"))));
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
			driver.findElement(By.xpath(prreader.getPropertyvalues("usersMenuLink"))).click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new UsersPage(driver);

	}

	public UASsPage navigateToUASsPage() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			//wait.until(ExpectedConditions
					//.visibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("activeEventsLabel"))));
			//driver.findElement(By.id(prreader.getPropertyvalues("mainMenuButton"))).click();
			//wait.until(ExpectedConditions.elementToBeClickable(By.id(prreader.getPropertyvalues("uasMenuLink"))));
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
			driver.findElement(By.xpath(prreader.getPropertyvalues("uasMenuLink"))).click();
			wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(prreader.getPropertyvalues("listOfUASText"))));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new UASsPage(driver);

	}

	public MapLayersPage navigateToMapLayersPage() throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			//driver.findElement(By.id(prreader.getPropertyvalues("mainMenuButton"))).click();
			//wait.until(ExpectedConditions.elementToBeClickable(By.id(prreader.getPropertyvalues("mapLayersMenuLink"))));
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
			driver.findElement(By.xpath(prreader.getPropertyvalues("mapLayersMenuLink"))).click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new MapLayersPage(driver);

	}

	public VideoFeedsPage navigateToVideoFeedsPage() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			/*wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("activeEventsLabel"))));
			driver.findElement(By.id(prreader.getPropertyvalues("mainMenuButton"))).click();
			wait.until(
					ExpectedConditions.elementToBeClickable(By.id(prreader.getPropertyvalues("videoFeedsMenuLink"))));*/
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
			driver.findElement(By.xpath(prreader.getPropertyvalues("videoFeedsMenuLink"))).click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new VideoFeedsPage(driver);

	}

	public EventPage navigateToEventsPage() throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
			driver.findElement(By.xpath(prreader.getPropertyvalues("eventsMenuLink"))).click();
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return new EventPage(driver);

	}
	public void navigateToAccountMenu()
	{ 
		try {
	WebDriverWait wait = new WebDriverWait(driver, 10);
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
	//driver.findElement(By.id(prreader.getPropertyvalues("mainMenuButton"))).click();
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prreader.getPropertyvalues("accountMenu"))));
	driver.findElement(By.xpath(prreader.getPropertyvalues("accountMenu"))).click();
		}
		catch(Exception e)
		{
			System.out.println("Exception occured while navigating to Account menu");
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
	public boolean isAccountSettingsAccessible()
	{
		boolean state = false;
		try {
		driver.findElement(By.xpath(prreader.getPropertyvalues("accountMenu"))).click();
		state = driver.findElement(By.id(prreader.getPropertyvalues("accountSettings"))).isDisplayed();
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
		//action.sendKeys(Keys.ESCAPE).build().perform();
		}
		catch(Exception e)
		{
			System.out.println("Exception occured while accessing the Account Settings menu");
		}
		return state;
		
	}
	public LoginPage logOut() throws InterruptedException
	{
		try {
		WebDriverWait wait = new WebDriverWait(driver,15);
		//wait.until(ExpectedConditions.elementToBeClickable(By.id(prreader.getPropertyvalues("mainMenuButton"))));
		//driver.findElement(By.id(prreader.getPropertyvalues("mainMenuButton"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prreader.getPropertyvalues("accountMenu"))));
		driver.findElement(By.xpath(prreader.getPropertyvalues("accountMenu"))).click();
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

}
