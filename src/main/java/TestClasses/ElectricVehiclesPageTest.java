package TestClasses;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import BaseClasses.BaseTestClass;
import BaseClasses.PageBaseClass;
import PageClasses.ElectricVehiclesPage;
import PageClasses.LandingPage;

public class ElectricVehiclesPageTest  extends BaseTestClass {
	
	LandingPage landingPage;
	ElectricVehiclesPage electricVehiclesPage;
	int ElectricVehiclesPageTestTC1=8,ElectricVehiclesPageTestTC2=9;
	
	@Test(priority = 0)
	public void clickElectricVehicle() throws InterruptedException {

	 
		logger = report.createTest("Selecting the 'Electric Vehicles' option from 'Vehicles' and moving into its page ");

		PageBaseClass pageBase = new PageBaseClass(driver,logger);
		PageFactory.initElements(driver, pageBase);
		landingPage = pageBase.OpenApplication();
		//landingPage.clickCancelLogin();
		electricVehiclesPage = landingPage.selectElectricVehicles();
		electricVehiclesPage.verifyTitle(expectedElectricVehiclesPageTitle, ElectricVehiclesPageTestTC1);
		electricVehiclesPage.closeBrowser();

	} 
	
	@Test(priority = 1)
	public void displayMaxPricedElectricVehicle() throws InterruptedException, IOException {

	 
		logger = report.createTest("Selecting the maximum priced Electric Vehicle ");

		PageBaseClass pageBase = new PageBaseClass(driver,logger);
		PageFactory.initElements(driver, pageBase);
		landingPage = pageBase.OpenApplication();
		//landingPage.clickCancelLogin();
		electricVehiclesPage = landingPage.selectElectricVehicles();
		electricVehiclesPage.displayVehicles();
		electricVehiclesPage.verifyTitle(expectedElectricVehiclesPageTitle, ElectricVehiclesPageTestTC2);
		electricVehiclesPage.closeBrowser();

	} 

}
