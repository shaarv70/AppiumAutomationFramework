package com.saucelabs.driver;

import java.util.Objects;

import io.appium.java_client.AppiumDriver;

public class DriverManager {


     private DriverManager() {}

	static ThreadLocal<AppiumDriver> dr= new ThreadLocal<>();

	public static AppiumDriver getDriver()
	{
		if(Objects.nonNull(dr))
		{
			return dr.get();
		}
		return null;	
	}

	static void setDriver(AppiumDriver ref ) {
		if(Objects.nonNull(ref)) {
			dr.set(ref);
		}
	}

	static void unload()
	{
		dr.remove();
	}




}
