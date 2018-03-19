package page_objects;

import java.util.ArrayList;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.DashboardPage;
import utils.PropertiesFileReader;

public class LoginPage {

	static PropertiesFileReader prreader = new PropertiesFileReader();
	//private static String straxURL = prreader.getPropertyvalues("STRAXUrl");
	// *****reads the target application URL from Jenkin parameter****
	 private static String straxURL = System.getProperty("StraxUrl");
	RemoteWebDriver driver;

	public LoginPage(RemoteWebDriver driver) {
		this.driver = driver;

		// TODO Auto-generated constructor stub
	}

	/**
	 * opens the STRAX login page
	 */
	public void openLoginPage() {
		driver.get(straxURL);
	}

	/**
	 * Login method to login with given UserName & Password
	 * 
	 * @param UserName
	 * @param Password
	 * @return
	 */
	public DashboardPage loginAsUser(String UserName, String Password) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prreader.getPropertyvalues("userEmail"))));
			driver.findElement(By.id(prreader.getPropertyvalues("userEmail"))).sendKeys(UserName);
			if (Password.isEmpty()) {
				driver.findElement(By.id(prreader.getPropertyvalues("userPassword"))).sendKeys(Password);
				driver.findElement(By.id(prreader.getPropertyvalues("userPassword"))).sendKeys(Keys.TAB);
			} else {
				driver.findElement(By.id(prreader.getPropertyvalues("userPassword"))).sendKeys(Password);
			}
			driver.findElement(By.id(prreader.getPropertyvalues("loginButton"))).click();
		} catch (Exception e) {
			System.out.println("Exception while logging in to STRAX");
		}
		return new DashboardPage(driver);

	}

	/**
	 * returns login failure error message
	 * 
	 * @return
	 */
	public String getLoginFailedError() {
		String errorMessage = "";
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(prreader.getPropertyvalues("unauthorizedLogin"))));
			errorMessage = driver.findElement(By.xpath(prreader.getPropertyvalues("unauthorizedLogin"))).getText();
		} catch (Exception e) {
			System.out.println("Exception while getting failures message");
		}
		return errorMessage;

	}

	/**
	 * returns page title of login page
	 * 
	 * @return
	 */
	public String getLoginPageTitle() {
		String title = driver.getTitle();
		return title;
	}

	/**
	 * returns copyright information from STRAX web page footer
	 * 
	 * @return
	 */
	public String getFooterCopyrightInfo() {
		String copyright = "";
		try {

			copyright = driver.findElement(By.xpath(prreader.getPropertyvalues("footerCopyright"))).getText();
			System.out.println("is it changed ..." + copyright);
		} catch (Exception e) {
			System.out.println("Exception while getting the copyright info");
		}
		return copyright;
	}

	public String getInvalidEmailError() {
		String invalidEmaail = "";
		try {
			invalidEmaail = driver.findElement(By.xpath(prreader.getPropertyvalues("invalidEmailError"))).getText();
		} catch (Exception e) {
			System.out.println("Exception while getting the invalid email error message");
		}
		return invalidEmaail;

	}

	public String getInvalidPasswordError() {
		String invalidPassword = "";
		try {

			invalidPassword = driver.findElement(By.xpath(prreader.getPropertyvalues("invalidPasswordError")))
					.getText();
		} catch (Exception e) {
			System.out.println("Exception while getting the invalid password error message");
		}
		return invalidPassword;
	}

	public void lockAccount(String userName, String password) {
		int count = 0;
		try {
			while (count <= 5) {
				WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.id(prreader.getPropertyvalues("userEmail"))));
				driver.findElement(By.id(prreader.getPropertyvalues("userEmail"))).clear();
				driver.findElement(By.id(prreader.getPropertyvalues("userEmail"))).sendKeys(userName);
				driver.findElement(By.id(prreader.getPropertyvalues("userPassword"))).clear();
				driver.findElement(By.id(prreader.getPropertyvalues("userPassword"))).sendKeys(password);
				driver.findElement(By.id(prreader.getPropertyvalues("loginButton"))).click();
				wait.until(ExpectedConditions
						.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
				count++;
			}
		} catch (Exception e) {
			System.out.println("Exception while performing account lock");
		}
	}

}
