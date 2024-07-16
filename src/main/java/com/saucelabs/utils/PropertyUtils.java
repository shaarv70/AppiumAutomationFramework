package com.saucelabs.utils;

import static com.saucelabs.constants.FrameworkConstants.getConfigfilepath;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import com.saucelabs.enums.Config;

public class PropertyUtils {
	
	
	
	    protected static ThreadLocal<Properties> props= new ThreadLocal<Properties>()  ;
	    private static final Map<String,String> CONFIGMAP= new HashMap<String, String>();
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
	            throw new RuntimeException("Failed to load properties: " + e.getMessage());
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
	            throw new RuntimeException("Key not found");
	        }
	        return CONFIGMAP.get(config.name().toLowerCase());
	    }
	}


	


