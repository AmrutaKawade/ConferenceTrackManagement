package com.amruta.bean;

import java.util.List;

public class Track {
	private List<Session> sessionList;	
	
	public Track() {
		
	}
	public Track(List<Session> sessionList) {
		super();
		this.sessionList = sessionList;
	}

	/**
	 * @param sessionList the sessionList to set
	 */
	public void setSessionList(List<Session> sessionList) {
		this.sessionList = sessionList;
	}
	public List<Session> getSessionList() {
		return sessionList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sessionList == null) ? 0 : sessionList.hashCode());
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
		if (!(obj instanceof Track))
			return false;
		Track other = (Track) obj;
		if (sessionList == null) {
			if (other.sessionList != null)
				return false;
		} else if (!sessionList.equals(other.sessionList))
			return false;
		return true;
	}
	
	
}
