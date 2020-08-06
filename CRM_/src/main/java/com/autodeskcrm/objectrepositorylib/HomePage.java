package com.autodeskcrm.objectrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * 
 * @author indudhara
 *
 */
public class HomePage {

	/* Declaration of WebElements */
	@FindBy(linkText="Organizations")
	private WebElement organizationTab;
	
	@FindBy(linkText="Contacts")
	private WebElement contactsTab;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImage;
	
	@FindBy(xpath="//a[text()='Sign Out']")
	private WebElement signout;
	
	
	/* initialization of WebElements */
	public HomePage(WebDriver driver)	{
	PageFactory.initElements(driver, this);
	}
		
	/* Utilization of WebElements */
	public void clickOnOrganization()	{
	organizationTab.click();
	}
		
	public void clickOnContactsTab()	{
	contactsTab.click();
	}
	
	public void clickOnSignout()	{
		signout.click();
	}
	
	public WebElement getOrganizationTab()	{
		return organizationTab;
	}
	
	public WebElement getContactsTab()	{
		return contactsTab;
	}
	
	public WebElement getAdminImg()	{
		return adminImage;
	}
	
	public WebElement getSignOut()	{
		return signout;
	}
}
