package com.amruta.util;

import java.util.ArrayList;
import java.util.List;

import com.amruta.bean.Talk;
import com.amruta.constant.Constants;
import com.amruta.constant.ErrorCode;
import com.amruta.exception.ConferenceTrackManagementException;

public class TalkUtil {

	/**
	 * Create List Of Talk Lengths
	 * @param talkList
	 * @return
	 */
	public static List<Integer> getTalkLenList(List<Talk> talkList) {
		List<Integer> lenList = new ArrayList<>();
		for (Talk talk : talkList) {
			lenList.add(talk.getLength());
		}
		return lenList;
	}

	/**
	 * From Input List Of String Create List Of Talk Object
	 * @param inputStrTalks
	 * @return
	 * @throws ConferenceTrackManagementException
	 */
	public static List<Talk> getTalkList(List<String> inputStrTalks) throws ConferenceTrackManagementException {

		if (inputStrTalks.isEmpty()) {
			throw new ConferenceTrackManagementException(ErrorCode.CTM_INPUT_EMPTY_ERROR.name(),
					ErrorMessageUtil.getErrorMessage(ErrorCode.CTM_INPUT_EMPTY_ERROR.name()));
		}
		List<Talk> talkList = new ArrayList<>();
		for (String line : inputStrTalks) {
			String title = line;
			String length = line.substring(line.lastIndexOf(Constants.TITLE_LEN_SEPARATOR) + 1);
			String[] time = length.split(Constants.TIME_REGEX);
			Integer duration;

			if (Constants.LIGHTNING.equals(length)) {
				duration = Constants.LIGHTNING_DURATION;
			} else {
				if (Constants.HR.equalsIgnoreCase(time[1].trim())) {
					duration = Integer.valueOf(time[0]) * 60;
				} else {
					duration = Integer.valueOf(time[0]);
				}
			}
			Talk talk = new Talk(title, duration);
			talkList.add(talk);
		}
		return talkList;
	}

}
