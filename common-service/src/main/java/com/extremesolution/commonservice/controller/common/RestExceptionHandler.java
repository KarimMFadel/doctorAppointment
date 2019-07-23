package com.extremesolution.commonservice.controller.common;

import java.nio.file.AccessDeniedException;
import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.extremesolution.commonservice.general.util.exception.BusinessException;
import com.extremesolution.commonservice.general.util.exception.ErrorResponse;

@EnableWebMvc
@RestControllerAdvice
@PropertySource(value={"classpath:messages/messages.properties"},ignoreResourceNotFound=true)
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private Environment env;
	
	private static final String generalErrorCode = "General00000";
	private static final String generalErrorMessage = "General system error.";
	
	private static final String securityErrorCode = "General00001";
	private static final String securityErrorMessage = "Access denied, you don''t have permission to access this functionality";
	
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> handleCustomException(WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode(generalErrorCode);
		errorResponse.setErrorMessage(env.getProperty(generalErrorCode));
		errorResponse.setErrorDetails(request.getDescription(false));
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode(e.getErrorCode());
		String errorMessage = new String();
		// If there's a custom message on the exception, use it
		if (e.getErrorMessage() != null) {
			errorMessage = e.getErrorMessage();
		} else {
			errorMessage = env.getProperty(e.getErrorCode());
		}
		if (e.getParams() != null)
			errorMessage = MessageFormat.format(errorMessage, e.getParams());
		if (errorMessage == null && env.getProperty(generalErrorCode).equals(null))
			errorMessage = generalErrorMessage;
		errorResponse.setErrorMessage(errorMessage);
		errorResponse.setErrorDetails(request.getDescription(false));
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ AccessDeniedException.class })
    protected ResponseEntity<ErrorResponse> handleAccessDeniedExceptions(Exception e, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode(securityErrorCode);
		errorResponse.setErrorMessage(env.getProperty(securityErrorMessage));
		errorResponse.setErrorDetails(request.getDescription(false));
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.FORBIDDEN);
    }
}
