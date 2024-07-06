package com.saucelabs.pages;

import org.openqa.selenium.WebElement;

import com.saucelabs.enums.WaitStrategy;

import io.appium.java_client.pagefactory.AndroidFindBy;



public final class LoginPage extends BasePage {


	
	 @AndroidFindBy(accessibility ="test-Username" )
	 private static WebElement username;
	 
	 @AndroidFindBy(accessibility= "test-Password")
	 private static WebElement password;
	 
	 @AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]")
     private static WebElement lgnBtn;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@text=\"Username and password do not match any user in this service.\"]")
	 private static WebElement mess;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@text=\"PRODUCTS\"]")
	 private static WebElement homepagetitle;
	 
	 
	 
	 public LoginPage setUsername(String name)
	 {
		sendkeys(WaitStrategy.VISIBILITY,username,name,"username");
		return this;
	 }
	 
	 public LoginPage setPassword(String pass)
	 {
		 sendkeys(WaitStrategy.NONE,password,pass,"pasword");
		 return this;
	 }
	 
	 public LoginPage clickLogin() 
	 {
		 click(WaitStrategy.VISIBILITY, lgnBtn,"Login Button");
		 return this;
	 }
	 
	 
	public void assertLoginNotification(String exp)
	 {
		String actual= mess.getText();
		assertFunction(actual, exp);
	 }
	
	public ProductsPage assertProductPageTitle()
	{
		return new ProductsPage();
	}
     
	public ProductsPage login(String user,String pass)
	{
		setUsername(user);
		setPassword(pass);
		clickLogin();
		return new ProductsPage();
		
	}
	
	
	
	
	

}
