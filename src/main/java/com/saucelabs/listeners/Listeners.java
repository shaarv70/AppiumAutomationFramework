package com.saucelabs.listeners;

import static com.saucelabs.factories.DriverFactory.checkIfAppiumServerIsRunnning;
import static com.saucelabs.utils.LoggingUtils.log;

import java.util.Arrays;
import java.util.Objects;

import org.slf4j.MDC;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.saucelabs.annotations.FrameworkAnnotation;
import com.saucelabs.driver.DriverManager;
import com.saucelabs.enums.Config;
import com.saucelabs.exceptions.AppiumDriverLocalServiceException;
import com.saucelabs.exceptions.ExtentReportInvocationFailedException;
import com.saucelabs.factories.DriverFactory;
import com.saucelabs.reports.ExtentLogger;
import com.saucelabs.reports.ExtentReport;
import com.saucelabs.utils.PropertyUtils;
import com.saucelabs.utils.ScreenshotUtils;
import com.saucelabs.utils.TestPropertiesUtils;
import com.saucelabs.utils.VideoUtils;

import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Listeners implements ITestListener, ISuiteListener{

	private  AppiumDriverLocalService server;


	@Override
	public void onStart(ISuite suite) {

		MDC.put("ROUTINGKEY", "ServerLogs");
		server=DriverFactory.getAppiumService();
	
			try {
				if(!(checkIfAppiumServerIsRunnning(4723))) {
					server.start();
					server.clearOutPutStreams();//if we do not want to show server logs to console
					log().info("APPIUM SERVER STARTED");
				}
				else {
					log().info("Server is already running");
				}
			} catch (Exception e) {
				
			throw new AppiumDriverLocalServiceException("Failed to check status of AppiumDriverLocalService" ,e);
			}
		
	 
		PropertyUtils.initializeProperties();
		ExtentReport.initReports();
	}

	@Override
	public void onFinish(ISuite suite) {
		
		MDC.put("ROUTINGKEY", "ServerLogs");
		ExtentReport.flushReports();
		server.stop();
		log().info("APPIUM SERVER STOPPED");

	}

	@Override
	public void onTestStart(ITestResult result) {

		try {
		ExtentReport.createTest(result.getMethod().getMethodName());	
		ExtentReport.addAuthors(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class).author());
		TestPropertiesUtils.testProperties(result);
		ExtentReport.addCategory();   
		}
		
		catch(RuntimeException e)
		{
			throw new ExtentReportInvocationFailedException("Failed to invoke Extent Report",e);
		}
		((CanRecordScreen)DriverManager.getDriver()).startRecordingScreen();

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		ExtentLogger.pass(result.getMethod().getMethodName()+" is passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		if(Objects.nonNull(result.getThrowable()))
		{
			result.getThrowable().printStackTrace();
		}

		if(PropertyUtils.getProperty(Config.SAVESCREENSHOTS).equals("yes")) {
			
				ScreenshotUtils.getScreenshot(result);
			}

		if(PropertyUtils.getProperty(Config.SAVEVIDEOS).equals("yes")) {
		
				VideoUtils.getVideo(result);
		}


		ExtentLogger.fail(result.getMethod().getMethodName()+" is failed",true);
		ExtentLogger.fail(result.getThrowable().toString());
		ExtentLogger.fail(Arrays.toString(result.getThrowable().getStackTrace()));


	}




	@Override
	public void onTestSkipped(ITestResult result) {

		ExtentLogger.skipped(result.getMethod().getMethodName()+" is skipped", true);
	}

	/*
	 * @Override public void onTestFailedButWithinSuccessPercentage(ITestResult
	 * result) {
	 * 
	 * ITestListener.super.onTestFailedButWithinSuccessPercentage(result); }
	 * 
	 * @Override public void onTestFailedWithTimeout(ITestResult result) { // TODO
	 * Auto-generated method stub
	 * ITestListener.super.onTestFailedWithTimeout(result); }
	 * 
	 * @Override public void onStart(ITestContext context) {
	 * 
	 * ITestListener.super.onStart(context);
	 * 
	 * }
	 * 
	 * @Override public void onFinish(ITestContext context) { // TODO Auto-generated
	 * method stub ITestListener.super.onFinish(context); }
	 * 
	 */



}
