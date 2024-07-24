package com.saucelabs.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LoggingUtils {


	private LoggingUtils() {}

	public static Logger log()
	{
		return LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());

	}



}
