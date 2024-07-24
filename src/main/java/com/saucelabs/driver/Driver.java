package com.saucelabs.driver;

import java.util.Objects;

import com.saucelabs.factories.DriverFactory;



public final class Driver {

	private Driver(){}
	
	
	public static void initializeDriver(String emul,String pname, String dname, String ud) 
	{
		if(Objects.isNull(DriverManager.getDriver())) {

		DriverManager.setDriver(DriverFactory.initialize(emul,pname, dname, ud));
		    
		}
		
	}
		
	

	 public static void flush()
	 {
		 if(Objects.nonNull(DriverManager.getDriver())) {
				DriverManager.getDriver().quit();
				DriverManager.unload();
			}
	 }
     
    
	
}
