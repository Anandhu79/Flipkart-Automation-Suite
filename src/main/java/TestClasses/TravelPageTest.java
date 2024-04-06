package TestClasses;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BaseClasses.BaseTestClass;
import BaseClasses.PageBaseClass;
import PageClasses.TravelPage;
import PageClasses.LandingPage;


public class TravelPageTest extends BaseTestClass {
	
	LandingPage landingPage;
	 TravelPage travelPage;
	 int TravelPageTestTC1=6,TravelPageTestTC2=7;

	@Test(priority = 0)
	public void displayLoginElementsinTravel() throws InterruptedException, IOException {

	 
		logger = report.createTest("Verifying if the Travel page is displayed after clicking 'Travel' option and displaying Login Hover Elements");

		PageBaseClass pageBase = new PageBaseClass(driver,logger);
		PageFactory.initElements(driver, pageBase);
		landingPage = pageBase.OpenApplication();
		//landingPage.clickCancelLogin();
		travelPage = landingPage.selectTravel();
		travelPage.displayLoginHoveringElements();
		travelPage.verifyTitle(expectedTravelPageTitle, TravelPageTestTC1);
		travelPage.closeBrowser();

	} 
	
	@Test(priority = 1, dataProvider="invalidData")
	public void verifyInvalidCredentials(String data1,String data2) throws InterruptedException {

	 
		logger = report.createTest("Displaying the error messages displayed when giving invalid credentials for Login");

		PageBaseClass pageBase = new PageBaseClass(driver,logger);
		PageFactory.initElements(driver, pageBase);
		landingPage = pageBase.OpenApplication();
		//landingPage.clickCancelLogin();
		travelPage = landingPage.selectTravel();
		travelPage.invalidLogin(data1,data2);
		travelPage.verifyTitle(expectedTravelPageTitle, TravelPageTestTC2);
		travelPage.closeBrowser();

	} 
	
	
	@DataProvider(name="invalidData")
	public String[][] getInvalidData() throws EncryptedDocumentException, IOException, InvalidFormatException {
		String[][] data=PageBaseClass.getInvalid();
		return data;
	}
	
	

}
