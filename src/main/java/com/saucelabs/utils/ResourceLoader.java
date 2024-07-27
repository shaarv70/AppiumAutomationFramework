package com.saucelabs.utils;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;



public final class ResourceLoader {
	
	
	private ResourceLoader() {}
	
	  public static InputStream getResource(String path) throws Exception {
	        InputStream stream = ResourceLoader.class.getClassLoader().getResourceAsStream(path);//here we ae reading the
	        //file from classpath
	        if(Objects.nonNull(stream)) //checking if it is present in classpath or not
	        {
	            return stream;
	        }
	        return Files.newInputStream(Path.of(path));//else return it from
	    }

	


}
