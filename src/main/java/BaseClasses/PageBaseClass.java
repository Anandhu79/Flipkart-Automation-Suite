package BaseClasses;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.time.Duration;
import java.io.File;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import PageClasses.CartPage;
import PageClasses.LandingPage;
import utils.DateUtil;


public class PageBaseClass extends BaseTestClass{
	
	
	public ExtentTest logger;
	
	public PageBaseClass(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}

	public LandingPage OpenApplication() throws InterruptedException {

		invokeBrowser();
		openURL("url");
		LandingPage landingPage = new LandingPage(driver,logger);
		PageFactory.initElements(driver, landingPage);

		return landingPage;

	}

	public void getTitle(String expectedTitle) {
		try {
			Assert.assertEquals(driver.getTitle(), expectedTitle);
			reportPass("Actual Title : " + driver.getTitle() + " - equals to Expected Title : " + expectedTitle);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}
	public void verifyTitle(String expectedString,int testCaseNumber) {
		try {
			Assert.assertTrue(driver.getTitle().contains(expectedString));
			//Assert.assertEquals(driver.getTitle(), expectedTitle);
			reportPass("Actual Title : " + driver.getTitle() + " - contains Expected String : " + expectedString);
			reportPass("TestCase No: "+testCaseNumber+" is executed successfully!!..");
		} catch (Exception e) {
			reportPass("TestCase No: "+testCaseNumber+" execution has been failed!!..");
			reportFail(e.getMessage());
		}

	}
	

	// Report log for failing cases
	public void reportFail(String report) {
		logger.log(Status.FAIL, "Test Case execution failed");
		logger.log(Status.FAIL, report);

	}

	// Report log for passing cases
	public void reportPass(String report) {
		logger.log(Status.PASS, report);
	}

	// Report log for Information
	public void reportInfo(String report) {
		logger.log(Status.INFO, report);
	}

	public void closeBrowser() {

		driver.quit();
		reportPass("Browser has been closed");
	}
	
	public void veriyElementIsDisplayed(WebElement webElement) {
		try {
			Thread.sleep(1000);
			if (webElement.isDisplayed()) {
				reportPass(webElement.getText() + " Element is Displayed");
			} else {
				reportFail(webElement.getText() + " Element is not appeared");
			}

		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		
		

	}
	public CartPage cartAdd() throws InterruptedException {
		By cartButton = By.xpath("//button[@class='_2KpZ6l _2U9uOA _3v1-ww']");
		scrollDown();
		driver.findElement(cartButton).click();
		CartPage cartPage = new  CartPage(driver, logger);
		PageFactory.initElements(driver, cartPage);
		return cartPage;
	}

	public void transferOutput(String data, String fileName) throws IOException {
		
		File f = new File(System.getProperty("user.dir")+"/Output/"+fileName+".txt");
		FileUtils.writeStringToFile(f, data , Charset.defaultCharset());
	 
	}
	
public static String[][] getInvalid() throws EncryptedDocumentException, IOException, InvalidFormatException {
		
		InputStream fin=new FileInputStream(System.getProperty("user.dir")+"./TestData/TestDataInvalid.xlsx");
		Workbook wb=WorkbookFactory.create(fin);
		Sheet sh1=wb.getSheetAt(0);
		Row r1=sh1.getRow(1);
		Cell c1=r1.getCell(0);
		Cell c2=r1.getCell(1);
		String s1 = c1.getStringCellValue();
		String s2 = c2.getStringCellValue();

		String[][] data2={{s1,s2}};
		return data2;
   }

public void scrollDown() throws InterruptedException {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollBy(0,350)", "");
	Thread.sleep(2000);
}

public void takeScreenShotOnFailure() {
	TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
	File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);

	File destFile = new File(System.getProperty("user.dir") + "/Screenshots/" + DateUtil.getTimeStamp() + ".png");
	try {
		FileUtils.copyFile(sourceFile, destFile);
		logger.addScreenCaptureFromPath(System.getProperty("user.dir") + "/Screenshots/" + DateUtil.getTimeStamp() + ".png");

	} catch (IOException e) {
		e.printStackTrace();
	}

}

public WebElement waitForElement(By Path) {
	
	Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver)							
			.withTimeout(Duration.ofSeconds(9))
			.pollingEvery(Duration.ofSeconds(3))		
			.ignoring(NoSuchElementException.class);
	WebElement element =  wait1.until(ExpectedConditions.presenceOfElementLocated(Path));
	return element;
	
}

}
