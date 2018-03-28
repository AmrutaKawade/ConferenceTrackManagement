/**
 * 
 */
package com.amruta.service;

import java.util.List;

import com.amruta.bean.Session;
import com.amruta.bean.Talk;
import com.amruta.exception.ConferenceTrackManagementException;
import com.amruta.logger.ConferenceLogger;
import com.amruta.util.DateUtil;

/**
 * @author amruta
 *
 */
public class SessionService {
	
	private ScheduleTalkService scheduledTalkService = new ScheduleTalkService();
	
	/**
	 * Create Schedulable Session Object
	 * @param talkDetails
	 * @param sessionType
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ConferenceTrackManagementException
	 */
	public Session createSession(List<Talk> talkDetails, String sessionType, String startTime, String endTime) throws ConferenceTrackManagementException {
		Session session = new Session(startTime, endTime, sessionType);
		
		try {
			List<Talk> scheduledTalks = scheduledTalkService.getScheduledTalks(session, talkDetails);
			populateTimeDetails(scheduledTalks, session);
			session.setTalks(scheduledTalks);
			removeScheduledFromOriginal(talkDetails, scheduledTalks);
		} catch (ConferenceTrackManagementException e) {
			ConferenceLogger.error("Following talks cannot fit in session - ");
			for(Talk talk : talkDetails)
		           ConferenceLogger.error(talk.getTitle() + " ");
		}
		return session;
	}


	/**
	 * @param scheduledTalks
	 * @param session
	 * @throws ConferenceTrackManagementException
	 */
	private void populateTimeDetails(List<Talk> scheduledTalks, Session session) throws ConferenceTrackManagementException {
		String startTime = session.getStartTime();
		String endTime = "";
		for (Talk talk : scheduledTalks) {
        	startTime = "".equals(endTime) ? startTime : endTime;
			talk.setStartTime(startTime);
			endTime = DateUtil.updateTime(startTime,talk.getLength());
			talk.setEndTime(endTime);
        }
		session.setEndTime(endTime);
	}

	/**
	 * @param originalTalkList
	 * @param scheduledTalks
	 */
	private void removeScheduledFromOriginal(List<Talk> originalTalkList, List<Talk> scheduledTalks) {
		for (Talk talk : scheduledTalks) {
			originalTalkList.remove(talk);
		}
	}
}
