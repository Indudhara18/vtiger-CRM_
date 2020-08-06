package com.autodeskcrm.contacttest;

import org.openqa.selenium.By;
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
public class CreateContactWithOrganizationName extends BaseClass {

	@Test
	public void createContactWithOrgtest() throws Throwable {
		
		/* read test script data from excel file */
		String orgName = excelL.readExcelData("contact", 1, 2)+"_"+webutils.getRandomNo();
		String orgType= excelL.readExcelData("contact", 1, 3);
		String orgIndustry = excelL.readExcelData("contact", 1, 4);
		String contactName = excelL.readExcelData("contact", 1, 5)+"-"+webutils.getRandomNo();
		
		
		
		/*  navigate to Organization page */
		
		driver.findElement(By.linkText("Organizations")).click();
			
		/*  navigate to create new Organization page */
		
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		/*  Create Organization */
		
		driver.findElement(By.name("accountname")).sendKeys(orgName);
	
		WebElement  swb1 = driver.findElement(By.name("accounttype"));
	    webutils.select(swb1, orgType);
				
		WebElement  swb2 = driver.findElement(By.name("industry"));
		webutils.select(swb2, orgIndustry);
				
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		/*  verify the Organization */
		
		String actualOrgName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		Assert.assertTrue(actualOrgName.contains(orgName));
		
		
		/*  Navigate to Contact page */
		driver.findElement(By.linkText("Contacts")).click();
		
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
		
		/* verify the Organization */
		String actualconatct = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		Assert.assertTrue(actualconatct.contains(contactName));
		
	}
	
}
