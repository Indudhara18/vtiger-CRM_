package com.autodeskcrm.orgtest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.autodeskcrm.genericUtils.BaseClass;

/**
 * 
 * @author indudhara
 *
 */
public class DeleteOrganizationAndVerifyDeleteTest extends BaseClass {

	
	@Test
	public void deleteOrgAndVerify() throws Throwable	
	{		
		/* read test script data from excel file */
		String orgName = excelL.readExcelData("org", 1, 2)+"_"+webutils.getRandomNo();
		String orgType= excelL.readExcelData("org", 1, 3);
		String orgIndustry = excelL.readExcelData("org", 1, 4);
		
		
		
		/*  navigate to Organization page */
		
		WebElement org = driver.findElement(By.linkText("Organizations"));
		org.click();
		
		/*  navigate to create new Organization page */
		
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		/*  Create Organization */
		
		driver.findElement(By.name("accountname")).sendKeys(orgName);
	
		WebElement  swb1 = driver.findElement(By.name("accounttype"));
	    webutils.select(swb1, orgType);
				
		WebElement  swb2 = driver.findElement(By.name("industry"));
		webutils.select(swb2, orgIndustry);
				
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		Thread.sleep(3000);
	
		/* navigate to Organization page */
		
		WebElement orgTab =driver.findElement(By.linkText("Organizations"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", orgTab);

		/*  Search for Organization to delete  */
		driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(orgName);
		WebElement dd = driver.findElement(By.xpath("(//select[@name='search_field'])[1]"));
		webutils.select(dd, "Organization Name");
		driver.findElement(By.xpath("//input[@name='submit']")).click();
		
		/*  Delete searched Organization  */
		String orgToDelete = driver.findElement(By.xpath("//div[@id='ListViewContents']//table//tr[last()]//td[last()-7]/..//td[3]")).getText();
		driver.findElement(By.xpath("//div[@id='ListViewContents']//table//tr[last()]//td[last()-7]")).click();
		driver.findElement(By.xpath("(//input[@class='crmbutton small delete'])[1]")).click();
		webutils.alertOk(driver);
		driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(orgName);
		WebElement dd1 = driver.findElement(By.xpath("(//select[@name='search_field'])[1]"));
		webutils.select(dd1, "Organization Name");
		driver.findElement(By.xpath("//input[@name='submit']")).click();
		WebElement errorMSg = driver.findElement(By.xpath("//span[contains(text(),'No Organization Found !')]"));
		if (errorMSg.isDisplayed()) {
			System.out.println("Organization "+orgName+ " is deleted --> " + errorMSg.getText());	
		}
	}
}
