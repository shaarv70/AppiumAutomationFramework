package com.saucelabs.reports;


import com.aventstack.extentreports.MediaEntityBuilder;
import com.saucelabs.enums.Config;
import com.saucelabs.utils.PropertyUtils;
import com.saucelabs.utils.ScreenshotUtils;

public final class ExtentLogger {

	private ExtentLogger() {}

	
	public static void pass(String message) {
		
		ExtentManager.getTest().pass(message);
	}
	
	
	public static void  fail(String message) {
		
		ExtentManager.getTest().fail(message);
	}
	
    
	public static void skipped(String message) {
		
		ExtentManager.getTest().skip(message);
	}
	
	public static void pass(String message, boolean needScreenshot )
	{
		if(PropertyUtils.getProperty(Config.PASSEDSTEPSSCREENSHOT).equalsIgnoreCase("yes") && needScreenshot)
		{
		   ExtentManager.getTest().pass(message, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getScreenshot()).build());	
		}
		else
		{
			pass(message);
		}
		
	}
	
	public static void fail(String message, boolean needScreenshot ) 
	{
		if(PropertyUtils.getProperty(Config.FAILEDSTEPSSCREENSHOT).equalsIgnoreCase("yes") && needScreenshot)
		{
		   ExtentManager.getTest().fail(message, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getScreenshot()).build());	
		}
		else
		{
			fail(message);
		}
		
	}
	
	public static void skipped(String message, boolean needScreenshot ) 
	{
		if(PropertyUtils.getProperty(Config.SKIPPEDSTEPSSCREENSHOT).equalsIgnoreCase("yes") && needScreenshot)
		{
		   ExtentManager.getTest().skip(message, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getScreenshot()).build());	
		}
		else
		{
			skipped(message);
		}
		
	}




	
	
}
