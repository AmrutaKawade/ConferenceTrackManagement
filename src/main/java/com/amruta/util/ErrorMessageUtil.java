/**
 * 
 */
package com.amruta.util;

import java.util.Properties;

import com.amruta.constant.Constants;
import com.amruta.io.ConfigReader;

/**
 * @author amruta
 *
 */
public class ErrorMessageUtil {

	private static Properties prop = new Properties();
	static {
		ConfigReader.loadSessionConfig(prop, Constants.ERROR_MESSAGE_FILE);
	}
	
	public static String getErrorMessage(String errorCode) {
		return prop.getProperty(errorCode);
	}
}
