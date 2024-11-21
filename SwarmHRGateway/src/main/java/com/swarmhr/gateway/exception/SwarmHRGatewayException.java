package com.swarmhr.gateway.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.swarmhr.gateway.vo.SwarmHrGatewayErrorObject; 

public class SwarmHRGatewayException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	private HttpStatus httpStatus;
	
	private List<SwarmHrGatewayErrorObject> gatewayErrorMsgList;

	
	public SwarmHRGatewayException() {
		
	}
	
	public SwarmHRGatewayException(Exception e) {
		super(e);
	}
	
	public SwarmHRGatewayException(String message, HttpStatus httpStatus) {
		this.message = message;
		this.httpStatus = httpStatus;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}

	public SwarmHRGatewayException(List<SwarmHrGatewayErrorObject> gatewayErrorMsgList) {
		this.gatewayErrorMsgList = gatewayErrorMsgList;
	}
	
	public List<SwarmHrGatewayErrorObject> getAtsErrorLis(){
		return gatewayErrorMsgList;
	}
}
