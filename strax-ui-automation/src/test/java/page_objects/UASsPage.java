package page_objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.DatabaseConnection;
import utils.PropertiesFileReader;

public class UASsPage {

	RemoteWebDriver driver;

	public UASsPage(RemoteWebDriver driver) {
		this.driver = driver;

	}

	static PropertiesFileReader prreader = new PropertiesFileReader();
	CommonClass cClass = new CommonClass(driver);

	public boolean isAddUasButtonPresent() {
		boolean state = false;
		try {
			if (driver.findElement(By.id(prreader.getPropertyvalues("UASAddButton"))).isDisplayed()) {
				state = true;
			} else

				state = false;
		} catch (Exception e) {
			System.out.println("Exception while finding the add UAS button");
		}
		return state;
	}

	public void addNewUAS(String name, String feed, String make, String serialNumber, String model, String status,
			String type) throws InterruptedException {
		try {
			driver.findElement(By.id(prreader.getPropertyvalues("UASAddButton"))).click();
			driver.findElement(By.id(prreader.getPropertyvalues("fidgetNameTextBox"))).sendKeys(name);
			WebElement selectFeed = driver.findElement(By.id(prreader.getPropertyvalues("fidgetFeedList")));
			selectDropdownOption(selectFeed, feed);
			WebElement selectMake = driver.findElement(By.id(prreader.getPropertyvalues("fidgetMakeList")));
			selectDropdownOption(selectMake, make);
			driver.findElement(By.id(prreader.getPropertyvalues("fidgetSerialNumberTextBox"))).sendKeys(serialNumber);
			WebElement selectModel = driver.findElement(By.id(prreader.getPropertyvalues("fidgetModelList")));
			selectDropdownOption(selectModel, model);
			WebElement selectStatus = driver.findElement(By.id(prreader.getPropertyvalues("fidgetUasStatusList")));
			selectDropdownOption(selectStatus, status);
			WebElement selectType = driver.findElement(By.id(prreader.getPropertyvalues("fidgetUasTypeList")));
			selectDropdownOption(selectType, type);
			driver.findElement(By.id(prreader.getPropertyvalues("saveButton"))).click();
		} catch (Exception e) {
			System.out.println("Exception while adding a new UAS");
		}
	}

	public boolean searchUAS(String UAS) throws InterruptedException {
		boolean state = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
			driver.findElement(By.id(prreader.getPropertyvalues("fidgetSearchButton"))).click();
			driver.findElement(By.id(prreader.getPropertyvalues("fidgetInputSearch"))).sendKeys(UAS);
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOfAllElements(
					driver.findElements(By.xpath(prreader.getPropertyvalues("UASListTableRows")))));
			List<WebElement> trList = driver.findElements(By.xpath(prreader.getPropertyvalues("UASListTableRows")));

			for (WebElement tr : trList) {

				WebElement td = tr.findElement(By.xpath("//td[1]"));
				if ((td.getText()).equals(UAS)) {
					state = true;
				} else {
					state = false;
				}

			}

		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			driver.findElement(By.id(prreader.getPropertyvalues("fidgetSearchButton"))).click();

		}

		return state;

	}

	public void selectDropdownOption(WebElement dropdown, String optionToSelect) throws InterruptedException {
		try {
			dropdown.click();
			// WebDriverWait wait = new WebDriverWait(driver,10);
			// wait.until(ExpectedConditions.visi)
			List<WebElement> dropdownOptions = driver.findElements(By.tagName("md-option"));
			for (WebElement option : dropdownOptions) {
				if ((option.getText()).equals(optionToSelect)) {
					option.click();
					Thread.sleep(1000);
					break;
				}
			}
		} catch (Exception e) {
			throw e;
		}

	}

	public String deleteUASFromDB(String uas) {
		String deletedUAS = "";
		try {
			DatabaseConnection conn = new DatabaseConnection();
			deletedUAS = conn.deleteUAS(uas);
		} catch (Exception e) {
			System.out.println("Exception communicating to MongoDB");
		}
		return deletedUAS;

	}

	public void deleteUASFromUI(String uas) throws InterruptedException {
		try {
			searchUAS(uas);
			driver.findElement(By.xpath(prreader.getPropertyvalues("UASDeleteButton"))).click();
			Thread.sleep(1000);
			driver.findElement(By.id(prreader.getPropertyvalues("ConfirmDialogButton"))).click();
		} catch (Exception e) {
			System.out.println("Exception while deleting a feed");
		}
		
		
	}

	public void verifyUasState(String uas) {
		// TODO Auto-generated method stub
		
	}

}
