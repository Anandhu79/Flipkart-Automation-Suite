package PageClasses;


import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;
import BaseClasses.PageBaseClass;

public class TravelPage extends PageBaseClass {
	
	WebElement roundTripRadio,departCityElement,arrivalCityElement,login,element,toCity;
	
	String errormsg,errormsg2,details="Login Hover Elements : ";
	List<WebElement> loginHoverList;
	WebElement errormsgElement; 
	
	By loginXpath = By.xpath("//input[@class='_2IX_2- VJZDxU']");
	By errormsgXpath = By.xpath("//span[@class='_2YULOR']/span");
	By loginHoverListXpath = By.xpath("//ul[@class='pO9syL _1s9xSv']/li");
	By elementXpath = By.xpath("//a[@class='_1_3w1N']");
	By toCityXpath = By.name("0-arrivalcity");
	
	
	public TravelPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}

	public void verifyTravelPage() {
		reportInfo("Verifying the title");
		getTitle("Online Shopping India | Buy Mobiles, Electronics, Appliances, Clothing and More Online at Flipkart.com");
	}
	
	
	public void openTravel() throws InterruptedException {
		
		try {
		scrollDown();
        Actions actions = new Actions(driver);
    	toCity = waitForElement(toCityXpath);
    	toCity.click();
    	element = waitForElement(elementXpath);
        actions.moveToElement(element).perform();
		}catch (Exception e) {
			takeScreenShotOnFailure();
			reportFail(e.getMessage());

		}

	}
	
	public void displayLoginHoveringElements() throws InterruptedException, IOException {

		try {
		openTravel();
        loginHoverList = driver.findElements(loginHoverListXpath);
        reportPass("Number of login hover items : " +loginHoverList.size());
        reportInfo("Displaying the elements");
       for(WebElement ele : loginHoverList) {
    	   reportPass(ele.getText());
    	   details=details+" "+ele.getText()+",";
       }
      transferOutput(details,"LoginHoverElements");
       reportPass("Displayed all the elements");
       loginHoverList.get(0).click();
		}catch (Exception e) {
			takeScreenShotOnFailure();
			reportFail(e.getMessage());

		}

	}
	
	
	public void invalidLogin(String data1,String data2) throws InterruptedException {
		
		try {
		openTravel();
		loginHoverList = driver.findElements(loginHoverListXpath);
	    reportInfo("Clicking Profile option from the Login Hover Dropdown elements");
		loginHoverList.get(0).click();
		login = driver.findElement(loginXpath);
	    reportInfo("Checking the login with Invalid Data No: 1");
		login.sendKeys(data1);
		login.sendKeys(Keys.ENTER);
		errormsgElement=waitForElement(errormsgXpath);
		errormsg = errormsgElement.getText();
		takeScreenShotOnFailure();
		reportPass("Error message no:1 has been obtained successfully and is:: "+errormsg);
		scrollDown();
		login.clear();
	    reportInfo("Checking the login with Invalid Data No: 2");
		login.sendKeys(data2);
		login.sendKeys(Keys.ENTER);
		errormsg2 = driver.findElement(errormsgXpath).getText();
		takeScreenShotOnFailure();
		reportPass("Error message no:2 has been obtained successfully and is:: "+errormsg2);
		login.clear();
		}catch (Exception e) {
			takeScreenShotOnFailure();
			reportFail(e.getMessage());

		}
		
	}
	

	
	



  
   }
	
   

