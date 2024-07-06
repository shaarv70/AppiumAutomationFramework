package com.saucelabs.utils;

import java.io.IOException;

import org.testng.ITestResult;

import com.saucelabs.driver.DriverManager;

import io.appium.java_client.screenrecording.CanRecordScreen;

public class VideoUtils {
	
	
	public static void getVideo(ITestResult result ) throws IOException
	{
		String rec=((CanRecordScreen)DriverManager.getDriver()).stopRecordingScreen();
		try {
			MediaLocationUtils.directoryUtils(result,"video",rec,".mp4");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	

}}
