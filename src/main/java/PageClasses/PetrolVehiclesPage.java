package PageClasses;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;
import BaseClasses.PageBaseClass;

public class PetrolVehiclesPage extends PageBaseClass{

	List<WebElement> vehicleList;
	int max,flag=0,i=0,price;
	String name=" ",pname,pprice,parentWindow,childWindow,vehicleElement,opDetails="Displayed petrol Vehicles are : ";
	Set<String> windowId;
	Iterator<String> id;
	
	By pricePath = By.className("_30jeq3");
	By vehicleNamePath = By.className("s1Q9rs");
	By selectedProduct = By.xpath("//div[@class='XUp0WS']");
	By vehicleListXpath = By.xpath("//div[contains(@class,'_3YgSsQ')]");
	
	
	public PetrolVehiclesPage(WebDriver driver, ExtentTest logger) {
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
			if(i==7) {
				break;
			}
		}
		transferOutput(opDetails, "PetrolVehiclesDisplayed");
		reportPass("Max Priced Petrol Vehicle : " + name + "  Max Price : " + max);
		}catch (Exception e) {
			takeScreenShotOnFailure();
			reportFail(e.getMessage());

		}
		
		}
	
	

}
