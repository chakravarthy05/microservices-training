package com.swarmhr.gateway.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "offboarding_configuration")
public class OffboardConfiguration {

	
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	private String organizationID;
	private String departmentID;
	private String usernames;
	private boolean relievingEsign;
	private String fileType;
	private String fileName;
	private String gcsPath;
	private String createdBy;
	private Date createdDate ;
	private String modifiedBy; 
	private Date modifiedDate ;
	
	public OffboardConfiguration(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrganizationID() {
		return organizationID;
	}

	public void setOrganizationID(String organizationID) {
		this.organizationID = organizationID;
	}

	public String getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(String departmentID) {
		this.departmentID = departmentID;
	}

	public String getUsernames() {
		return usernames;
	}

	public void setUsernames(String usernames) {
		this.usernames = usernames;
	}

	public boolean isRelievingEsign() {
		return relievingEsign;
	}

	public void setRelievingEsign(boolean relievingEsign) {
		this.relievingEsign = relievingEsign;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getGcsPath() {
		return gcsPath;
	}

	public void setGcsPath(String gcsPath) {
		this.gcsPath = gcsPath;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "OffboardConfiguration [id=" + id + ", organizationID=" + organizationID + ", departmentID="
				+ departmentID + ", usernames=" + usernames + ", relievingEsign=" + relievingEsign + ", fileType="
				+ fileType + ", fileName=" + fileName + ", gcsPath=" + gcsPath + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate
				+ "]";
	}

}
