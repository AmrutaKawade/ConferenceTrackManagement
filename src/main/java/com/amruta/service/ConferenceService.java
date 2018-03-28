/**
 * 
 */
package com.amruta.service;

import java.util.ArrayList;
import java.util.List;

import com.amruta.bean.Conference;
import com.amruta.bean.Talk;
import com.amruta.bean.Track;
import com.amruta.constant.ErrorCode;
import com.amruta.exception.ConferenceTrackManagementException;
import com.amruta.util.ErrorMessageUtil;

/**
 * @author amruta
 *
 */
public class ConferenceService {
	
	private TrackService trackService = new TrackService();
	/**
	 * Create Conference from Input Talk Details List
	 * @param talkDetails
	 * @return
	 * @throws ConferenceTrackManagementException
	 */
	public Conference createConference(List<Talk> talkDetails) throws ConferenceTrackManagementException {

		Conference conference = new Conference();
		List<Track> tracks = new ArrayList<>();
		if (null == talkDetails || talkDetails.isEmpty()) {
			throw new ConferenceTrackManagementException(ErrorCode.CTM_INPUT_EMPTY_ERROR.name(),
					ErrorMessageUtil.getErrorMessage(ErrorCode.CTM_INPUT_EMPTY_ERROR.name()));
		}
		while (!talkDetails.isEmpty()) {
			Track track = trackService.createTrack(talkDetails);
			if (!track.getSessionList().isEmpty())
				tracks.add(track);
		}
		conference.setTracks(tracks);
		return conference;
	}
}
