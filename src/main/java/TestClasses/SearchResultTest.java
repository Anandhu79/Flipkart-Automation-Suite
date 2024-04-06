package TestClasses;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import BaseClasses.BaseTestClass;
import BaseClasses.PageBaseClass;
import PageClasses.LandingPage;
import PageClasses.SearchResultsPage;

public class SearchResultTest extends BaseTestClass {
	

	LandingPage landingPage;
	SearchResultsPage searchResultsPage;
	int SearchResultTestTC1=4,SearchResultTestTC2=5;

	@Test(priority = 0)
	public void displaySearchContent() throws InterruptedException {

	 
		logger = report.createTest("Displaying the search results for the keyword searched ");

		PageBaseClass pageBase = new PageBaseClass(driver,logger);
		PageFactory.initElements(driver, pageBase);
		landingPage = pageBase.OpenApplication();
		//landingPage.clickCancelLogin();
		searchResultsPage = landingPage.searchProduct("Iphone 14");
		searchResultsPage.verifyTitle(expectedSearchResultsPageTitle, SearchResultTestTC1);;
		searchResultsPage.closeBrowser();

	} 
	
	@Test(priority = 1)
	public void displayMaxProductAndTraverse() throws InterruptedException, IOException {

	 
		logger = report.createTest("Finding the maximum priced product from the search results for the keyword searched and displaying its details");

		PageBaseClass pageBase = new PageBaseClass(driver,logger);
		PageFactory.initElements(driver, pageBase);
		landingPage = pageBase.OpenApplication();
		//landingPage.clickCancelLogin();
		searchResultsPage = landingPage.searchProduct("Iphone 14");
		int flag = searchResultsPage.displayMaxPricedSearchedProduct();
		searchResultsPage.selectedProduct(flag);
		searchResultsPage.verifyTitle(expectedSearchResultsPageTitle2, SearchResultTestTC2);
		searchResultsPage.closeBrowser();

	} 
	
	
}
