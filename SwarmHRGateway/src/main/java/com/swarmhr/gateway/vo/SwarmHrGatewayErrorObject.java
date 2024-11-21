package com.swarmhr.gateway.vo;

public class SwarmHrGatewayErrorObject {

	private String code;
	private String message;

	public SwarmHrGatewayErrorObject() {
	}

	public SwarmHrGatewayErrorObject(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
