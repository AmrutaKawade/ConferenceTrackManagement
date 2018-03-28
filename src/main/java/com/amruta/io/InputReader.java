/**
 * 
 */
package com.amruta.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import com.amruta.constant.Constants;
import com.amruta.constant.ErrorCode;
import com.amruta.exception.ConferenceTrackManagementException;
import com.amruta.logger.ConferenceLogger;
import com.amruta.util.ErrorMessageUtil;

/**
 * @author amruta
 *
 */
public class InputReader {

	/**
	 * Reads input from file
	 * 
	 * @return List<String> lines
	 * @throws ConferenceTrackManagementException 
	 */
	public static List<String> getInput() throws ConferenceTrackManagementException {
		ConferenceLogger.info("Trying to read input from File - " + Constants.INPUT_FILE);
		List<String> lines = null;
		try {
			lines = Files.lines(Paths.get(Constants.INPUT_FILE)).collect(Collectors.toList());
			ConferenceLogger.info("Input read successfully from file");
		} catch (IOException e) {
			ConferenceLogger.error("Error occured while reading input file : " + e.getMessage());
			throw new ConferenceTrackManagementException(ErrorCode.CTM_INPUT_READING_ERROR.name(),
					ErrorMessageUtil.getErrorMessage(ErrorCode.CTM_INPUT_READING_ERROR.name()));
		}
		return lines;
	}
}
