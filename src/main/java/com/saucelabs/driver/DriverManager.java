package com.saucelabs.driver;

import java.util.Objects;

import io.appium.java_client.AppiumDriver;

public class DriverManager {
	
	


	private static ThreadLocal<AppiumDriver> dr= new ThreadLocal<>();

	public static AppiumDriver getDriver()
	{
		return dr.get();
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
