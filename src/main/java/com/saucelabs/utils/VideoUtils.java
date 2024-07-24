package com.saucelabs.utils;

import org.testng.ITestResult;

import com.saucelabs.constants.FrameworkConstants;
import com.saucelabs.driver.DriverManager;

import io.appium.java_client.screenrecording.CanRecordScreen;

public class VideoUtils {
	
	private VideoUtils() {}
	
	public static void getVideo(ITestResult result )
	{
		String rec=((CanRecordScreen)DriverManager.getDriver()).stopRecordingScreen();
		
			MediaLocationUtils.directoryUtils(result,FrameworkConstants.getVideos(),rec,".mp4");
	}
	
}
