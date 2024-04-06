package PageClasses;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import BaseClasses.PageBaseClass;


public class LandingPage extends PageBaseClass {
	
	List<WebElement> menuList; List<WebElement> optionsList;
	WebElement option1;
	int i=1;
	String details = "Menu Items are : ";
	
	By cancel = By.xpath("//span[@role='button']");
	By menuItems = By.className("_1ch8e_");
	By option1xpath = By.xpath("//li[@class='_3D0G9a'][1]");
	By optionsListxpath = By.xpath("//li[@class='_3D0G9a']");
	By searchBoxxpath = By.xpath("//input[@name='q']");
	By travelXpath = By.xpath("//span[contains(text(),'Travel')]");
	By electricVehiclesXpath = By.xpath("//a[normalize-space()='Electric Vehicles']");
	By vehiclesXpath = By.xpath("//span[contains(text(),'Two Wheelers')]");
	By petrolVehiclesXpath = By.xpath("//a[normalize-space()='Petrol Vehicles']");
	
	
	
	public LandingPage(WebDriver driver, ExtentTest logger) {
		super(driver,logger);
	}

	public void clickCancelLogin() {
		driver.findElement(cancel).click();
	}
	
	public void flipkartMenu() throws IOException {
		
		try {
		menuList = driver.findElements(menuItems);	
		for (WebElement menuItem : menuList ) {
			 reportPass("Menu Item no:"+" "+ i +" "+ menuItem.getText());
			 details=details+" "+menuItem.getText()+",";
			 if(i==(menuList.size())) {
				 reportPass("All the flipkart menu items have been displayed successfully");
			 }
			 ++i;
		 }
		transferOutput(details, "HomePageMenuItems");
		}catch (Exception e) {
			takeScreenShotOnFailure();
			reportFail(e.getMessage());

		}

	}
	
	public void inspectSearch(String product) {
		
		try {
		 reportPass("Status of the presence of the Search bar : "+driver.findElement(searchBoxxpath).isDisplayed());
			driver.findElement(searchBoxxpath).sendKeys(product);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			option1 = wait.until(ExpectedConditions.elementToBeClickable(option1xpath));
			reportInfo("Displaying the no: of items displayed when a keyword is typed in the Search box") ;
			optionsList = driver.findElements(optionsListxpath);
				reportPass("The no: of items appeared upon enter the keyword for Search are : "+optionsList.size());
		}catch (Exception e) {
			takeScreenShotOnFailure();
			reportFail(e.getMessage());

		}

		
	}
	
	public SearchResultsPage searchProduct(String product) {
		
		try {
		driver.findElement(searchBoxxpath).sendKeys(product);
		driver.findElement(searchBoxxpath).sendKeys(Keys.ENTER);
		}catch (Exception e) {
			takeScreenShotOnFailure();
			reportFail(e.getMessage());

		}
		SearchResultsPage searchResultsPage = new SearchResultsPage(driver, logger);
		PageFactory.initElements(driver, searchResultsPage);
		return searchResultsPage;
	}
	
  public TravelPage selectTravel() {
		
	  try {
	   driver.findElement(travelXpath).click();
	   reportPass("Travel option has been clicked successfully");
		}catch (Exception e) {
			takeScreenShotOnFailure();
			reportFail(e.getMessage());

		}
	   TravelPage travelPage = new TravelPage(driver, logger);
		PageFactory.initElements(driver, travelPage);
		return travelPage;
	}
  
  public ElectricVehiclesPage selectElectricVehicles() {
	  
	  try {
	  driver.findElement(vehiclesXpath).click();
	  reportPass("Two Wheelers option has been clicked successfully");
	  driver.findElement(electricVehiclesXpath).click();
	  reportPass("Electric Vehicles option has been clicked from the dropdown successfully");
	  }catch (Exception e) {
			takeScreenShotOnFailure();
			reportFail(e.getMessage());

		}
	  ElectricVehiclesPage  electricVehiclesPage = new  ElectricVehiclesPage(driver, logger);
		PageFactory.initElements(driver, electricVehiclesPage);
		return electricVehiclesPage;
	  
  }
public PetrolVehiclesPage selectPetrolVehicles() {
	  
	try {
	  driver.findElement(vehiclesXpath).click();
	  reportPass("Two Wheelers option has been clicked successfully");
	  driver.findElement(petrolVehiclesXpath).click();
	  reportPass("Petrol Vehicles option has been clicked from the dropdown successfully");
	}catch (Exception e) {
		takeScreenShotOnFailure();
		reportFail(e.getMessage());

	}
	  PetrolVehiclesPage  petrolVehiclesPage = new  PetrolVehiclesPage(driver, logger);
		PageFactory.initElements(driver, petrolVehiclesPage);
		return petrolVehiclesPage;
	  
  }
}
