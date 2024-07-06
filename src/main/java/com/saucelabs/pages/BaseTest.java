package com.saucelabs.pages;



import static com.saucelabs.utils.PropertyUtils.getProperty;
import java.lang.reflect.Method;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import com.saucelabs.driver.Driver;
import com.saucelabs.driver.DriverManager;
import com.saucelabs.enums.Config;
import com.saucelabs.utils.PropertyUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.screenrecording.CanRecordScreen;



public class BaseTest {
	
 
	public static Logger log;
	
	@BeforeTest
	@Parameters({"emulator","platformName","deviceName","udid"})
	protected  void setup(String eml,String pname, String dname, String ud) 
	{
		PropertyUtils.initializeProperties();
		Driver.initializeDriver(eml,pname,dname, ud);
    }
	
	@BeforeClass
	protected void initializeLogger()
	{
		log=LogManager.getLogger(this.getClass());	
	}
		
	
	@AfterTest
	 protected void tearDown()
	 {
		 Driver.flush();
		
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
	
}
