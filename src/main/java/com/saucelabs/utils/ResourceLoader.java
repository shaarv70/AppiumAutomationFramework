package com.saucelabs.utils;

import java.io.File;
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



	public static String getResourceFilePath(String relativePath) {

	
		File file = new File(ResourceLoader.class.getClassLoader().getResource(relativePath).getFile());
		if (file.exists()) {
			return file.getAbsolutePath();
		} else {
			System.out.println("File not found at path: " + relativePath);
			return null;
		}
	}



}
