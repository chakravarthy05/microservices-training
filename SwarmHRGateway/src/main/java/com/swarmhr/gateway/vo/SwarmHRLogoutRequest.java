package com.swarmhr.gateway.vo;

import java.util.Date;

public class SwarmHRLogoutRequest {

	private Date date;
	private String time;
	private String timezone;
	private String ip;

	public SwarmHRLogoutRequest() {
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
