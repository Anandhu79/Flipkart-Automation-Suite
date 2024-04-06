package TestClasses;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import BaseClasses.BaseTestClass;
import BaseClasses.PageBaseClass;
import PageClasses.LandingPage;

public class FlipkartHomeTest extends BaseTestClass {
	
	LandingPage landingPage;
	int FlipkartHomeTestTC1=1,FlipkartHomeTestTC2=2,FlipkartHomeTestTC3=3;
	
	@Test(priority = 0)
	public void openFlipkart() throws InterruptedException {

	 
		logger = report.createTest(" Verifying the Opening of the Browser and invokation of the URL for FLIPKART ");

		PageBaseClass pageBase = new PageBaseClass(driver,logger);
		PageFactory.initElements(driver, pageBase);
		landingPage = pageBase.OpenApplication();
		//landingPage.clickCancelLogin();
		landingPage.verifyTitle(expectedFlipkartHomePageTitle, FlipkartHomeTestTC1);
		landingPage.closeBrowser();

	}
	
	@Test(priority = 1)
	public void displayFlipkartMenu() throws InterruptedException, IOException {

	 
		logger = report.createTest(" Verifying the menu options displayed in the FLIPKART ");

		PageBaseClass pageBase = new PageBaseClass(driver,logger);
		PageFactory.initElements(driver, pageBase);
		landingPage = pageBase.OpenApplication();
		//landingPage.clickCancelLogin();
		landingPage.flipkartMenu();
		landingPage.verifyTitle(expectedFlipkartHomePageTitle, FlipkartHomeTestTC2);
		landingPage.closeBrowser();

	}
	
	//@Test(priority = 2)
	public void inspectFlipkartSearch() throws InterruptedException {

	 
		logger = report.createTest("Inspecting the search bar in FLIPKART ");

		PageBaseClass pageBase = new PageBaseClass(driver,logger);
		PageFactory.initElements(driver, pageBase);
		landingPage = pageBase.OpenApplication();
		//landingPage.clickCancelLogin();
		landingPage.inspectSearch("Iphone 14");
		landingPage.verifyTitle(expectedFlipkartHomePageTitle, FlipkartHomeTestTC3);
		landingPage.closeBrowser();

	}
	

	
	
	


}
