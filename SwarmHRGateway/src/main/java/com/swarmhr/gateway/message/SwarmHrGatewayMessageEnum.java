package com.swarmhr.gateway.message;

public enum SwarmHrGatewayMessageEnum {
	
	SWARM_HR_GATEWAY_GET_ERROR("01", "Error While Fetching Data"),
	SWARM_HR_GATEWAY_UPDATE_ERROR("02", "Error While Updating Data"),
	SWARM_HR_GATEWAY_SAVE_ERROR("03", "Error While Inserting Data");

	private String code;
	private String message;

	private SwarmHrGatewayMessageEnum() {
	}

	private SwarmHrGatewayMessageEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public String getCode() {
		return code;
	}

}
