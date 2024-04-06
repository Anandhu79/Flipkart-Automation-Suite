package PageClasses;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import BaseClasses.PageBaseClass;

public class ElectricVehiclesPage extends PageBaseClass{

	List<WebElement> vehicleList;
	int max,flag=0,i=0,price;
	String name=" ",pname,pprice,parentWindow,childWindow,vehicleElement,opDetails="Displayed Electric Vehicles are : ";
	Set<String> windowId;
	Iterator<String> id;
	
	By pricePath = By.className("_30jeq3");
	By vehicleNamePath = By.className("s1Q9rs");
	By selectedProduct = By.xpath("//div[@class='XUp0WS']");
	By cartButton = By.xpath("//button[@class='_2KpZ6l _2U9uOA _3v1-ww']");
	By vehicleListXpath = By.xpath("//div[contains(@class,'_3YgSsQ')]");
	

	public ElectricVehiclesPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		
	}

	public void displayVehicles() throws InterruptedException, IOException {
		
		try {
		vehicleList= driver.findElements(vehicleListXpath);
		max = Integer.parseInt(vehicleList.get(2).findElement(pricePath).getText().replaceAll("[₹,]", ""));
		reportInfo("Displaying the names of all the Electric Vehicles along with their prices ");
		for (WebElement vehiclename : vehicleList ) {
			
			pname = vehiclename.findElement(vehicleNamePath).getText();
			pprice = vehiclename.findElement(pricePath).getText().replaceAll("[₹,]", "");
			price = Integer.parseInt(pprice);
			vehicleElement = "Name: "+pname+" Price: "+price;
			opDetails=opDetails+" "+vehicleElement+",";
			reportPass(vehicleElement);			
			if (max < price) {

				max = price;
				name = pname;
				flag=i;

			}
			i++;
		}
		transferOutput(opDetails, "ElectricVehiclesDisplayed");
		reportPass("Max Priced Electric Vehicle : " + name + "  Max Price : " + max);
		}	catch (Exception e) {
			takeScreenShotOnFailure();
			reportFail(e.getMessage());

		}
		
	}
	
	
public CartPage selectedProduct() throws InterruptedException {
		
	try {
		windowId = driver.getWindowHandles();
		id = windowId.iterator();
		parentWindow = id.next();
		childWindow = id.next();
		driver.switchTo().window(childWindow);
		scrollDown();
		String details = driver.findElement(selectedProduct).getText();
		reportPass("Switched succesfully to the selected product window");
		driver.findElement(cartButton).click();
		reportPass("Available offers for the the Vehicle: "+details);
	}catch (Exception e) {
		takeScreenShotOnFailure();
		reportFail(e.getMessage());

	}
		CartPage cartPage = new  CartPage(driver, logger);
		PageFactory.initElements(driver, cartPage);
		return cartPage;
	}


public void clickMaxPricedVehicle() {
	
	try {
	vehicleList= driver.findElements(vehicleListXpath);
	max = Integer.parseInt(vehicleList.get(2).findElement(pricePath).getText().replaceAll("[₹,]", ""));
	reportInfo("Displaying the names of all the Electric Vehicles along with their prices ");
	for (WebElement vehiclename : vehicleList ) {
		
		pname = vehiclename.findElement(vehicleNamePath).getText();
		pprice = vehiclename.findElement(pricePath).getText().replaceAll("[₹,]", "");
		price = Integer.parseInt(pprice);
		reportPass("Name: "+pname+" Price: "+price);
		if (max < price) {

			max = price;
			name = pname;
			flag=i;

		}
		i++;
	}
	reportPass("Max Priced Electric Vehicle : " + name + "  Max Price : " + max);
	
	vehicleList.get(flag).click();
	}catch (Exception e) {
		takeScreenShotOnFailure();
		reportFail(e.getMessage());

	}

	
}


	}

