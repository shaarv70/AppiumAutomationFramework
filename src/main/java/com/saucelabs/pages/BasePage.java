package com.saucelabs.pages;



import static com.saucelabs.utils.LoggingUtils.log;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.saucelabs.driver.DriverManager;
import com.saucelabs.enums.WaitStrategy;
import com.saucelabs.factories.ExplicitWaitFactory;
import com.saucelabs.reports.ExtentLogger;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;



public class BasePage {


      
	public BasePage()
	{
		PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()),this);
	}
	
	



	protected void click(WaitStrategy wait,WebElement ele,String eleName)
	{

	    log().info("Clicking on "+eleName); 
	    ExtentLogger.pass(eleName+" clicked",true);
		ExplicitWaitFactory.explicitWait(wait,ele).click();
	}

	protected void sendkeys(WaitStrategy wait,WebElement ele,String text, String eleName)
	{
		log().info("Entering the "+eleName); 
		ExtentLogger.pass("Entered the "+ eleName, true );
		ExplicitWaitFactory.explicitWait(wait,ele).sendKeys(text);
	}

	protected void scroll(String ele, String eleName)
	{

		log().info("Doing Scrolling for "+eleName);
		ExtentLogger.pass("Doing scrooling for the "+ eleName, true );
		DriverManager.getDriver().findElement(AppiumBy.androidUIAutomator
				("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" +ele +"\"))"));
	}


	protected void assertFunction(String actual,String expected)
	{
		log().info("Performing Assertion");  
		ExtentLogger.pass("Performing Assertion",true );
		SoftAssert s= new SoftAssert();
		s. assertEquals(actual,expected);
		s.assertAll();

	}


}
