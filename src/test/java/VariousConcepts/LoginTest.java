package VariousConcepts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {

	WebDriver driver;

	By userNameField = By.xpath("//input[@id='username']");
	By passwordField = By.xpath("//*[@id=\"password\"] ");
	By signinButtonField = By.xpath("/html/body/div/div/div/form/div[3]/button");
	By dashboardHeaderField = By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div/h2");
	By customerMenuField = By.xpath("//*[@id=\"side-menu\"]/li[3]/a/span[1]");
	By addCustomerMenuField = By.xpath("//*[@id=\"side-menu\"]/li[3]/ul/li[1]/a");
	By addCustomerHeaderField = By.xpath("//*[@id=\"page-wrapper\"]/div[3]/div[1]/div/div/div/div[1]/h5");
	By fullNamerField = By.xpath("//*[@id=\"account\"]");
	By companyDropdownField = By.xpath("//*[@id=\"cid\"]");
	By companyNameField = By.xpath("//*[@id=\"cid\"]");
	By emailField = By.xpath("//*[@id=\"email\"]");
	By phoneField = By.xpath("//*[@id=\"phone\"]");
	By SelectField = By.xpath("//select[@id=\"country\"]");
	
	

//	Test data or mockdata

	String USER_NAME = "demo@techfios.com";
	String PASSWORD = "abc123";
	String DASHBOARD_HEADER_TEXT = "Dashboard";
	String ADDCUSTOMER_HEADER_TEXT = "Add Contact";
	String FULL_NAME = "Selenium Feb2023";
	String COMPANY = "Techfios";
	String EMAIL = "demo@techfios.com";
	String PHONE = "555-555-5555";
	String SELECT = "demo@techfios.com";
	
	
	
	@BeforeMethod
	public void init() {

		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		driver = new ChromeDriver();

//		System.setProperty("webdriver.edge.driver", "driver\\msedgedriver.exe");
//		driver = new EdgeDriver();
//		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://techfios.com/billing/?ng=login/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void login() {

		driver.findElement(userNameField).sendKeys(USER_NAME);;
		driver.findElement(passwordField).sendKeys(PASSWORD);
		driver.findElement(signinButtonField).click();

		// Assert.assertEquals(actual, expected, message)
		Assert.assertEquals(driver.findElement(dashboardHeaderField).getText(), DASHBOARD_HEADER_TEXT ,
				"Dashboard page not found");
	}

	@Test
	public void addCustomer() throws InterruptedException{
 

		login();
		Thread.sleep(2000);
		driver.findElement(customerMenuField).click();
		driver.findElement(addCustomerMenuField);
		Assert.assertEquals(driver.findElement(dashboardHeaderField).getText(), DASHBOARD_HEADER_TEXT ,
				"Dashboard page not found");

		driver.findElement(fullNamerField).sendKeys(FULL_NAME);
		Select sel = new Select (driver.findElement(companyDropdownField));
		sel.deselectByVisibleText(COMPANY);
		
		
		
	}

//	@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();

	}
}