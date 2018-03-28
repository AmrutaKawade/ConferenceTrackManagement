package com.amruta.ConferenceTrackManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.amruta.bean.Talk;
import com.amruta.constant.Constants;
import com.amruta.service.ConferenceServiceTest;
import com.amruta.service.ScheduleTalkServiceTest;
import com.amruta.util.DateUtilTest;
import com.amruta.util.TalkUtilTest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class ConferenceTrackManagementTest extends TestCase

{
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public ConferenceTrackManagementTest(String testName) {
		super(testName);
	}

	public ConferenceTrackManagementTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		Class[] testClasses = { ConferenceServiceTest.class, DateUtilTest.class, TalkUtilTest.class,
				ScheduleTalkServiceTest.class};
		return new TestSuite(testClasses);
	}

	protected List<Talk> getTalkList(Map<Integer, Integer> lenCountMap) {
		List<Talk> talkList = new ArrayList<>();
		Integer count = 1;
		for (Map.Entry<Integer, Integer> entry : lenCountMap.entrySet()) {
			Integer length = entry.getKey();
			Integer talkCount = entry.getValue();
			while (talkCount > 0) {
				talkList.add(getTalk("Talk - " + count, length));
				talkCount--;
			}
		}
		return talkList;
	}

	protected List<String> getTalkInputStrList(Map<Integer, Integer> lenCountMap) {
		List<String> inputStrList = new ArrayList<>();
		Integer count = 1;
		for (Map.Entry<Integer, Integer> entry : lenCountMap.entrySet()) {
			Integer length = entry.getKey();
			Integer talkCount = entry.getValue();
			while (talkCount > 0) {
				inputStrList.add("Talk - " + count + Constants.TITLE_LEN_SEPARATOR + length + "min");
				talkCount--;
			}
		}
		return inputStrList;
	}

	protected Talk getTalk(String title, Integer length) {
		return new Talk(title, length);
	}

}
