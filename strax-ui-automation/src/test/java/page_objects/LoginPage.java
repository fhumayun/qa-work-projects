package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import page_objects.BaseClass;
import page_objects.DashboardPage;
import utils.PropertiesFileReader;

public class LoginPage extends BaseClass
{

	WebDriverWait wait = new WebDriverWait(driver, 10);
	static PropertiesFileReader prreader = new PropertiesFileReader();
	private static String straxURL = prreader.getPropertyvalues("STRAXUrl");

	public LoginPage(WebDriver driver)
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * opens the STRAX login page
	 */
	public void openLoginPage()
	{
		driver.get(straxURL);
	}

	/**
	 * Login method to login with given UserName & Password
	 * 
	 * @param UserName
	 * @param Password
	 * @return
	 */
	public DashboardPage loginAsUser(String UserName, String Password)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prreader.getPropertyvalues("userEmail"))));
		driver.findElement(By.id(prreader.getPropertyvalues("userEmail"))).sendKeys(UserName);
		if(Password.isEmpty())
		{
		driver.findElement(By.id(prreader.getPropertyvalues("userPassword"))).sendKeys(Password);
		driver.findElement(By.id(prreader.getPropertyvalues("userPassword"))).sendKeys(Keys.TAB);
		}
		else
		{
		driver.findElement(By.id(prreader.getPropertyvalues("userPassword"))).sendKeys(Password);
		}
		driver.findElement(By.id(prreader.getPropertyvalues("loginButton"))).click();
		return new DashboardPage(driver);

	}

	/**
	 * returns login failure error message
	 * 
	 * @return
	 */
	public String getLoginFailedError()
	{
		String errorMessage = "";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prreader.getPropertyvalues("unauthorizedLogin"))));
		errorMessage = driver.findElement(By.xpath(prreader.getPropertyvalues("unauthorizedLogin"))).getText();
		return errorMessage;

	}
	
	/**
	 * returns page title of login page
	 * @return
	 */
	public String getLoginPageTitle()
	{
		String title = driver.getTitle();
		return title;
	}
	
	/**
	 * returns copyright information from STRAX web page footer
	 * @return
	 */
	public String getFooterCopyrightInfo()
	{
		String copyright = driver.findElement(By.xpath(prreader.getPropertyvalues("footerCopyright"))).getText();
		return copyright;
	}
	public String getInvalidEmailError()
	{
		String invalidEmaail = driver.findElement(By.xpath(prreader.getPropertyvalues("invalidEmailError"))).getText();
		return invalidEmaail;
		
	}
	public String getInvalidPasswordError()
	{
		
		String invalidPassword = driver.findElement(By.xpath(prreader.getPropertyvalues("invalidPasswordError"))).getText();
		
		return invalidPassword;
	}

}
