package page_objects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.PropertiesFileReader;

public class DashboardPage extends BaseClass
{
	WebDriverWait wait = new WebDriverWait(driver, 10);
	static PropertiesFileReader prreader = new PropertiesFileReader();
	//private final By mainMenuButton = By.id("main-menu__button--open");

	public DashboardPage(WebDriver driver)
	{
		super(driver);

	}

	/**
	 * verify if navigation is successful to Dashboard page.
	 * 
	 * @return
	 */
	public boolean verifyDashboardNavigation()
	{

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prreader.getPropertyvalues("mainMenuButton"))));
		if (driver.findElement(By.id(prreader.getPropertyvalues("mainMenuButton"))).isDisplayed())
		{
			return true;
		} else
		{
			return false;
		}

	}
	
	

}
