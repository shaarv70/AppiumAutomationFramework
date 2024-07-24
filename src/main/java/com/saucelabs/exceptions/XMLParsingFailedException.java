package com.saucelabs.exceptions;

@SuppressWarnings("serial")
public class XMLParsingFailedException extends FrameworkExceptions {

	public XMLParsingFailedException(String message, Throwable cause) {
		super(message, cause);
		
	}
	public XMLParsingFailedException(String message) {
		super(message);
		
	}

}
