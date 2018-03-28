/**
 * 
 */
package com.amruta.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.amruta.ConferenceTrackManagement.ConferenceTrackManagementTest;
import com.amruta.bean.Talk;
import com.amruta.constant.ErrorCode;
import com.amruta.exception.ConferenceTrackManagementException;
import com.amruta.util.TalkUtil;

/**
 * @author amruta
 *
 */
public class TalkUtilTest extends ConferenceTrackManagementTest {

	public void testGetTalkLenList() {
		String failureMessage = "Talk len List is not working properly";
		Map<Integer, Integer> lenCountMap = new HashMap<>();
		lenCountMap.put(60, 5);
		lenCountMap.put(30, 6);
		List<Talk> talkList = getTalkList(lenCountMap);
		List<Integer> lenList = TalkUtil.getTalkLenList(talkList);
		Assert.assertEquals(failureMessage, talkList.size(), lenList.size());
	}
	
	public void testGetTalkLenListEmpty() {
		String failureMessage = "Talk len List is not working properly";
		List<Talk> talkList = new ArrayList<>();
		List<Integer> lenList = TalkUtil.getTalkLenList(talkList);
		Assert.assertEquals(failureMessage, talkList.size(), lenList.size());
	}
	
	public void testGetTalkList() {
		String failureMessage = "Talk List is not working properly";
		Map<Integer, Integer> lenCountMap = new HashMap<>();
		lenCountMap.put(60, 5);
		lenCountMap.put(30, 6);
		List<String> talkStrList = getTalkInputStrList(lenCountMap);
		try {
			List<Talk> talkList = TalkUtil.getTalkList(talkStrList);
			Assert.assertNotNull(failureMessage, talkList);
			Assert.assertEquals(failureMessage, talkStrList.size(), talkList.size());
		} catch (ConferenceTrackManagementException e) {
			Assert.fail(failureMessage);
		}
	}
	
	public void testGetTalkListEmpty() {
		String failureMessage = "Talk List is not working properly";
		List<String> talkStrList = new ArrayList<>();
		try {
			TalkUtil.getTalkList(talkStrList);
			Assert.fail(failureMessage);
		} catch (ConferenceTrackManagementException e) {
			Assert.assertEquals(failureMessage, ErrorCode.CTM_INPUT_EMPTY_ERROR.name(), e.getErrorCode());
		}
	}
}
