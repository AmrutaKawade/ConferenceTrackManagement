package com.amruta.exception;

public class ConferenceTrackManagementException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String errorCode;
	private final String errorMessage;
	
	public ConferenceTrackManagementException(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

}
