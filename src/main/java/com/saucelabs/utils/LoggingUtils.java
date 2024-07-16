package com.saucelabs.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingUtils {



public static Logger log()
{


	return LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());

}



}
