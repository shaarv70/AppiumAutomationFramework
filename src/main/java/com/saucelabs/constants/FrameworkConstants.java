package com.saucelabs.constants;

public final class FrameworkConstants {
	
	
	   private FrameworkConstants() {}
	   
	
	 private static final String RESOURCES=System.getProperty("user.dir")+"/src/test/resources";	
	 private static final String APPPATH= RESOURCES+"/app/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk";
	 private static final String CONFIGFILEPATH=RESOURCES+"/config.properties";
	 private static final String TESTDATA=RESOURCES+"/testData/loginUsers.json";
	 private static final String STATICTEXT=RESOURCES+"/staticData/strings.xml";
	 public static String getStatictext() {
		return STATICTEXT;
	}


	private static final int EXPLICITWAIT=10;
	 
	 
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
	 

	
	 
	 
	
	
	
	 
	
	

}
