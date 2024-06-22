package com.saucelabs.pages;


import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.saucelabs.driver.DriverManager;
import com.saucelabs.enums.WaitStrategy;
import com.saucelabs.factories.ExplicitWaitFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;



public class BasePage {
	
	
	
	public BasePage()
	{
		PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()),this);
	}
	
	
	protected void click(WaitStrategy wait,WebElement ele)
	{
	      ExplicitWaitFactory.explicitWait(wait,ele).click();
	}
	
	protected void sendkeys(WaitStrategy wait,WebElement ele,String text)
	{
		ExplicitWaitFactory.explicitWait(wait,ele).sendKeys(text);
	}
	
	protected void scroll(String ele)
	{
		
		DriverManager.getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" +ele +"\"))"));
	}
	
	
	protected void assertMess(String actual,String expected)
	 {
		  assertEquals(actual,expected);
	 }
	

}
