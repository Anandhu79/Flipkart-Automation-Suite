package PageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import BaseClasses.PageBaseClass;

public class CartPage extends PageBaseClass{
	
	By placeOrderButton = By.xpath("//button[@class='_2KpZ6l _2ObVJD _3AWRsL']");
	By priceDetailsXpath = By.xpath("//div[@class='_35mLK5']");
	By flipHomeButton = By.xpath("//img[@title='Flipkart']");
			
	public CartPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		
	}
	
	public void verifyCartElements() {
		
		try {
		reportPass("Status of the 'Place Order' button : "+driver.findElement(placeOrderButton).isDisplayed());  
		reportInfo("Dispalying the price details in the cart");
		reportPass(driver.findElement(priceDetailsXpath).getText());
		}catch (Exception e) {
			takeScreenShotOnFailure();
			reportFail(e.getMessage());

		}
	}
	
	public void traverseToHome() {
		
		try {
		reportInfo("Directing to the Flipkart HomePage");
		driver.findElement(flipHomeButton).click();
		}catch (Exception e) {
			takeScreenShotOnFailure();
			reportFail(e.getMessage());

		}
	}

}
