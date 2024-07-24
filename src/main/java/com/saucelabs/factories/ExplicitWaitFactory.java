package com.saucelabs.factories;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.saucelabs.constants.FrameworkConstants;
import com.saucelabs.driver.DriverManager;
import com.saucelabs.enums.WaitStrategy;




public final class ExplicitWaitFactory {
	
	private ExplicitWaitFactory() {}
	
	
	public static WebElement explicitWait(WaitStrategy wait,WebElement ele)
	{   WebElement element=null;
		
		if(wait==WaitStrategy.CLICKABLE)
		{
			element= new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(FrameworkConstants.getExplicitwait())).
			until(ExpectedConditions.elementToBeClickable(ele));
		}
	
		else if(wait==WaitStrategy.VISIBILITY)
		{
			element= new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(FrameworkConstants.getExplicitwait())).
			until(ExpectedConditions.visibilityOf(ele));
		}
		
		else if(wait==WaitStrategy.NONE)
		{
			element= ele; 
		}
		
		return element;
		
	}
	
}


