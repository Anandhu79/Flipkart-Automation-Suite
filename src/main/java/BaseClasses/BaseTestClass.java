package BaseClasses;

import java.time.Duration;
import java.util.Properties;
import java.io.FileInputStream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ExtentReportManager;

public class BaseTestClass {
	
	public  WebDriver driver;
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public static ExtentTest logger;
	public static Properties prop;
	public String expectedCartPageTitle = "Shopping Cart";
	public String expectedPetrolVehiclesPageTitle = "Best Deals on Petrol Vehicle";
	public String expectedElectricVehiclesPageTitle = "Electric Scooter";
	public String expectedTravelPageTitle = "Flight Booking";
	public String expectedSearchResultsPageTitle = "Iphone 14";
	public String expectedSearchResultsPageTitle2 = "Apple";
	public String expectedFlipkartHomePageTitle = "Online Shopping Site";

	/****************** Invoke Browser ***********************/
	public void invokeBrowser() {

		prop = new Properties();

		try {
			prop.load(new FileInputStream(System.getProperty("user.dir")+"./config/config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Chrome

		if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			ChromeOptions option = new ChromeOptions();
			option.addArguments("start-maximized");
			option.addArguments("--remote-allow-origins=*");
			option.addArguments("--disable-blink-features=AutomationControlled");
			option.addArguments("--disable-notifications");// Disabling any notifications
			driver = new ChromeDriver(option);

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}

	@AfterMethod
	public void flushReports() {
	
		report.flush();
		
	}
	
	public void openURL(String url) {

		logger.log(Status.INFO, "Opening the Website");
		driver.get(prop.getProperty(url));
		logger.log(Status.PASS, "Successfully Opened Flipkart ");

	}

}
