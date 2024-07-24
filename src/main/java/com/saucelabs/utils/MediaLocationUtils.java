package com.saucelabs.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.testng.ITestResult;

import com.saucelabs.exceptions.FileNotPresentException;

public final class MediaLocationUtils {
	
	   
	private MediaLocationUtils() {}
	
	
	
	static FileOutputStream stream=null;
	

	public static final String TIMESTAMP = new SimpleDateFormat("yyyy.MM.dd_hh.mm.ss").format(new Date());
	
	
	public static void directoryUtils(ITestResult result,String directory,String file,String type) 
	{
		
		
		String path= directory+File.separator+TestPropertiesUtils.getCONFIGMAP().get("platformName")+"_"+
				TestPropertiesUtils.getCONFIGMAP().get("deviceName")+File.separator+result.getTestClass().getRealClass().getSimpleName()+File.separator+TIMESTAMP;

		File dir= new File(path);
		synchronized (dir) {
			if(!dir.exists())
			{
				dir.mkdirs();
			}
		}
		
		String location=dir+File.separator+result.getName()+type;

		
			try {
				stream= new FileOutputStream(location);
				stream.write(Base64.decodeBase64(file));
			} catch (FileNotFoundException e) {
				
				 throw new FileNotPresentException("File not found at the present location : "+location, e);  
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		  finally {
			try {
				stream.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}

}}
