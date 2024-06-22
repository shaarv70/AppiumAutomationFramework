package com.saucelabs.factories;

import static com.saucelabs.constants.FrameworkConstants.getApppath;
import static com.saucelabs.utils.PropertyUtils.getProperty;

import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.saucelabs.enums.Config;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;



public class DriverFactory {
	
	private DriverFactory() {}
	
	
	
	
	public static AppiumDriver initialize(String emul,String platformname, String devicename, String udid)
	{
		AppiumDriver driver=null;
		try {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("platformName",platformname);
			caps.setCapability("deviceName", devicename);
			caps.setCapability("automationName",getProperty(Config.AUTOMATIONNAME));
			if(emul.equalsIgnoreCase("true")) {
			caps.setCapability("udid", udid);
			caps.setCapability("adv", devicename);
			}
			//else{	caps.setCapability("udid", udid);}//here udid of realdevice will be fetched from testng.xml
		//	caps.setCapability("appPackage",getProperty(Config.APPPACKAGE));
		//	caps.setCapability("appActivity",getProperty(Config.APPACTIVITY));
		//	caps.setCapability("app",getApppath());
			caps.setCapability("newCommandTimeout",400);

			URL url = new URL(getProperty(Config.APPIUMURL));
		    driver= new AndroidDriver(url,caps); 

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return driver;
    }
	

}
