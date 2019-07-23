package com.extremesolution.commonservice.general.util.exception;

public class BusinessException extends RuntimeException{

	private static final long serialVersionUID = 6956409401208497147L;
	private String errorCode;
	private String errorMessage;
	private Object[] params;
	private String errorDetails; 
	
	public BusinessException(String errorCode, String errorMessage){
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public BusinessException(String errorCode, Object[] params){
		this.errorCode = errorCode;
		this.params = params;
	}
	
	public BusinessException(String errorCode) {
		super();
		this.errorCode = errorCode;
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
	public String getErrorDetails() {
		return errorDetails;
	}
	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}
	public Object[] getParams() {
		return params;
	}
	public void setParams(Object[] params) {
		this.params = params;
	}

}
