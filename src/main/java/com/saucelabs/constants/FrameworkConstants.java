package com.saucelabs.constants;

import java.io.File;

import com.saucelabs.enums.Config;
import com.saucelabs.utils.MediaLocationUtils;
import com.saucelabs.utils.PropertyUtils;

public final class FrameworkConstants {


	private FrameworkConstants() {}


	private static final String RESOURCES=System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources";	
	private static final String APPPATH= RESOURCES+File.separator+"app"+File.separator+"Android.SauceLabs.Mobile.Sample.app.2.7.1.apk";
	private static final String CONFIGFILEPATH=RESOURCES+File.separator+"config.properties";
	private static final String TESTDATA=RESOURCES+File.separator+"testData"+File.separator+"loginUsers.json";
	private static final String STATICTEXT=RESOURCES+File.separator+"staticData"+File.separator+"strings.xml";
	private static final String SCREENSHOTS= System.getProperty("user.dir")+File.separator+"Screenshots";
	private static final String VIDEOS= System.getProperty("user.dir")+File.separator+"Videos";
    private static final String DRIVEREXECUTABLE="C:"+File.separator+"Program Files"+File.separator+"nodejs"+File.separator+"node.exe";
    private static final String APPIUMJS= "C:"+File.separator+"Users"+File.separator+"arvin"+File.separator+"AppData"+
    										File.separator+"Roaming"+File.separator+"npm"+File.separator+"node_modules"+
    										File.separator+"appium"+File.separator+"build"+File.separator+"lib"+File.separator+"main.js";
	
    
    public static String getDriverexecutable() {
		return DRIVEREXECUTABLE;
	}



	public static String getAppiumjs() {
		return APPIUMJS;
	}


	private static final String EXTENTREPORTFOLDERPATH=System.getProperty("user.dir")+File.separator+"extent-test-output"+File.separator;
	private static String EXTENTREPORTFILEPATH="";
	private static String LOGFILEPATH="";
	private static final String LOGFOLDERPATH=System.getProperty("user.dir")+File.separator+"Logs"+File.separator;
	

	public static String getVideos() {
		return VIDEOS;
	}



	public static String getScreenshots() {
		return SCREENSHOTS;
	}

	public static String getStatictext() {
		return STATICTEXT;
	}


	private static final int EXPLICITWAIT=20;


	public static String getTestdata() {
		return TESTDATA;
	}

	public static int getExplicitwait() {
		return EXPLICITWAIT;
	}

	public static String getApppath() {

		return APPPATH;
	}

	public static String getConfigfilepath() {
		return CONFIGFILEPATH;
	}

	public static String getNEWFILEPATH() 
	{       if(EXTENTREPORTFILEPATH.isEmpty())

	{
		EXTENTREPORTFILEPATH=createFilepath();
	}

	return EXTENTREPORTFILEPATH;
	}


	public static String createFilepath() {
		if(PropertyUtils.getProperty(Config.OVERRIDEREPORTS).equalsIgnoreCase("yes"))
		{
			return EXTENTREPORTFOLDERPATH+"index.html";
		}
		else
		{
			return   EXTENTREPORTFOLDERPATH+MediaLocationUtils.timeStamp+File.separator+"report.html";
		}

	}
	
	
	
	public static String getNEWLogFILEPATH(String dname, String ud) 
	{          
		
		if(!createLogsFilePath(dname,ud).exists())

	{
	        createLogsFilePath(dname,ud).mkdirs();
	}
		return LOGFILEPATH;
		
	}

	
	
	
	
	public static File createLogsFilePath(String dname, String ud)
	{
		    LOGFILEPATH=LOGFOLDERPATH+ud+"_"+dname;
		
			return new File(LOGFILEPATH);
		
		
	/*	String strFile = "logs" + File.separator + ud + "_" +dname ;
		
		File logFile = new File(strFile);
		if (!logFile.exists()) {
			logFile.mkdirs();*/
		}
	}


