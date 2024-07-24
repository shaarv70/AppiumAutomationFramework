package com.saucelabs.exceptions;

@SuppressWarnings("serial")
public class KeyNotFoundException extends FrameworkExceptions {

	public KeyNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public KeyNotFoundException(String message) {
		super(message);
		
	}
	
	

}
