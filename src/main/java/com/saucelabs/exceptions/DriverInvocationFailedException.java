package com.saucelabs.exceptions;

@SuppressWarnings("serial")
public class DriverInvocationFailedException extends FrameworkExceptions {

	
	
	public DriverInvocationFailedException(String message) {
		super(message);
		
	}

	public DriverInvocationFailedException(String message, Throwable cause) {
		super(message, cause);
		
	}
	


}
