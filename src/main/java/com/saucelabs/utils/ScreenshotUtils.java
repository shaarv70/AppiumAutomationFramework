package com.saucelabs.utils;

import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import com.saucelabs.constants.FrameworkConstants;
import com.saucelabs.driver.DriverManager;



public final class ScreenshotUtils {
	
	private ScreenshotUtils() {}
	
	public static void getScreenshot(ITestResult result ) 
	{
		String ss =DriverManager.getDriver().getScreenshotAs(OutputType.BASE64);
        MediaLocationUtils.directoryUtils(result,FrameworkConstants.getScreenshots(),ss,".png");
	}
	
	public static String getScreenshot()
	{
		return DriverManager.getDriver().getScreenshotAs(OutputType.BASE64);
	}
	


}
