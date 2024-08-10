package com.saucelabs.constants;

import java.io.File;

import com.saucelabs.enums.Config;
import com.saucelabs.utils.MediaLocationUtils;
import com.saucelabs.utils.PropertyUtils;

public final class FrameworkConstants {


	private FrameworkConstants() {}


 //   private static final String RESOURCES=System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources";	
	private static final String CONFIGFILEPATH="config"+File.separator+"config.properties";
	private static final String TESTDATA="testData"+File.separator+"loginUsers.json";
	private static final String STATICTEXT="staticData"+File.separator+"strings.xml";
	private static final String SCREENSHOTS="Screenshots";
	private static final String VIDEOS= "Videos";
	private static final String EXTENTREPORTFOLDERPATH="extent-test-output"+File.separator;
	private static String EXTENTREPORTFILEPATH="";
	private static String LOGFILEPATH="";
	private static final String LOGFOLDERPATH="Logs"+File.separator;
	private static final String REMOTEDRIVEREXECUTABLE="/usr/bin/node";
	private static final String LOCALDRIVEREXECUTABLE="C:"+File.separator+"Program Files"+File.separator+"nodejs"+File.separator+"node.exe";
	private static final String REMOTEAPPIUMJS="/usr/local/lib/node_modules/appium/build/lib/main.js";
    private static final String LOCALAPPIUMJS= "C:"+File.separator+"Users"+File.separator+"arvin"+File.separator+"AppData"+
    										File.separator+"Roaming"+File.separator+"npm"+File.separator+"node_modules"+
    										File.separator+"appium"+File.separator+"build"+File.separator+"lib"+File.separator+"main.js";
    
    
    public static String getRemotedriverexecutable() {
		return REMOTEDRIVEREXECUTABLE;
	}
    
    public static String getRemoteappiumjs() {
		return REMOTEAPPIUMJS;
	}
   

    
    public static String getDriverexecutable() {
		return LOCALDRIVEREXECUTABLE;
	}



	public static String getAppiumjs() {
		return LOCALAPPIUMJS;
	}


	
	

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
			return EXTENTREPORTFOLDERPATH+"report.html";
		}
		else
		{
			return   EXTENTREPORTFOLDERPATH+MediaLocationUtils.TIMESTAMP+File.separator+"report.html";
		}

	}
	
	
	
	public static String getNewLogFilePath(String dname) 
	{          
		
		if(!createLogsFilePath(dname).exists())

	{
	        createLogsFilePath(dname).mkdirs();
	}
		return LOGFILEPATH;
		
	}

	
	
	
	
	public static File createLogsFilePath(String dname)
	{
		    LOGFILEPATH=LOGFOLDERPATH+System.getProperty("udid")+"_"+dname;
		
			return new File(LOGFILEPATH);
		
		
	/*	String strFile = "logs" + File.separator + ud + "_" +dname ;
		
		File logFile = new File(strFile);
		if (!logFile.exists()) {
			logFile.mkdirs();*/
		}
	}


