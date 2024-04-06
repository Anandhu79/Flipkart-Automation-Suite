package TestClasses;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import BaseClasses.BaseTestClass;
import BaseClasses.PageBaseClass;
//import PageClasses.ElectricVehiclesPage;
import PageClasses.LandingPage;
import PageClasses.PetrolVehiclesPage;

public class PetrolVehiclesPageTest extends BaseTestClass{

	LandingPage landingPage;
	PetrolVehiclesPage petrolVehiclesPage;
	int PetrolVehiclesPageTestTC1=10,PetrolVehiclesPageTestTC2=11;
	
	@Test(priority = 0)
	public void clickPetrolVehicle() throws InterruptedException {

	 
		logger = report.createTest("Selecting the 'Petrol Vehicles' option from 'Vehicles' and moving into its page  ");

		PageBaseClass pageBase = new PageBaseClass(driver,logger);
		PageFactory.initElements(driver, pageBase);
		landingPage = pageBase.OpenApplication();
		//landingPage.clickCancelLogin();
		petrolVehiclesPage = landingPage.selectPetrolVehicles();
		petrolVehiclesPage.verifyTitle(expectedPetrolVehiclesPageTitle, PetrolVehiclesPageTestTC1);
		petrolVehiclesPage.closeBrowser();

	} 
	
	@Test(priority = 1)
	public void displayMaxPricedPetrolVehicle() throws InterruptedException, IOException {

	 
		logger = report.createTest("Selecting the maximum priced Petrol Vehicle  ");

		PageBaseClass pageBase = new PageBaseClass(driver,logger);
		PageFactory.initElements(driver, pageBase);
		landingPage = pageBase.OpenApplication();
		//landingPage.clickCancelLogin();
		petrolVehiclesPage = landingPage.selectPetrolVehicles();
		petrolVehiclesPage.displayVehicles();
		petrolVehiclesPage.verifyTitle(expectedPetrolVehiclesPageTitle, PetrolVehiclesPageTestTC2);
		petrolVehiclesPage.closeBrowser();

	} 

}
