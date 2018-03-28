package com.amruta.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.TreeMap;

import com.amruta.constant.Constants;
import com.amruta.io.ConfigReader;

public class SessionUtil {
	
	private static Properties prop = new Properties();
	
	static {
		ConfigReader.loadSessionConfig(prop, Constants.SESSION_CONFIG_FILE);
	}
	
	/**
	 * @return
	 */
	public static Map<String, String[]> getValidSessions() {
		Map<String, String[]> sessionMap = new TreeMap<>();
		for(Entry<Object, Object> sessionConf : prop.entrySet()){
			String sessionType = (String) sessionConf.getKey();
			String[] timings = ((String) sessionConf.getValue()).split(Constants.START_END_SEPERATOR);
			sessionMap.put(sessionType, timings);
		}
		return sessionMap;
	}
}
