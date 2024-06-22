package com.saucelabs.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import static com.saucelabs.constants.FrameworkConstants.*;
import com.saucelabs.enums.Config;

public class PropertyUtils {
	
	private PropertyUtils() {} 
	
	private static Properties prop= new Properties();
	private static final Map<String,String> MYMAP= new HashMap<>();
	
	
	  static {
	
	        try{
	            
	        	FileInputStream file= new FileInputStream(getConfigfilepath());
	            prop.load(file);
               
                for(Map.Entry<Object,Object> myentry:prop.entrySet())
                {
                	MYMAP.put(String.valueOf(myentry.getKey()),String.valueOf(myentry.getValue()).trim());
                }
             
	        }
             catch(IOException e){
            	 
            	 System.exit(0);
	          }
	        
		    }
		
	public static String getProperty(Config config)
	{
		if(Objects.isNull(config) || Objects.isNull(MYMAP.get(config.name().toLowerCase())))
		{
			throw new RuntimeException("Key not found");
		}
		
		return MYMAP.get(config.name().toLowerCase());
		
	}


}
	


