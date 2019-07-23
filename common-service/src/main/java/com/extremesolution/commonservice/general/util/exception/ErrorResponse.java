package com.extremesolution.commonservice.general.util.exception;

public class ErrorResponse {

	private String errorCode;
	private String errorMessage;
	private Object errorDetails;
	
	public ErrorResponse() {
		super();
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(Object errorDetails) {
		this.errorDetails = errorDetails;
	}
	
}
