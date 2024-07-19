package com.saucelabs.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.saucelabs.constants.FrameworkConstants;
import com.saucelabs.listeners.Listeners;
import com.saucelabs.utils.TestPropertiesUtils;


public final class ExtentReport {

	private ExtentReport(){}
	private static ExtentReports extent; 


	public static void initReports() 
	{
		if(Objects.isNull(extent)) {

			extent = new ExtentReports();
			ExtentSparkReporter spark= new ExtentSparkReporter(FrameworkConstants.getNEWFILEPATH());
			extent.attachReporter(spark);
			spark.config().setTheme(Theme.STANDARD);
			spark.config().setDocumentTitle("APPIUM Report");
			spark.config().setReportName("Automation Report");

		}}


	public static void flushReports() 
	{
		if(Objects.nonNull(extent)) {
			extent.flush();
		}

		ExtentManager.unload();

		try { 
			Desktop.getDesktop().browse(new File(FrameworkConstants.getNEWFILEPATH()).toURI()); 
		}

		catch (IOException e) {

			e.printStackTrace(); 

		}

	}

	public static void createTest(String testcasename)
	{

		ExtentManager.setTest(extent.createTest(testcasename));	

	}

	public static void addAuthors(String[] authors)
	{
		for(String temp:authors)
		{
			ExtentManager.getTest().assignAuthor(temp);
		}
	}

	public static void addCategory()
	{
		String udid=TestPropertiesUtils.getCONFIGMAP().get("udid");
		ExtentManager.getTest().assignCategory(TestPropertiesUtils.getCONFIGMAP().get("udid"));

	}


}
