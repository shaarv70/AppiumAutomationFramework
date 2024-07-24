package com.saucelabs.factories;

import static com.saucelabs.utils.PropertyUtils.getProperty;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.io.ByteStreams;
import com.saucelabs.constants.FrameworkConstants;
import com.saucelabs.driver.DriverManager;
import com.saucelabs.enums.Config;
import com.saucelabs.exceptions.DriverInvocationFailedException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;



public final  class DriverFactory {
	
	
	
	private DriverFactory() {}
	
	
	public static AppiumDriver initialize(String emul,String platformname, String devicename, String udid)
	{
		
		AppiumDriver driver = null;
		try {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("platformName",platformname);
			caps.setCapability("deviceName", devicename);
			caps.setCapability("automationName",getProperty(Config.AUTOMATIONNAME));
			if(emul.equalsIgnoreCase("true")) {
			caps.setCapability("udid", udid);
			caps.setCapability("adv", devicename);
			caps.setCapability("newCommandTimeout",400);
			/*
			 * caps.setCapability("systemPort",sPort);
			 * caps.setCapability("chromeDriverPort",cPort);
			 */
			}
			//else{	caps.setCapability("udid", udid);}//here udid of realdevice will be fetched from testng.xml
		  // caps.setCapability("appPackage",getProperty(Config.APPPACKAGE));
		 // caps.setCapability("appActivity",getProperty(Config.APPACTIVITY));
			//caps.setCapability("app",getApppath());
			
			
			URL url = new URL(getProperty(Config.APPIUMURL));
		    driver= new AndroidDriver(url,caps); 

		}
		catch (NullPointerException | MalformedURLException e)
		{
			throw new DriverInvocationFailedException("Failed to invoke driver" , e);
		}

		return driver;
    }
	
	public static void initialize(String url){
		
		HashMap<String,String> deepUrl= new HashMap<>();
		deepUrl.put("url", url);
		deepUrl.put("package",getProperty(Config.APPPACKAGE));
		DriverManager.getDriver().executeScript("mobile:deepLink", deepUrl);
	}
	
	public static AppiumDriverLocalService getAppiumService()//Another utlity if we want to run appium server with certain properties
	{
		return  AppiumDriverLocalService.buildService(new AppiumServiceBuilder().
				usingDriverExecutable(new File(FrameworkConstants.getDriverexecutable())).
				withAppiumJS(new File(FrameworkConstants.getAppiumjs())).
				usingPort(4723).
				withTimeout(Duration.ofSeconds(10)).
				withArgument(GeneralServerFlag.SESSION_OVERRIDE).
				withLogOutput(ByteStreams.nullOutputStream()).
			    withLogFile(new File("ServerLogs\\server.log")));
	}
	
	public static AppiumDriverLocalService getDefaultService()
	{
		return AppiumDriverLocalService.buildDefaultService();
	}
	
	
	public static boolean checkIfAppiumServerIsRunnning(int port) throws Exception {
	    boolean isAppiumServerRunning = false;
	    ServerSocket socket;
	    try {
	        socket = new ServerSocket(port);
	        socket.close();
	    } catch (IOException e) {
	        isAppiumServerRunning = true;
	    } finally {
	        socket=null;
	    }
	    return isAppiumServerRunning;
	}
	
	
	

	
	
	
	

}
