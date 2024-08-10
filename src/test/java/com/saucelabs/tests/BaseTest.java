package com.saucelabs.tests;


import static com.saucelabs.utils.PropertyUtils.getProperty;

import java.time.Duration;

import org.slf4j.MDC;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.saucelabs.constants.FrameworkConstants;
import com.saucelabs.driver.Driver;
import com.saucelabs.driver.DriverManager;
import com.saucelabs.enums.Config;
import com.saucelabs.exceptions.DriverInvocationFailedException;
import com.saucelabs.factories.DriverFactory;
import com.saucelabs.utils.JsonUtils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.appmanagement.AndroidTerminateApplicationOptions;



public class BaseTest {




	@BeforeTest
	@Parameters({"emulator","platformName","deviceName"})
	protected  void setup(String eml,String pname, String dname) 
	{
		MDC.put("ROUTINGKEY", FrameworkConstants.getNewLogFilePath(dname));
		
		try {
			Driver.initializeDriver(eml,pname,dname);
		}

		catch(Exception e)
		{
			e.printStackTrace();
			throw new DriverInvocationFailedException("Failed to invoke driver",e);
		}

		JsonUtils.initializeTestData();
		
	}


	@BeforeMethod(onlyForGroups = {"WithoutDeepLinks"})
	protected void launchApp() {

		((AndroidDriver)(DriverManager.getDriver())).activateApp(getProperty(Config.APPPACKAGE));

	}



	@BeforeMethod(onlyForGroups = {"DeepLinks"}) 
	protected void launchAppWithDeepLink() {

		DriverFactory.initialize("swaglabs://swag-overview/0,1");

	}



	@AfterMethod
	protected  void closeApp()
	{

		((AndroidDriver)(DriverManager.getDriver())).terminateApp(getProperty(Config.APPPACKAGE),
				new AndroidTerminateApplicationOptions().withTimeout(Duration.ofSeconds(5)));


	}

	@AfterTest
	protected void tearDown()
	{
		Driver.flush();

	}



}
