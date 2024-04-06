package TestClasses;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import BaseClasses.BaseTestClass;
import BaseClasses.PageBaseClass;
import PageClasses.CartPage;
import PageClasses.ElectricVehiclesPage;
import PageClasses.LandingPage;
import PageClasses.PetrolVehiclesPage;
import PageClasses.SearchResultsPage;

public class CartPageTest extends BaseTestClass{
	
	CartPage cartPage,cartPage1;
	LandingPage landingPage;
	ElectricVehiclesPage electricVehiclesPage;
	PetrolVehiclesPage petrolVehiclesPage;
	int flag,CartPageTestTC1=12,CartPageTestTC2=13;
	SearchResultsPage searchResultsPage;
	//String expectedTitle = "Online Shopping India | Buy Mobiles, Electronics, Appliances, Clothing and More Online at Flipkart.com";
	
	@Test(priority = 0)
	public void singleAddToCart() throws InterruptedException {

	 
		logger = report.createTest("Verifying the addition of a  single item into the cart ");

		PageBaseClass pageBase = new PageBaseClass(driver,logger);
		PageFactory.initElements(driver, pageBase);
		landingPage = pageBase.OpenApplication();
		//landingPage.clickCancelLogin();
		electricVehiclesPage = landingPage.selectElectricVehicles();
		electricVehiclesPage.clickMaxPricedVehicle();
		cartPage = electricVehiclesPage.selectedProduct();
		cartPage.verifyCartElements();
		cartPage.verifyTitle(expectedCartPageTitle, CartPageTestTC1);
		cartPage.closeBrowser();

	} 
	
	
	@Test(priority = 1)
	public void multipleAddToCart() throws InterruptedException, IOException {

	 
		logger = report.createTest("Verifying the addition of multiple items into the cart ");

		PageBaseClass pageBase = new PageBaseClass(driver,logger);
		PageFactory.initElements(driver, pageBase);
		landingPage = pageBase.OpenApplication();
		//landingPage.clickCancelLogin();
		electricVehiclesPage = landingPage.selectElectricVehicles();
		electricVehiclesPage.clickMaxPricedVehicle();;
		cartPage = electricVehiclesPage.selectedProduct();
		cartPage.verifyCartElements();
		cartPage.traverseToHome();
		//landingPage.clickCancelLogin();
		searchResultsPage = landingPage.searchProduct("Iphone 15");
		flag = searchResultsPage.displayMinPricedSearchedProduct();
		searchResultsPage.selectedProductAddtoCart(flag);
		cartPage=searchResultsPage.cartAdd();
		cartPage.verifyCartElements();
		cartPage.verifyTitle(expectedCartPageTitle, CartPageTestTC2);
		cartPage.closeBrowser();

	} 

}
