/**
 * 
 */
package com.amruta.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.amruta.ConferenceTrackManagement.ConferenceTrackManagementTest;
import com.amruta.bean.Session;
import com.amruta.bean.Talk;
import com.amruta.constant.Constants;
import com.amruta.constant.ErrorCode;
import com.amruta.exception.ConferenceTrackManagementException;
import com.amruta.service.ScheduleTalkService;

/**
 * @author amruta
 *
 */
public class ScheduleTalkServiceTest extends ConferenceTrackManagementTest {

	public void testGetScheduledTalks() {
		String failureMessage = "Scheduling not working properly";
		try {
			ScheduleTalkService scheduleTalkService = new ScheduleTalkService();
			Session session = new Session("9:00 AM", "11:00 AM", Constants.MORNING);
			Map<Integer, Integer> lenCountMap = new HashMap<>();
			lenCountMap.put(30, 5);
			List<Talk> talkList = getTalkList(lenCountMap);
			List<Talk> scheduledtalkList = scheduleTalkService.getScheduledTalks(session, talkList);
			Assert.assertNotNull(failureMessage, scheduledtalkList);
			Assert.assertEquals(failureMessage, new Long(4), new Long(scheduledtalkList.size()));
		} catch (ConferenceTrackManagementException e) {
			Assert.fail(failureMessage);
		}
	}
	
	public void testGetScheduledTalksNoFit() {
		String failureMessage = "Scheduling not working properly";
		try {
			ScheduleTalkService scheduleTalkService = new ScheduleTalkService();
			Session session = new Session("9:00 AM", "11:00 AM", Constants.MORNING);
			Map<Integer, Integer> lenCountMap = new HashMap<>();
			lenCountMap.put(150, 5);
			List<Talk> talkList = getTalkList(lenCountMap);
			scheduleTalkService.getScheduledTalks(session, talkList);
			Assert.fail(failureMessage);
		} catch (ConferenceTrackManagementException e) {
			Assert.assertNotNull(failureMessage, e);
			Assert.assertEquals(failureMessage, ErrorCode.CTM_TALK_CANNOT_FIT_IN_SESSION_ERROR.name(), e.getErrorCode());
		}
	}
}
