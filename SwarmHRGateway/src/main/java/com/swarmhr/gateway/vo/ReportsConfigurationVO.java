package com.swarmhr.gateway.vo;

public class ReportsConfigurationVO {
	
	private String[] listReports;
	
	private String reportID;
	
	private String reportName;
	
	private String createdDate;
	
	private String createdBy;
	
	private String status;
	
	private String modifiedDate;
	
	private String modifiedBy;
	
	public ReportsConfigurationVO() {
		
	}

	public String[] getListReports() {
		return listReports;
	}

	public void setListReports(String[] listReports) {
		this.listReports = listReports;
	}

	public String getReportID() {
		return reportID;
	}

	public void setReportID(String reportID) {
		this.reportID = reportID;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

