package com.autodeskcrm.contacttest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.autodeskcrm.genericUtils.BaseClass;
import com.autodeskcrm.genericUtils.ExcelLib;
import com.autodeskcrm.genericUtils.FileLib;
import com.autodeskcrm.genericUtils.WebDriverUtils;

/**
 * 
 * @author indudhara
 *
 */
public class CreateContactAndDeleteContactAswellAsOrg extends BaseClass {

	@Test
	public void deletContact() throws Throwable	{
		
		/* read test script data from excel file */
		String orgName = excelL.readExcelData("contact", 1, 2)+"_"+webutils.getRandomNo();
		String orgType= excelL.readExcelData("contact", 1, 3);
		String orgIndustry = excelL.readExcelData("contact", 1, 4);
		String contactName = excelL.readExcelData("contact", 1, 5)+"-"+webutils.getRandomNo();
		
		
		/*  navigate to Organization page */
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		/*  Create Organization */
		driver.findElement(By.name("accountname")).sendKeys(orgName);
	
		WebElement  swb1 = driver.findElement(By.name("accounttype"));
	    webutils.select(swb1, orgType);
				
		WebElement  swb2 = driver.findElement(By.name("industry"));
		webutils.select(swb2, orgIndustry);
				
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		Thread.sleep(2000);
		
		/*  Navigate to Contact page */
		
		WebElement ContactTab = driver.findElement(By.linkText("Contacts"));
//		JavascriptExecutor executor = (JavascriptExecutor)driver;
//		executor.executeScript("arguments[0].click();", ContactTab);
		webutils.waitAndClickWebElement(ContactTab);
//		Thread.sleep(3000);
		
		/*  Navigate to create new Contact page */
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		/*  Create new Contact  */
		driver.findElement(By.name("lastname")).sendKeys(contactName);
		/* Identify and Click On Organization name look up icon  */
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
	
		/* handle child browser */
	    webutils.switchToNewWindow(driver, "specific_contact_account_address");
		
		driver.findElement(By.name("search_text")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.linkText(orgName)).click();
		
		/* come back to parent Window */
		webutils.switchToNewWindow(driver, "Administrator - Contacts");
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		Thread.sleep(3000);
		
		/*  Navigate to Contact page */
		WebElement ContactTab1 = driver.findElement(By.linkText("Contacts"));
		JavascriptExecutor executor1 = (JavascriptExecutor)driver;
		executor1.executeScript("arguments[0].click();", ContactTab1);
		Thread.sleep(3000);
		
		/*  Create Contact */
		driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(contactName);
		WebElement sel1 = driver.findElement(By.xpath("(//select[@name='search_field'])[1]"));
		webutils.select(sel1, "Last Name");
		driver.findElement(By.xpath("//input[@name='submit']")).click();
		
		/* Delete the Created Contact */
		driver.findElement(By.xpath("//div[@id='ListViewContents']//table//tr[last()]//td[last()-7]/..//td[1]")).click();
		driver.findElement(By.xpath("(//input[@class='crmbutton small delete'])[1]")).click();
		webutils.alertOk(driver);
		Thread.sleep(2000);
		
		/*  Navigate to create Organization Page */
		WebElement orgTab =driver.findElement(By.linkText("Organizations"));
		JavascriptExecutor executor2 = (JavascriptExecutor)driver;
		executor2.executeScript("arguments[0].click();", orgTab);
		Thread.sleep(2000);
//		webutils.alertOk(driver);

		
		/*  Search and delete Organization which was assigned for deleted contact */
		driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(orgName);
		WebElement dd = driver.findElement(By.xpath("(//select[@name='search_field'])[1]"));
		webutils.select(dd, "Organization Name");
		driver.findElement(By.xpath("//input[@name='submit']")).click();
		driver.findElement(By.xpath("//div[@id='ListViewContents']//table//tr[last()]//td[last()-7]/..//td[1]")).click();
		driver.findElement(By.xpath("(//input[@class='crmbutton small delete'])[1]")).click();
		webutils.alertOk(driver);
		Thread.sleep(2000);
	}
}

/* 1) create organization and contact
   2) delete contact and verify
   3) wrt contact orz delete that orgz also and 
	verify in org page  */