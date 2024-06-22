package com.saucelabs.utils;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saucelabs.constants.FrameworkConstants;



public class JsonUtils {
	
	private JsonUtils() {}
	
	private static  Map<String,Map<String,String>>CONFIGMAP;
	static {

		try {
			
			CONFIGMAP= new ObjectMapper().readValue(new File(FrameworkConstants.getTestdata()),new TypeReference<Map<String,Map<String,String>>>(){});
			}
			
		//	property.entrySet().forEach(entry ->CONFIGMAP.put(String.valueOf(entry.getKey()),String.valueOf(entry.getValue())));JAVA -8
		
		catch (IOException e) {

			e.printStackTrace();
		    System.exit(0);
		}


	}

	
	
	public static String get(String key,String key1) 
	{
		if(Objects.isNull(key))
		{
			throw new RuntimeException("key not found");
		}
	
	
		return CONFIGMAP.get(key).get(key1);
	}
	
	
	 
	  
}
	

