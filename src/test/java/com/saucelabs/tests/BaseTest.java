package com.saucelabs.tests;

import static com.saucelabs.utils.PropertyUtils.getProperty;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.saucelabs.driver.Driver;
import com.saucelabs.driver.DriverManager;
import com.saucelabs.enums.Config;

import io.appium.java_client.android.AndroidDriver;

public class BaseTest {
	
 
	
	
	@BeforeClass
	@Parameters({"emulator","platformName","deviceName","udid"})
	protected void setup(String eml,String pname, String dname, String ud) 
	{
		
    Driver.initializeDriver(eml,pname,dname, ud);
    }
		
	
	@AfterClass
	 protected void tearDown()
	 {
		 Driver.flush();
		
	 }
	
	@BeforeMethod
	protected void launchApp()
	{
		((AndroidDriver)(DriverManager.getDriver())).activateApp(getProperty(Config.APPPACKAGE));
		
		
	}
	
	@AfterMethod
	protected void closeApp() throws InterruptedException
	{
		
		((AndroidDriver)(DriverManager.getDriver())).terminateApp(getProperty(Config.APPPACKAGE));
	}
	
}
