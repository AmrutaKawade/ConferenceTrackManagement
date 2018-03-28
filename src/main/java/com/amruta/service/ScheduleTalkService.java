/**
 * 
 */
package com.amruta.service;

import java.util.ArrayList;
import java.util.List;

import com.amruta.bean.Session;
import com.amruta.bean.Talk;
import com.amruta.constant.ErrorCode;
import com.amruta.exception.ConferenceTrackManagementException;
import com.amruta.util.ErrorMessageUtil;
import com.amruta.util.TalkUtil;

/**
 * @author amruta
 *
 */
public class ScheduleTalkService {

	/**
	 * @param session
	 * @param talkList
	 * @return scheduled Talk List
	 * @throws ConferenceTrackManagementException
	 */
	public List<Talk> getScheduledTalks(Session session, List<Talk> talkList) throws ConferenceTrackManagementException {
		List<Integer> talkDuartionList = TalkUtil.getTalkLenList(talkList);
		List<Talk> eligibleTalks = new ArrayList<>();
		int duration = session.getDuration()/5;
		
		int n = talkDuartionList.size();

		boolean[][] part = new boolean[duration + 1][n + 1];

		for (int i = 1; i < duration + 1; i++)
			part[i][0] = false;

		for (int j = 0; j < n + 1; j++)
			part[0][j] = true;

		for (int i = 1; i < duration + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				part[i][j] = part[i][j - 1];
				if (i*5 >= talkDuartionList.get(j - 1))
					part[i][j] = part[i][j] || part[(i*5 - talkDuartionList.get(j - 1))/5][j - 1];
			}
		}

		while (duration > 0 && !part[duration][n])
			duration--;
		if (duration > 0)
			getEligibleTalks(eligibleTalks, part, talkDuartionList, talkList, duration, n);
		else
			throw new ConferenceTrackManagementException(ErrorCode.CTM_TALK_CANNOT_FIT_IN_SESSION_ERROR.name(),
					ErrorMessageUtil.getErrorMessage(ErrorCode.CTM_TALK_CANNOT_FIT_IN_SESSION_ERROR.name()));

		return eligibleTalks;
	}

	/**
	 * @param eligibleTalks
	 * @param part
	 * @param talkLenList
	 * @param talkList
	 * @param duration : duration
	 * @param numberOfTalks : length of talkLenList
	 */
	private void getEligibleTalks(List<Talk> eligibleTalks, boolean[][] part, List<Integer> talkLenList,
			List<Talk> talkList, int duration, int numberOfTalks) {
		if (!part[duration][numberOfTalks] || duration == 0)
			return;

		if (part[duration][numberOfTalks - 1])
			getEligibleTalks(eligibleTalks, part, talkLenList, talkList, duration, numberOfTalks - 1);
		else {
			getEligibleTalks(eligibleTalks, part, talkLenList, talkList, (duration*5 - talkLenList.get(numberOfTalks - 1))/5, numberOfTalks - 1);
			eligibleTalks.add(talkList.get(numberOfTalks - 1));
		}
	}
}
