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
	//private final By mainMenuButton = By.id("main-menu__button--open");

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
		//String s1 = driver.findElement(By.xpath(prreader.getPropertyvalues("usersMenuLink"))).getText();
		//System.out.println("seee if it makes sense" +s1);
		driver.findElement(By.id(prreader.getPropertyvalues("usersMenuLink"))).click();
		return new UsersPage(driver);
		
	}
	
	

}
