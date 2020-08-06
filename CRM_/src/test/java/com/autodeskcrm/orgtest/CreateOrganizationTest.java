package com.autodeskcrm.orgtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.autodeskcrm.genericUtils.BaseClass;

/**
 * 
 * @author indudhara
 *
 */
public class CreateOrganizationTest extends BaseClass {

	@Test void createOrganization() throws Throwable	{
		
		/* read test script data from excel file */
		String orgName = excelL.readExcelData("org", 1, 2)+"_"+webutils.getRandomNo();
		String orgType= excelL.readExcelData("org", 1, 3);
		String orgIndustry = excelL.readExcelData("org", 1, 4);

		/*  navigate to Organization page */
		
		driver.findElement(By.linkText("Organizations")).click();
			
		/* Step 4 : navigate to create new Organization page */
		
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		/* Step 5 : Create Organization */
		
		driver.findElement(By.name("accountname")).sendKeys(orgName);
	
		WebElement  swb1 = driver.findElement(By.name("accounttype"));
	    webutils.select(swb1, orgType);
				
		WebElement  swb2 = driver.findElement(By.name("industry"));
		webutils.select(swb2, orgIndustry);
				
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		/* Step 6 : verify the Organization */
		
		String actualOrgName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		Assert.assertTrue(actualOrgName.contains(orgName));
		
	}
}
