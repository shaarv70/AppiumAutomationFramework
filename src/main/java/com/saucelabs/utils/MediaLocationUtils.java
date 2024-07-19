package com.saucelabs.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.testng.ITestResult;

import com.saucelabs.listeners.Listeners;

public class MediaLocationUtils {
	
	   
	private MediaLocationUtils() {}
	
	
	
	static FileOutputStream stream=null;
	

	public static String timeStamp = new SimpleDateFormat("yyyy.MM.dd_hh.mm.ss").format(new Date());
	
	
	public static void directoryUtils(ITestResult result,String directory,String file,String type) throws IOException
	{
		
		
		String path= directory+File.separator+TestPropertiesUtils.getCONFIGMAP().get("platformName")+"_"+
				TestPropertiesUtils.getCONFIGMAP().get("deviceName")+File.separator+result.getTestClass().getRealClass().getSimpleName()+File.separator+timeStamp;

		File dir= new File(path);
		synchronized (dir) {
			if(!dir.exists())
			{
				dir.mkdirs();
			}
		}
		

		try{
			stream= new FileOutputStream(dir+File.separator+result.getName()+type);
			stream.write(Base64.decodeBase64(file));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		finally {
			stream.close();
		}

}}
