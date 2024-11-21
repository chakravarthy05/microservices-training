package com.swarmhr.gateway.message;

public enum SwarmHRPasswordResponseType {

	Success(251, "Success"), Failed(351, "Failed");

	private Integer code;
	private String message;

	private SwarmHRPasswordResponseType() {
	}

	private SwarmHRPasswordResponseType(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public Integer getCode() {
		return code;
	}

}
