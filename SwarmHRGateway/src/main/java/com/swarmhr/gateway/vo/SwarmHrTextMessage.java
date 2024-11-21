package com.swarmhr.gateway.vo;

public class SwarmHrTextMessage {
	private String username;
	private String orgID;
	private String textMessage;
	private String mobile;
	private int countryCode;

	public SwarmHrTextMessage() {

	}

	public SwarmHrTextMessage(String username, String orgID, String textMessage, String mobile, int countryCode) {
		super();
		this.username = username;
		this.orgID = orgID;
		this.textMessage = textMessage;
		this.mobile = mobile;
		this.countryCode = countryCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOrgID() {
		return orgID;
	}

	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}

	public String getTextMessage() {
		return textMessage;
	}

	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}

}
