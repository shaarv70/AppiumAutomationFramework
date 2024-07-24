package com.saucelabs.exceptions;

@SuppressWarnings("serial")
public class DataLoadingFailedException extends FrameworkExceptions {

	public DataLoadingFailedException(String message, Throwable cause) {
		super(message, cause);
	
	}

	public DataLoadingFailedException(String message) {
		super(message);
		
	}

}
