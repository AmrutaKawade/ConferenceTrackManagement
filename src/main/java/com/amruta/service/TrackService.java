/**
 * 
 */
package com.amruta.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.amruta.bean.Session;
import com.amruta.bean.Talk;
import com.amruta.bean.Track;
import com.amruta.constant.Constants;
import com.amruta.constant.ErrorCode;
import com.amruta.exception.ConferenceTrackManagementException;
import com.amruta.logger.ConferenceLogger;
import com.amruta.util.DateUtil;
import com.amruta.util.ErrorMessageUtil;
import com.amruta.util.SessionUtil;

/**
 * @author amruta
 *
 */
public class TrackService {
	
	private SessionService sessionService = new SessionService();
	
	/**
	 * Create Tracks In Conference;
	 * @param talkDetails
	 * @return
	 * @throws ConferenceTrackManagementException
	 */
	public Track createTrack(List<Talk> talkDetails) throws ConferenceTrackManagementException {
		Track track = new Track();
		List<Session> sessionList = getSessionList(talkDetails);
		track.setSessionList(sessionList);
		ConferenceLogger.info("Track Created");
		return track;
	}

	/**
	 * Create Session List for each Track
	 * @param talkDetails
	 * @return
	 * @throws ConferenceTrackManagementException
	 */
	private List<Session> getSessionList(List<Talk> talkDetails) throws ConferenceTrackManagementException {
		List<Session> sessionList = new ArrayList<>();
		Map<String, String[]> sessionProps = SessionUtil.getValidSessions();
		String sessionEndTime = null;
		for (Map.Entry<String, String[]> entry : sessionProps.entrySet()) {
			Session session;
			String sessionType = entry.getKey();
			String startTime = getStartTime(sessionEndTime,entry.getValue()[0]);
			String endTime = entry.getValue()[1];
			if (!talkDetails.isEmpty() && (sessionType.contains(Constants.MORNING) || sessionType.contains(Constants.AFTERNOON))) {
				session = sessionService.createSession(talkDetails, sessionType, startTime, endTime);
				sessionEndTime = session.getEndTime();
				sessionList.add(session);
			} else if (talkDetails.isEmpty() && (sessionType.contains(Constants.MORNING) || sessionType.contains(Constants.AFTERNOON))){
				ConferenceLogger.error("Input Talk Details list is empty. Method: TrackService.getSessionList");
				break;
			} else {
				session = new Session(startTime, endTime, sessionType);
				Talk lunch = new Talk(sessionType.substring(2, sessionType.length()), 0);
				lunch.setStartTime(startTime);
				lunch.setEndTime(endTime);
				List<Talk> miscellaneousTalks = new ArrayList<>();
				miscellaneousTalks.add(lunch);
				session.setTalks(miscellaneousTalks);
				sessionList.add(session);
			}
			ConferenceLogger.info("Session: " + sessionType + " created");
		}
		Collections.sort(sessionList);
		return sessionList;
	}

	/**
	 * @param sessionEndTime
	 * @param flexibleTime
	 * @return
	 * @throws ConferenceTrackManagementException
	 */
	private String getStartTime(String sessionEndTime, String flexibleTime) throws ConferenceTrackManagementException {
		if(flexibleTime.contains(Constants.FLEX_TIME_SEPERATOR)){
			String[] startTimes = flexibleTime.split(Constants.FLEX_TIME_SEPERATOR);
			if(null != sessionEndTime){
				if(DateUtil.compareTime(sessionEndTime, startTimes[0]) <= 0){
					return startTimes[0];
				}else{
					return startTimes[1];
				}
			}else{
				throw new ConferenceTrackManagementException(ErrorCode.CTM_INVALID_START_TIME_FOR_SESSION_ERROR.name(),
						ErrorMessageUtil.getErrorMessage(ErrorCode.CTM_INVALID_START_TIME_FOR_SESSION_ERROR.name()));
			}
		}else{
			return flexibleTime;
		}
	}
}
