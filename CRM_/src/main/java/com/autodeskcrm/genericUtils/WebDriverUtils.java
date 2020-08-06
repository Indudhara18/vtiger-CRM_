package com.autodeskcrm.genericUtils;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 * @author indudhara
 *
 */
public class WebDriverUtils {

	/**
	 * wait for all webelements to load in DOM page
	 * @param driver
	 */
	public void waitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	/**
	 * wait for  visibility of specific element in GUI
	 * @param driver
	 */
	public void waitForElementVisibality(WebDriver driver, WebElement expElemnet) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(expElemnet));
	}
	
	/**
	 * wait for  page title to be available
	 * @param driver
	 */
	public void waitForPageTitle(WebDriver driver, String pageTitle) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.titleContains(pageTitle));
	}
	
	/**
	 * Wait for any WebElement
	 * @param element
	 * @return
	 * @throws InterruptedException
	 */
	public boolean waitForAnyWebElement(WebElement element) throws InterruptedException	{
		boolean flag = false ;
		int count = 0 ;
		while(count < 50)
		{
		try {
			element.isDisplayed() ;
			flag =true ;
			break ; } 
		catch (Exception e) {
			count++ ;
			Thread.sleep(1000);
		}
		}
		return flag ;
	}
	
	/**
	 * wait for WebElement and click on it
	 * @param element
	 * @throws InterruptedException
	 */
	public void waitAndClickWebElement(WebElement element) throws InterruptedException	{
	int count = 0 ;	
	while(count < 50 ) {
	try {
		element.click();
		break;
	} catch (Exception e) {
		count++ ;
		Thread.sleep(500);	}
	}
	}
	
	/**
	 * Select WebElement from drop down by passing Visible text of WebElement
	 * @param element
	 * @param text
	 */
	public void select(WebElement element, String text)	{
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
	}
	
	/**
	 * Select WebElement from drop down by passing index position
	 * @param element
	 * @param text
	 */
	public void select(WebElement element, int index)	{
		Select sel = new Select(element);
		sel.selectByIndex(index);;
	}
	
	/**
	 * Used to switch for child window
	 * @param driver
	 * @param expectedTitle
	 */
	public void switchToNewWindow(WebDriver driver, String expectedTitle)	{
		Set<String> allTabs = driver.getWindowHandles() ;
		for (String tab : allTabs) {
			driver.switchTo().window(tab);
			String actTitle = driver.getTitle();
			if (actTitle.contains(expectedTitle)) {
				break;
			}
		}
	}
	
	/**
	 * Used to click on ok button in alert popup	
	 * @param driver
	 */
	public void alertOk(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	/**
	 * Used to click on cancel button in alert popup	
	 * @param driver
	 */
	public void alertCancel(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * Used to do mouse overing action on WebElement
	 * @param driver
	 * @param element
	 */
	public void moveMouseToElement(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	/**
	 * Used to do right click on WebElement
	 * @param driver
	 * @param element
	 */
	public void rightClick(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.contextClick(element).perform();
	}
	
	/**
	 * Used to do double click on WebElement
	 * @param driver
	 * @param element
	 */
	public void doubleClick(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.doubleClick(element).perform();
	}
	
	/**
	 * Used to generate random numbers
	 * @return
	 */
	public int getRandomNo()	{
		Random ran = new Random();
		int no = ran.nextInt(10000);
		return no ;
	}
	
	/**
	 * Used to switch frame by passing String
	 * @param driver
	 * @param value
	 */
	public void switchToFrame(WebDriver driver , String value)	{
	driver.switchTo().frame(value);
	}
	
	/**
	 * Used to switch frame by passing index
	 * @param driver
	 * @param value
	 */
	public void switchToFrame(WebDriver driver , int index)	{
	driver.switchTo().frame(index);
	}
	
	/**
	 * Used to switch frame by passing WebElement
	 * @param driver
	 * @param value
	 */
	public void switchToFrame(WebDriver driver , WebElement element)	{
	driver.switchTo().frame(element);
	}
	
	/**
	 * Used to switch to immediate parent frame
	 * @param driver
	 * @param value
	 */
	public void switchToImmediateParentFrame(WebDriver driver)	{
	driver.switchTo().parentFrame();
	}
	

	/**
	 * Used to switch back to original parent frame
	 * @param driver
	 * @param value
	 */
	public void switchToParentFrame(WebDriver driver)	{
	driver.switchTo().defaultContent();
	}
	
	/**
	 * Used to execute javaScript 
	 * @param driver
	 * @param script
	 */
	public void executeJavaScript(WebDriver driver, String javascript) {
	JavascriptExecutor js = (JavascriptExecutor) driver ;
	js.executeScript(javascript);
	}
}