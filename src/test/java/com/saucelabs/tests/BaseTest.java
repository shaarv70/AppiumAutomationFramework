package com.saucelabs.tests;


import static com.saucelabs.utils.LoggingUtils.log;
import static com.saucelabs.utils.PropertyUtils.getProperty;
import static com.saucelabs.factories.DriverFactory.checkIfAppiumServerIsRunnning;
import java.io.File;
import java.lang.reflect.Method;
import org.slf4j.MDC;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.saucelabs.driver.Driver;
import com.saucelabs.driver.DriverManager;
import com.saucelabs.enums.Config;
import com.saucelabs.factories.DriverFactory;
import com.saucelabs.utils.PropertyUtils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;



public class BaseTest {



	private static AppiumDriverLocalService server;


	@BeforeSuite
	public void initialize() throws AppiumServerHasNotBeenStartedLocallyException, Exception 
	{
		MDC.put("ROUTINGKEY", "ServerLogs");
		server=DriverFactory.getAppiumService();
		if(!(checkIfAppiumServerIsRunnning(4723))) {
			server.start();
		//	server.clearOutPutStreams();//if we do not want to show server logs to console
		    log().info("APPIUM SERVER STARTED");
		}
		
		else
		{
			 log().info("Server is already running");
		}

		PropertyUtils.initializeProperties();
	}




	@BeforeTest
	@Parameters({"emulator","platformName","deviceName","udid"})
	protected  void setup(String eml,String pname, String dname, String ud) 
	{
		Driver.initializeDriver(eml,pname,dname, ud);
		String strFile = "logs" + File.separator + ud + "_" + dname;
		File logFile = new File(strFile);
		if (!logFile.exists()) {
			logFile.mkdirs();
		}

		MDC.put("ROUTINGKEY", strFile);
	}




	@BeforeMethod
	protected  void launchApp(Method m)
	{

		((AndroidDriver)(DriverManager.getDriver())).activateApp(getProperty(Config.APPPACKAGE));
		((CanRecordScreen)DriverManager.getDriver()).startRecordingScreen();


	}

	@AfterMethod
	protected  void closeApp(ITestResult result)
	{

		((AndroidDriver)(DriverManager.getDriver())).terminateApp(getProperty(Config.APPPACKAGE));


	}

	@AfterTest
	protected void tearDown()
	{
		Driver.flush();

	}


	@AfterSuite
	protected void terminate()
	{
		server.stop();
		System.out.println("APPIUM SERVER STOPPED");
	}

}
