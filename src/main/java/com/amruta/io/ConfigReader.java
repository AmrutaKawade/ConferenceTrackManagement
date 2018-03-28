package com.amruta.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.amruta.logger.ConferenceLogger;

public class ConfigReader {
	
	/**
	 * @param prop
	 * Loads Session Configuration from Property file
	 */
	public static void loadSessionConfig(Properties prop, String fileName){
		
		FileInputStream file = null;
		try {
			file = new FileInputStream(fileName);
			prop.load(file);		
		} catch (IOException ex) {
			ConferenceLogger.error("Error occured while reading session config file: "+ fileName + " " + ex.getMessage());
		} finally {
			try {
				file.close();
			} catch (IOException e) {
				ConferenceLogger.error("Error occured while closing the file: " + fileName + " " + e.getMessage());
			}
		}
	}
}
