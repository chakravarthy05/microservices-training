package com.swarmhr.gateway.vo;

public class ReportVo {
	private int id;
	private String reportID;
	private String reportName;
	private String url;
	private String iconClass;
	private String status;
	
	
	private String eName;
	private String sDate;
	private String eDate;
	private String projectID;
	private String username;
	private String projectName;
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProjectID() {
		return projectID;
	}

	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public String getsDate() {
		return sDate;
	}

	public void setsDate(String sDate) {
		this.sDate = sDate;
	}

	public String geteDate() {
		return eDate;
	}

	public void seteDate(String eDate) {
		this.eDate = eDate;
	}

	/**
	 * Default Constructor.
	 */
	public ReportVo() {

	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the reportID
	 */
	public String getReportID() {
		return reportID;
	}

	/**
	 * @param reportID reportID to set
	 */
	public void setReportID(String reportID) {
		this.reportID = reportID;
	}

	/**
	 * @return the reportName
	 */
	public String getReportName() {
		return reportName;
	}

	/**
	 * @param reportName reportName to set
	 */
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the iconClass
	 */
	public String getIconClass() {
		return iconClass;
	}

	/**
	 * @param iconClass iconClass to set
	 */
	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * The string representation of the Report Class
	 */
	@Override
	public String toString() {
		return "Report [id=" + id + ", reportID=" + reportID + ", reportName=" + reportName + ", url=" + url
				+ ", iconClass=" + iconClass + ", status=" + status + ", username=" + username + " , projectName=" + projectName + " , projectID=" + projectID + "]";
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}
