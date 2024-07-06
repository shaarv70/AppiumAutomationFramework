package com.saucelabs.listeners;

import java.io.IOException;
import java.util.Objects;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.saucelabs.enums.Config;
import com.saucelabs.utils.PropertyUtils;
import com.saucelabs.utils.ScreenshotUtils;
import com.saucelabs.utils.VideoUtils;

public class Listeners implements ITestListener, ISuiteListener{

	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		ISuiteListener.super.onStart(suite);
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		ISuiteListener.super.onFinish(suite);
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		if(Objects.nonNull(result.getThrowable()))
		{
			 result.getThrowable().printStackTrace();
		}
		
	    if(PropertyUtils.getProperty(Config.SAVESCREENSHOTS).equals("true")) {
		try {
			ScreenshotUtils.getScreenshot(result);
		} catch (IOException e) {
			
			e.printStackTrace();
		}}
	
	 if(PropertyUtils.getProperty(Config.SAVEVIDEOS).equals("true")) {
			try {
				VideoUtils.getVideo(result);
			} catch (IOException e) {
				
				e.printStackTrace();
			}}}
	
		
		

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
	}
	
	
	
	

}
