package com.amruta.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.amruta.exception.ConferenceTrackManagementException;
import com.amruta.util.DateUtil;

public class Session implements Comparable<Session>{
	private String startTime;
	private String endTime;
	private int duration;
	private String type;
	private List<Talk> talks;
	
	/**
	 * Copy Constructor
	 */
	public Session(Session session) {
		this.startTime = session.startTime;
		this.endTime = session.endTime;
		this.type = session.type;
		this.duration = session.duration;
	}
	
	public Session() {
		// TODO Auto-generated constructor stub
	}
	public Session(String startTime, String endTime, String type) throws ConferenceTrackManagementException {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.type = type;
		this.duration = DateUtil.calculateDuration(startTime,endTime);
	}
	 
	public String getStartTime() {
		return startTime;
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public int getDuration() {
		return duration;
	}
	
	public String getType() {
		return type;
	}

	public List<Talk> getTalks() {
		return talks;
	}

	public void setTalks(List<Talk> talks) {
		this.talks = talks;
	}
	
	@Override
	public String toString() {
		return "Session [Start Time=" + startTime + ", End Time=" + endTime + ", Type=" + type + "]";
	}

	@Override
	public int compareTo(Session o) {
		SimpleDateFormat format = new SimpleDateFormat("h:mm a");
		try {
			Date startThis = format.parse(this.getStartTime());
			Date startO = format.parse(o.getStartTime());
			return (int) (startThis.getTime() - startO.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + duration;
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((talks == null) ? 0 : talks.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Session))
			return false;
		Session other = (Session) obj;
		if (duration != other.duration)
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (talks == null) {
			if (other.talks != null)
				return false;
		} else if (!talks.equals(other.talks))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	
}
