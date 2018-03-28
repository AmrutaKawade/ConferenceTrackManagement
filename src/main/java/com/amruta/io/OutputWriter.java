package com.amruta.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import com.amruta.bean.Conference;
import com.amruta.bean.Session;
import com.amruta.bean.Talk;
import com.amruta.bean.Track;
import com.amruta.constant.Constants;
import com.amruta.exception.ConferenceTrackManagementException;
import com.amruta.logger.ConferenceLogger;

public class OutputWriter {
	
	/**
	 * Write Output to the file
	 * @param conference
	 */
	public static void writeOutput(Conference conference) {
		List<Track> tracks = conference.getTracks();
		Path output = Paths.get(Constants.OUTPUT_FILE);
		int count = 1;
		try {
			for(Track track:tracks){
				String trackStr = "Track"+count+"\n";
				if(count > 1){
					Files.write(output, trackStr.getBytes(), StandardOpenOption.APPEND);
				}else{
					Files.write(output, trackStr.getBytes());
				}
				for(Session session:track.getSessionList()){
					for(Talk talk: session.getTalks()){
						String content = talk.getStartTime()+" " +talk.getTitle()+"\n";					
						Files.write(output, content.getBytes(), StandardOpenOption.APPEND);
					}
				}
				Files.write(output, "\n".getBytes(), StandardOpenOption.APPEND);
				count++;
			}
		} catch (IOException e) {			
			ConferenceLogger.error("Error occured while writing to the file: " + Constants.OUTPUT_FILE + e.getMessage());
		}
	}
	
	/**
	 * @param ctmException
	 */
	public static void writeError(ConferenceTrackManagementException ctmException) {
		Path output = Paths.get(Constants.OUTPUT_FILE);
		String errorMessage = ctmException.getErrorMessage();
		try {
			Files.write(output, errorMessage.getBytes());
		} catch (IOException e) {
			ConferenceLogger.error("Error occured while writing to the file: " + Constants.OUTPUT_FILE + e.getMessage());
		}
	}

}
