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

public class SearchResultsPage extends PageBaseClass{
	
	List<WebElement> phoneElementList;
	int max,i=1,flag=0,price,min;
	String name = "",pprice,pname,parentWindow,childWindow,parentTitle,childWindow2,details,output,phoneElement,opDetails="Displayed phones are : ";
	Set<String> windowId;
	Iterator<String> id;
	
	By selectedProduct = By.className("_2418kt");
	By phoneElementListXpath = By.xpath("//div[@class='_13oc-S']");
	By pricePath = By.className("_30jeq3");
	By productNamePath = By.className("_4rR01T");
	
	
	
	public SearchResultsPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}

	public int displayMaxPricedSearchedProduct() throws IOException {
		
		try {
		 phoneElementList = driver.findElements(phoneElementListXpath);
		 max = Integer.parseInt(phoneElementList.get(6).findElement(pricePath).getText().replaceAll("[₹,]", ""));
		 reportInfo("Displaying the names of all the phones along with their prices as per the Search");
		for (WebElement productname : phoneElementList ) {
			
			pname = productname.findElement(productNamePath).getText();
			pprice = productname.findElement(pricePath).getText().replaceAll("[₹,]", "");
			price = Integer.parseInt(pprice);
			phoneElement = "Name: "+pname+" Price: "+price;
			opDetails=opDetails+" "+phoneElement+",";
			reportPass(phoneElement);
			if (max < price) {

				max = price;
				name = pname;
				flag=i;

			}
			i++;
		}
		transferOutput(opDetails,"IPhonesDisplayed");
		reportPass("Max Priced phone : " + name + "  Max Price : " + max);
		//return flag;
		}catch (Exception e) {
			takeScreenShotOnFailure();
			reportFail(e.getMessage());

		}
		return flag;
		
	}
	
	public void selectedProduct(int flag) throws InterruptedException, IOException {
		
		try {
		reportInfo("Displaying the details of the maximum priced phone");
		name = "Name : "+phoneElementList.get(flag-1).findElement(productNamePath).getText();
		pprice ="Price : "+phoneElementList.get(flag-1).findElement(pricePath).getText().replaceAll("[₹,]", "");
		phoneElementList.get(flag-1).findElement(productNamePath).click();
		windowId = driver.getWindowHandles();
		id = windowId.iterator();
		parentWindow = id.next();
		childWindow = id.next();
		driver.switchTo().window(childWindow);
		scrollDown();
		details = "Details : "+driver.findElement(selectedProduct).getText();
		output =name+" "+pprice+" "+details;
		transferOutput(output,"MaxPricedProductOutput");
		reportPass("Highlights of the phone: "+details);	
		}catch (Exception e) {
			takeScreenShotOnFailure();
			reportFail(e.getMessage());

		}
		
	}
public void selectedProductAddtoCart(int flag) throws InterruptedException {
		
	try {
		reportInfo("Displaying the details of the maximum priced phone");
		phoneElementList.get(flag-1).findElement(productNamePath).click();
		windowId = driver.getWindowHandles();
		id = windowId.iterator();
		parentWindow = id.next();
		childWindow = id.next();
		childWindow2 = id.next();
		driver.switchTo().window(childWindow2);
		scrollDown();
	    details = driver.findElement(selectedProduct).getText();
		reportPass("Highlights of the phone: "+details);
	}catch (Exception e) {
		takeScreenShotOnFailure();
		reportFail(e.getMessage());

	}
		
	}

public int displayMinPricedSearchedProduct() throws IOException {
	
	try {
	 phoneElementList = driver.findElements(phoneElementListXpath);
	 min = Integer.parseInt(phoneElementList.get(6).findElement(pricePath).getText().replaceAll("[₹,]", ""));
	 reportInfo("Displaying the names of all the phones along with their prices as per the Search");
	for (WebElement productname : phoneElementList ) {
		
		pname = productname.findElement(productNamePath).getText();
		pprice = productname.findElement(pricePath).getText().replaceAll("[₹,]", "");
		price = Integer.parseInt(pprice);
		phoneElement = "Name: "+pname+" Price: "+price;
		opDetails=opDetails+" "+phoneElement+",";
		reportPass(phoneElement);
		if (min > price) {

			min = price;
			name = pname;
			flag=i;

		}
		i++;
	}
	transferOutput(opDetails,"IPhonesDisplayed");
	reportPass("Min Priced phone : " + name + "  Min Price : " + min);
	//return flag;
	}catch (Exception e) {
		takeScreenShotOnFailure();
		reportFail(e.getMessage());

	}
	return flag;
	
}

}
