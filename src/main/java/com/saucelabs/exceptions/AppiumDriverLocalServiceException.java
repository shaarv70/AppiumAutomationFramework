package com.saucelabs.exceptions;

@SuppressWarnings("serial")
public class AppiumDriverLocalServiceException extends FrameworkExceptions{

	public AppiumDriverLocalServiceException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public AppiumDriverLocalServiceException(String message) {
		super(message);
		
	}

}
