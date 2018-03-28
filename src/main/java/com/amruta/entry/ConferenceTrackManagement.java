package com.amruta.entry;
import java.util.List;

import com.amruta.bean.Conference;
import com.amruta.bean.Talk;
import com.amruta.exception.ConferenceTrackManagementException;
import com.amruta.io.InputReader;
import com.amruta.io.OutputWriter;
import com.amruta.logger.ConferenceLogger;
import com.amruta.service.ConferenceService;
import com.amruta.util.TalkUtil;
/**
 * @author amruta
 *
 */
public class ConferenceTrackManagement {
	
	private static ConferenceService conferenceService = new ConferenceService();
	
	public static void main( String[] args )
    {
		try {
			// Read Input from file
			List<String> inputStrTalks = InputReader.getInput();
			
			// Create a conference
			List<Talk> inputTalks = TalkUtil.getTalkList(inputStrTalks);
			
			Conference conference = conferenceService.createConference(inputTalks);
			
			// Write the output to a file
			OutputWriter.writeOutput(conference);
			
			ConferenceLogger.info("Conference Created");
		} catch (ConferenceTrackManagementException e) {
			OutputWriter.writeError(e);
		}
		
    }
}
