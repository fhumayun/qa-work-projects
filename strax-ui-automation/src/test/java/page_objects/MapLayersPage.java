package page_objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.PropertiesFileReader;

public class MapLayersPage extends BaseClass {

	RemoteWebDriver driver;
	static PropertiesFileReader prreader = new PropertiesFileReader();
	CommonClass cClass = new CommonClass(driver);
	public MapLayersPage(RemoteWebDriver driver) {
		this.driver = driver;

	}
	
	public boolean isMapLayerButtonPresent()
	{
		if(driver.findElement(By.id(prreader.getPropertyvalues("MapLayerAddButton"))).isDisplayed())
		{
			return true;
		}
		else	
		
		return false;
		
	}
	public boolean searchEvent(String incident) throws InterruptedException
	{
		boolean state = false;
		try{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prreader.getPropertyvalues("loadingIcon"))));
		driver.findElement(By.id(prreader.getPropertyvalues("MapLayersSearchButton"))).click();
		driver.findElement(By.id(prreader.getPropertyvalues("MapLayersSearchInputBox"))).sendKeys(incident.toLowerCase());
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath(prreader.getPropertyvalues("MapLayersListTableRow")))));
		List<WebElement> trList = driver.findElements(By.xpath(prreader.getPropertyvalues("MapLayersListTableRow")));
		

		for (WebElement tr : trList) {

			WebElement td = tr.findElement(By.xpath("//td[1]"));
			if ((td.getText()).equals(incident)) {
				state = true;
			} else {
				state = false;
			}

		}
		
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			driver.findElement(By.id(prreader.getPropertyvalues("MapLayersSearchButton"))).click();
			
		}
		
		return state;
		
		
	}
	public void archiveMapLayer(String mapLayer) throws InterruptedException {
		
		driver.findElement(By.xpath(prreader.getPropertyvalues("MapLayerArchiveButton"))).click();
		driver.findElement(By.id(prreader.getPropertyvalues("MapLayerArchiveConfirmButton"))).click();
		Thread.sleep(1000);
		driver.navigate().refresh();
	
}

}
