package com.amruta.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.amruta.constant.Constants;
import com.amruta.constant.ErrorCode;
import com.amruta.exception.ConferenceTrackManagementException;
import com.amruta.logger.ConferenceLogger;

public class DateUtil {

	/**
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ConferenceTrackManagementException 
	 */
	public static int calculateDuration(String startTime, String endTime) throws ConferenceTrackManagementException {
		SimpleDateFormat format = new SimpleDateFormat(Constants.TIME_FORMAT);
		try {
			Date start = format.parse(startTime);
			Date end = format.parse(endTime);

			Long duration = end.getTime() - start.getTime();
			return (int) ((duration / 1000) / 60);
		} catch (ParseException e) {
			ConferenceLogger.error(e.getMessage());
			throw new ConferenceTrackManagementException(ErrorCode.CTM_DATE_FORMAT_ERROR.name(),
					ErrorMessageUtil.getErrorMessage(ErrorCode.CTM_DATE_FORMAT_ERROR.name()));
		}
	}

	/**
	 * 
	 * @param time
	 * @param length
	 * @return
	 * @throws ConferenceTrackManagementException 
	 */
	public static String updateTime(String time, Integer length) throws ConferenceTrackManagementException {
		SimpleDateFormat df = new SimpleDateFormat(Constants.TIME_FORMAT);
		Date d;
		Calendar cal = Calendar.getInstance();
		try {
			d = df.parse(time);
			cal.setTime(d);
			cal.add(Calendar.MINUTE, length);
		} catch (ParseException e) {
			ConferenceLogger.error(e.getMessage());
			throw new ConferenceTrackManagementException(ErrorCode.CTM_DATE_FORMAT_ERROR.name(),
					ErrorMessageUtil.getErrorMessage(ErrorCode.CTM_DATE_FORMAT_ERROR.name()));
		}
		return df.format(cal.getTime());
	}
	
	/**
	 * if time1 < time2 returns less than 0
	 * else if time1 == time2 returns 0
	 * else returns greater than 0
	 * @param time1
	 * @param time2
	 * @return
	 * @throws ConferenceTrackManagementException
	 */
	public static int compareTime(String time1, String time2) throws ConferenceTrackManagementException{
		SimpleDateFormat df = new SimpleDateFormat(Constants.TIME_FORMAT);
		Date d1;
		Date d2;
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		try {
			d1 = df.parse(time1);
			cal1.setTime(d1);
			
			d2 = df.parse(time2);
			cal2.setTime(d2);
			
			return cal1.compareTo(cal2);
		} catch (ParseException e) {
			ConferenceLogger.error(e.getMessage());
			throw new ConferenceTrackManagementException(ErrorCode.CTM_DATE_FORMAT_ERROR.name(),
					ErrorMessageUtil.getErrorMessage(ErrorCode.CTM_DATE_FORMAT_ERROR.name()));
		}
	}
}
