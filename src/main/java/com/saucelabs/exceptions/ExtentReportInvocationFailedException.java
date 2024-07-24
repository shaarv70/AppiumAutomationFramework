package com.saucelabs.exceptions;

@SuppressWarnings("serial")
public class ExtentReportInvocationFailedException extends FrameworkExceptions{

	public ExtentReportInvocationFailedException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ExtentReportInvocationFailedException(String message) {
		super(message);
		
	}

}
