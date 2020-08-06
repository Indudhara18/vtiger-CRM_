package com.autodeskcrm.objectrepositorylib;
/**
 * 
 * @author indudhara
 *
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.autodeskcrm.genericUtils.FileLib;

public class LoginPage {									/*Rule 1 */
	
	
	/* Declaration of WebElements */
	@FindBy(xpath="//input[@name='user_name']")			/*Rule 2 */
	private WebElement userNameTB;						
	
	@FindBy(xpath="//input[@name='user_password']")
	private WebElement passwordTB;
	
	@FindBy(xpath="//input[@id='submitButton']")
	private WebElement loginBTN;
	
	/* initialization of WebElements */ 
	public LoginPage(WebDriver driver)	{					/*Rule 3 */
		PageFactory.initElements(driver, this);
	}
	
	/* Utilization of WebElements */					/*Rule 4 */
	public void setUsername(String uname){
		userNameTB.sendKeys(uname);
	}
	
	public void setPassword(String pwd){
		passwordTB.sendKeys(pwd);
	}
	
	public void clickOnLogin(){
		loginBTN.click();
	}
	
	public void login() throws Throwable	{
		FileLib flib = new FileLib();
		String un = flib.getPropertyKeyValue("username");
		String pw = flib.getPropertyKeyValue("password");
		userNameTB.sendKeys(un);
		passwordTB.sendKeys(pw);
		loginBTN.click();
	}
														/*Rule 5 : Business logic*/
	public void login(String un, String pw)	{
		userNameTB.sendKeys(un);
		passwordTB.sendKeys(pw);
		loginBTN.click();
	}
	
	public WebElement getUserNameTB()	{
		return userNameTB ;
	}
	
	public WebElement getPasswordTB()	{
		return passwordTB ;
	}
	
	public WebElement getLoginBTN()	{
		return loginBTN ;
	}
}
