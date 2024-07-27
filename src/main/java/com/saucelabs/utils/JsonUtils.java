package com.saucelabs.utils;

import static com.saucelabs.constants.FrameworkConstants.getConfigfilepath;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saucelabs.constants.FrameworkConstants;
import com.saucelabs.exceptions.DataLoadingFailedException;
import com.saucelabs.exceptions.KeyNotFoundException;



public final class JsonUtils {

	private JsonUtils() {}

	private  static Map<String,Map<String,String>> CONFIGMAP= new HashMap<>();



	public static synchronized void  initializeTestData() {

		try{
			InputStream stream=ResourceLoader.getResource(FrameworkConstants.getTestdata());

			CONFIGMAP= new ObjectMapper().readValue(stream,new TypeReference<Map<String,Map<String,String>>>(){});

		}
		//	property.entrySet().forEach(entry ->CONFIGMAP.put(String.valueOf(entry.getKey()),String.valueOf(entry.getValue())));JAVA -8

		catch (Exception e) {

			throw new DataLoadingFailedException("Failed to load JSON data",e);


		} 

	}
	public static synchronized String get(String key,String childKey) 
	{
		if(Objects.isNull(key))
		{
			throw new KeyNotFoundException("JSON parent key not found : "+key);
		}


		return CONFIGMAP.get(key).get(childKey);
	}




}


