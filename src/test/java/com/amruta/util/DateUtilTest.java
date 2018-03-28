/**
 * 
 */
package com.amruta.util;

import org.junit.Assert;

import com.amruta.ConferenceTrackManagement.ConferenceTrackManagementTest;
import com.amruta.constant.ErrorCode;
import com.amruta.exception.ConferenceTrackManagementException;
import com.amruta.util.DateUtil;

/**
 * @author amruta
 *
 */
public class DateUtilTest extends ConferenceTrackManagementTest {

	public void testCalculateDuration60MinAM() {
		String failureMessage = "Duration Calculation is not working";
		String startTime = "1:00 AM";
		String endTime = "2:00 AM";
		Integer timeDuration;
		try {
			timeDuration = DateUtil.calculateDuration(startTime, endTime);
			Assert.assertNotNull(failureMessage, timeDuration);
			Assert.assertEquals(failureMessage, new Long(60), new Long(timeDuration));
		} catch (ConferenceTrackManagementException e) {
			Assert.fail(failureMessage);
		}

	}

	public void testCalculateDuration90MinAM() {
		String failureMessage = "Duration Calculation is not working";
		String startTime = "1:00 AM";
		String endTime = "2:30 AM";
		Integer timeDuration;
		try {
			timeDuration = DateUtil.calculateDuration(startTime, endTime);
			Assert.assertNotNull(failureMessage, timeDuration);
			Assert.assertEquals(failureMessage, new Long(90), new Long(timeDuration));
		} catch (ConferenceTrackManagementException e) {
			Assert.fail(failureMessage);
		}
	}

	public void testCalculateDuration60MinPM() {
		String failureMessage = "Duration Calculation is not working";
		String startTime = "1:00 PM";
		String endTime = "2:00 PM";
		Integer timeDuration;
		try {
			timeDuration = DateUtil.calculateDuration(startTime, endTime);
			Assert.assertNotNull(failureMessage, timeDuration);
			Assert.assertEquals(failureMessage, new Long(60), new Long(timeDuration));
		} catch (ConferenceTrackManagementException e) {
			Assert.fail(failureMessage);
		}
	}

	public void testCalculateDuration90MinPM() {
		String failureMessage = "Duration Calculation is not working";
		String startTime = "1:00 PM";
		String endTime = "2:30 PM";
		Integer timeDuration;
		try {
			timeDuration = DateUtil.calculateDuration(startTime, endTime);
			Assert.assertNotNull(failureMessage, timeDuration);
			Assert.assertEquals(failureMessage, new Long(90), new Long(timeDuration));
		} catch (ConferenceTrackManagementException e) {
			Assert.fail(failureMessage);
		}
	}
     
	/*public void testCalculateDuration90MinCrossPMAM() {
		String failureMessage = "Duration Calculation is not working";
		String startTime = "11:00 PM";
		String endTime = "01:30 AM";
		Integer timeDuration;
		try {
			timeDuration = DateUtil.calculateDuration(startTime, endTime);
			Assert.assertNotNull(failureMessage, timeDuration);
			Assert.assertEquals(failureMessage, new Long(150), new Long(timeDuration));
		} catch (CTMException e) {
			Assert.fail(failureMessage);
		}
	}*/
	
	public void testCalculateDuration90MinCrossAMPM() {
		String failureMessage = "Duration Calculation is not working";
		String startTime = "11:00 AM";
		String endTime = "01:30 PM";
		Integer timeDuration;
		try {
			timeDuration = DateUtil.calculateDuration(startTime, endTime);
			Assert.assertNotNull(failureMessage, timeDuration);
			Assert.assertEquals(failureMessage, new Long(150), new Long(timeDuration));
		} catch (ConferenceTrackManagementException e) {
			Assert.fail(failureMessage);
		}
	}

	public void testCalculateDurationInvalidFormat() {
		String failureMessage = "Duration Calculation is not working";
		String startTime = "1:00:00 PM";
		String endTime = "2:30:00 PM";
		try {
			DateUtil.calculateDuration(startTime, endTime);
			Assert.fail(failureMessage);
		} catch (ConferenceTrackManagementException e) {
			Assert.assertNotNull(failureMessage, e);
			Assert.assertEquals(failureMessage, ErrorCode.CTM_DATE_FORMAT_ERROR.name(), e.getErrorCode());
		}
	}

	public void testUpdateTimePM() {
		String failureMessage = "Time updation is not working";
		String startTime = "1:00 PM";
		Integer length = 60;
		String newTime;
		try {
			newTime = DateUtil.updateTime(startTime, length);
			Assert.assertNotNull(failureMessage, newTime);
			Assert.assertEquals(failureMessage, "2:00 PM", newTime);
		} catch (ConferenceTrackManagementException e) {
			Assert.fail(failureMessage);
		}

	}

	public void testUpdateTimeAM() {
		String failureMessage = "Time updation is not working";
		String startTime = "1:00 AM";
		Integer length = 60;
		String newTime;
		try {
			newTime = DateUtil.updateTime(startTime, length);
			Assert.assertNotNull(failureMessage, newTime);
			Assert.assertEquals(failureMessage, "2:00 AM", newTime);
		} catch (ConferenceTrackManagementException e) {
			Assert.fail(failureMessage);
		}
	}

	public void testUpdateTimeCrossAMPM() {
		String failureMessage = "Time updation is not working";
		String startTime = "11:30 AM";
		Integer length = 120;
		String newTime;
		try {
			newTime = DateUtil.updateTime(startTime, length);
			Assert.assertNotNull(failureMessage, newTime);
			Assert.assertEquals(failureMessage, "1:30 PM", newTime);
		} catch (ConferenceTrackManagementException e) {
			Assert.fail(failureMessage);
		}
	}
	
	public void testUpdateTimeInvalidFormat() {
		String failureMessage = "Time updation is not working";
		String startTime = "12:11:30 AM";
		Integer length = 120;
		try {
			DateUtil.updateTime(startTime, length);
			Assert.fail(failureMessage);
		} catch (ConferenceTrackManagementException e) {
			Assert.assertNotNull(failureMessage, e);
			Assert.assertEquals(failureMessage, ErrorCode.CTM_DATE_FORMAT_ERROR.name(), e.getErrorCode());
		}
	}

}
