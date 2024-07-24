package com.saucelabs.utils;

import static com.saucelabs.constants.FrameworkConstants.getConfigfilepath;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import com.saucelabs.enums.Config;
import com.saucelabs.exceptions.DataLoadingFailedException;
import com.saucelabs.exceptions.KeyNotFoundException;

public final class PropertyUtils {
	
	    private PropertyUtils() {}
	
	    protected static ThreadLocal<Properties> props= new ThreadLocal<>()  ;
	    private static final Map<String,String> CONFIGMAP= new HashMap<>();
	    public static FileInputStream file;
	 
	  
	    
	    public static Properties getProp() {
	        return props.get();
	    }
	 
	    private static void setProp(Properties proper) {
	        props.set(proper);
	    }
	 
	    // Method to initialize properties and map for the current thread
	   public static synchronized void  initializeProperties() {
	        try {
	           
	            Properties prop = new Properties();
	            file = new FileInputStream(getConfigfilepath());
	            prop.load(file);
	            setProp(prop);
	 
	            for (Map.Entry<Object, Object> myentry : getProp().entrySet()) {
	                CONFIGMAP.put(String.valueOf(myentry.getKey()), String.valueOf(myentry.getValue()).trim());
	            }
	           
	        } catch (IOException e) {
	            // Log the exception and throw a runtime exception to handle it properly
	            e.printStackTrace();
	            
	            throw new DataLoadingFailedException("Failed to load properties " + e);
	        }
	           
	        finally {
				try {
					file.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
	    }
	 
	    public static String getProperty(Config config) {
	    	
	    	
	        if (Objects.isNull(config) || Objects.isNull(CONFIGMAP.get(config.name().toLowerCase()))) {
	        	
	            throw new KeyNotFoundException("Property key not found :"+config);
	        }
	        return CONFIGMAP.get(config.name().toLowerCase());
	    }
	}


	


