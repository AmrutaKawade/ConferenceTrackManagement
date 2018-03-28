/**
 * 
 */
package com.amruta.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.amruta.ConferenceTrackManagement.ConferenceTrackManagementTest;
import com.amruta.bean.Conference;
import com.amruta.bean.Talk;
import com.amruta.constant.ErrorCode;
import com.amruta.exception.ConferenceTrackManagementException;
import com.amruta.service.ConferenceService;



/**
 * @author amruta
 *
 */
public class ConferenceServiceTest extends ConferenceTrackManagementTest {

	public void testCreateConference() {
		String failureMessage = "Error in creating conference";	
		Map<Integer, Integer> lenCountMap = new HashMap<>();
		lenCountMap.put(60, 14);
		List<Talk> inputTalks = getTalkList(lenCountMap);
		ConferenceService conferenceService = new ConferenceService();
		try {
			Conference conference = conferenceService.createConference(inputTalks);
			Assert.assertNotNull(failureMessage, conference);
		} catch (ConferenceTrackManagementException e) {
			Assert.fail(failureMessage + e.getErrorMessage());
		}
	}
	
	public void testCreateNullConference() {
		String failureMessage = "Error expected for null input";	
		ConferenceService conferenceService = new ConferenceService();
		try {
			Conference conference = conferenceService.createConference(null);
			Assert.fail(failureMessage);
			Assert.assertNotNull(failureMessage, conference);
		} catch (ConferenceTrackManagementException e) {
			Assert.assertNotNull(failureMessage, e);
			Assert.assertEquals(failureMessage,  ErrorCode.CTM_INPUT_EMPTY_ERROR.name(), e.getErrorCode());
		}
	}
	
	public void testCreateEmptyConference() {
		String failureMessage = "Error expected for Empty input";	
		ConferenceService conferenceService = new ConferenceService();
		List<Talk> inputTalks = new ArrayList<>();
		try {
			Conference conference = conferenceService.createConference(inputTalks);
			Assert.fail(failureMessage);
			Assert.assertNotNull(failureMessage, conference);
		} catch (ConferenceTrackManagementException e) {
			Assert.assertNotNull(failureMessage, e);
			Assert.assertEquals(failureMessage,  ErrorCode.CTM_INPUT_EMPTY_ERROR.name(), e.getErrorCode());
		}
	}

}
