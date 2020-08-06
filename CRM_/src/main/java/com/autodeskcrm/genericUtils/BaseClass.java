package com.autodeskcrm.genericUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.autodeskcrm.objectrepositorylib.HomePage;
import com.autodeskcrm.objectrepositorylib.LoginPage;

/**
 * 
 * @author indudhara
 *
 */
public class BaseClass {
	
public	DataBaseLib dblib = new DataBaseLib();
public	ExcelLib excelL = new ExcelLib() ;
public	FileLib flib = new FileLib();
public	WebDriverUtils webutils = new WebDriverUtils() ;
	
	public WebDriver driver = null;
	
/*	@BeforeSuite
	public void connectToDataBase() throws Throwable
	{
		dblib.connectToDB("", "", "");
	}
	
	@AfterSuite
	public void dissconnectfromDataBase() throws SQLException
	{
		dblib.dissconnectFromDB();
	}
	
	@Parameters("browser")
	@BeforeTest
	public void openBrowserForCrossCrowserTstings(String BROWSER) 
	{
		if(BROWSER.equals("chrome")) {
		   driver= new ChromeDriver(); } 
		else if(BROWSER.equals("firefox")) {
			driver = new FirefoxDriver(); }
		else if(BROWSER.equals("ie")) {
				driver = new InternetExplorerDriver(); }
		else {
	    	 driver = new FirefoxDriver();	}
	}
*/
	
	
	
	@BeforeClass
	public void openBrowser() throws Throwable
	{
		String BROWSER = flib.getPropertyKeyValue("browser");
		if(BROWSER.equals("chrome")) {
		   driver= new ChromeDriver(); } 
		else if(BROWSER.equals("firefox")) {
			driver = new FirefoxDriver(); }
		else if(BROWSER.equals("ie")) {
				driver = new InternetExplorerDriver(); }
		else {
	    	 driver = new FirefoxDriver();	}}

	@AfterClass
	public void closeBrowser()
	{
		driver.close();
	}
	
	@BeforeMethod
	public void loginToAplication() throws Throwable
	{
		/* read the data from properties file */
		String URL = flib.getPropertyKeyValue("url");
		String USERNAME = flib.getPropertyKeyValue("username");
		String PASSWORD = flib.getPropertyKeyValue("password");
		
		driver.manage().window().maximize();
		driver.get(URL);
		webutils.waitForPageToLoad(driver);
		
		LoginPage lp = new LoginPage(driver);
		lp.login(USERNAME, PASSWORD);
	}
	
	@AfterMethod
	public void logoutApplication()
	{	
		HomePage hp = new HomePage(driver);
		WebElement adminImG = hp.getAdminImg();
		
        webutils.moveMouseToElement(driver, adminImG);
        hp.clickOnSignout();
	}
	
}
