package com.saucelabs.utils;

import java.util.Map;

import org.testng.ITestResult;

public class TestPropertiesUtils {
	
	private TestPropertiesUtils() {}
	
	protected static ThreadLocal<Map<String,String>> parameters= new ThreadLocal<>();

	private static void setCONFIGMAP(Map<String,String> cONFIGMAP) {
		parameters.set(cONFIGMAP);
	}

	public  static Map<String,String> getCONFIGMAP() {
		return parameters.get();
	}
	
	public static void  testProperties(ITestResult result)
	{
		Map<String,String> params= null;
		params=result.getTestContext().getCurrentXmlTest().getAllParameters();
		setCONFIGMAP(params);
	}

}
