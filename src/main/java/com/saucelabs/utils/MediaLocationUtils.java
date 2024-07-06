package com.saucelabs.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.testng.ITestResult;

public class MediaLocationUtils {
	
	protected static ThreadLocal<Map<String,String>> params= new ThreadLocal<Map<String,String>>();
	private static Map<String,String> getCONFIGMAP() {
	return params.get();
	}

    private static void setCONFIGMAP(Map<String,String> cONFIGMAP) {
    	params.set(cONFIGMAP);
	}
	
	
	
	public static void directoryUtils(ITestResult result,String directory,String file,String type) throws IOException
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd_hh.mm.ss").format(new Date());
		Map<String,String> params= new HashMap<>();
		params=result.getTestContext().getCurrentXmlTest().getAllParameters();
		setCONFIGMAP(params);
		String path= directory+File.separator+getCONFIGMAP().get("platformName")+"_"+
				getCONFIGMAP().get("deviceName")+File.separator+result.getTestClass().getRealClass().getSimpleName()+File.separator+timeStamp;

		File dir= new File(path);
		synchronized (dir) {
			if(!dir.exists())
			{
				dir.mkdirs();
			}
		}
		

		try {
			FileOutputStream stream= new FileOutputStream(dir+File.separator+result.getName()+type);
			stream.write(Base64.decodeBase64(file));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

}}
