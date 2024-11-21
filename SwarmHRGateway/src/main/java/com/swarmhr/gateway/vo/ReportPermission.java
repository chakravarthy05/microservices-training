package com.swarmhr.gateway.vo;

import java.util.List;

public class ReportPermission {
	private String reportID;
	private String username;
	private String fullname;
	private String status;
	private List<String> usernameList;
	private String createdBy;
	private String createdTime;

	
	public ReportPermission() {

	}

	/**
	 * @return the reportID
	 */
	public String getReportID() {
		return reportID;
	}
	
	/**
	 * @param reportID  reportID to set
	 */
	public void setReportID(String reportID) {
		this.reportID = reportID;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username  username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname  fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status  status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the usernameList
	 */
	public List<String> getUsernameList() {
		return usernameList;
	}

	/**
	 * @param usernameList  usernameList to set
	 */
	public void setUsernameList(List<String> usernameList) {
		this.usernameList = usernameList;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy  createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdTime
	 */
	public String getCreatedTime() {
		return createdTime;
	}

	/**
	 * @param createdTime  createdTime to set
	 */
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * The string representation of the Report Permission Class
	 */
	@Override
	public String toString() {
		return "ReportPermission [reportID=" + reportID + ", username=" + username + ", fullname=" + fullname
				+ ", status=" + status + ", usernameList=" + usernameList + ", createdBy=" + createdBy
				+ ", createdTime=" + createdTime + "]";
	} 
}