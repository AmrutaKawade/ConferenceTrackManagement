package com.amruta.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConferenceLogger {
	
	private static final Logger LOGGER = LogManager.getRootLogger();
	
	public static void error(String info){
		LOGGER.error(info);
	}
	
	public static void debug(String info){
		LOGGER.debug(info);
	}
	
	public static void info(String info){
		LOGGER.info(info);
	}
}
