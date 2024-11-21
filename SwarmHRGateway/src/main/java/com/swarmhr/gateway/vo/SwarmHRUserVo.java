package com.swarmhr.gateway.vo;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.swarmhr.gateway.entity.OffboardConfiguration;
import com.swarmhr.gateway.entity.SwarmHRChannelPartner;

@JsonInclude(Include.NON_NULL)
public class SwarmHRUserVo {

	private String userName;
	private String[] roles;
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
	private String orgId;
	private String organizationName;
	private String deptId;
	private String departmentName;
	private String env;
	private String authentication;
	private String authenticationType;
	private SwarmHREmail emailMessage;
	private SwarmHrTextMessage textMessage;
	private boolean spAdminFlag;
	private boolean channelPartnerFlag;
	private SwarmHRChannelPartner channelPartner;
	private String orgLogo;
	private String deptLogo;
	private List<SwarmHRFeatureVo> featureList;
	private List<AccessGroup> groupList;
	private String country;
	private Map<String, Object> reportList;
	private String offConfigUsers;
	private String headerCountStatus;
	private String defaultTableEntry;

	public SwarmHRUserVo() {

	}

	public SwarmHRUserVo(String userName, String[] roles, String firstName, String lastName, String email,
			String mobile, String orgId, String organizationName, String deptId, String departmentName, String env,
			String authentication, String authenticationType, SwarmHREmail emailMessage, SwarmHrTextMessage textMessage,
			boolean spAdminFlag, boolean channelPartnerFlag, SwarmHRChannelPartner channelPartner, String orgLogo,
			String deptLogo, List<SwarmHRFeatureVo> featureList,String country,Map<String, Object> reportList) {
		super();
		this.userName = userName;
		this.roles = roles;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.orgId = orgId;
		this.organizationName = organizationName;
		this.deptId = deptId;
		this.departmentName = departmentName;
		this.env = env;
		this.authentication = authentication;
		this.authenticationType = authenticationType;
		this.emailMessage = emailMessage;
		this.textMessage = textMessage;
		this.spAdminFlag = spAdminFlag;
		this.channelPartnerFlag = channelPartnerFlag;
		this.channelPartner = channelPartner;
		this.orgLogo = orgLogo;
		this.deptLogo = deptLogo;
		this.featureList = featureList;
		this.country = country;
		this.reportList= reportList;
	}
	
	public SwarmHRUserVo(String userName, String[] roles, String firstName, String lastName, String email,
			String mobile, String orgId, String organizationName, String deptId, String departmentName, String env,
			String authentication, String authenticationType, SwarmHREmail emailMessage, SwarmHrTextMessage textMessage,
			boolean spAdminFlag, boolean channelPartnerFlag, SwarmHRChannelPartner channelPartner, String orgLogo,
			String deptLogo, List<SwarmHRFeatureVo> featureList, String country, Map<String, Object> reportList,
			String offConfigUsers, String headerCountStatus, String defaultTableEntry) {
		super();
		this.userName = userName;
		this.roles = roles;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.orgId = orgId;
		this.organizationName = organizationName;
		this.deptId = deptId;
		this.departmentName = departmentName;
		this.env = env;
		this.authentication = authentication;
		this.authenticationType = authenticationType;
		this.emailMessage = emailMessage;
		this.textMessage = textMessage;
		this.spAdminFlag = spAdminFlag;
		this.channelPartnerFlag = channelPartnerFlag;
		this.channelPartner = channelPartner;
		this.orgLogo = orgLogo;
		this.deptLogo = deptLogo;
		this.featureList = featureList;
		this.country = country;
		this.reportList = reportList;
		this.offConfigUsers = offConfigUsers;
		this.headerCountStatus = headerCountStatus;
		this.defaultTableEntry = defaultTableEntry;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public String getAuthentication() {
		return authentication;
	}

	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}

	public String getAuthenticationType() {
		return authenticationType;
	}

	public void setAuthenticationType(String authenticationType) {
		this.authenticationType = authenticationType;
	}

	public SwarmHREmail getEmailMessage() {
		return emailMessage;
	}

	public void setEmailMessage(SwarmHREmail emailMessage) {
		this.emailMessage = emailMessage;
	}

	public SwarmHrTextMessage getTextMessage() {
		return textMessage;
	}

	public void setTextMessage(SwarmHrTextMessage textMessage) {
		this.textMessage = textMessage;
	}

	public boolean isSpAdminFlag() {
		return spAdminFlag;
	}

	public void setSpAdminFlag(boolean spAdminFlag) {
		this.spAdminFlag = spAdminFlag;
	}

	public boolean isChannelPartnerFlag() {
		return channelPartnerFlag;
	}

	public void setChannelPartnerFlag(boolean channelPartnerFlag) {
		this.channelPartnerFlag = channelPartnerFlag;
	}

	public SwarmHRChannelPartner getChannelPartner() {
		return channelPartner;
	}

	public void setChannelPartner(SwarmHRChannelPartner channelPartner) {
		this.channelPartner = channelPartner;
	}

	public String getOrgLogo() {
		return orgLogo;
	}

	public void setOrgLogo(String orgLogo) {
		this.orgLogo = orgLogo;
	}

	public String getDeptLogo() {
		return deptLogo;
	}

	public void setDeptLogo(String deptLogo) {
		this.deptLogo = deptLogo;
	}

	public List<SwarmHRFeatureVo> getFeatureList() {
		return featureList;
	}

	public void setFeatureList(List<SwarmHRFeatureVo> featureList2) {
		this.featureList = featureList2;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Map<String, Object> getReportList() {
		return reportList;
	}

	public void setReportList(Map<String, Object> reportList) {
		this.reportList = reportList;
	}

	public String getOffConfigUsers() {
		return offConfigUsers;
	}

	public void setOffConfigUsers(String offConfigUsers) {
		this.offConfigUsers = offConfigUsers;
	}

	public String getHeaderCountStatus() {
		return headerCountStatus;
	}

	public void setHeaderCountStatus(String headerCountStatus) {
		this.headerCountStatus = headerCountStatus;
	}

	public String getDefaultTableEntry() {
		return defaultTableEntry;
	}

	public void setDefaultTableEntry(String value) {
		this.defaultTableEntry = value;
	}

	public List<AccessGroup> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<AccessGroup> groupList) {
		this.groupList = groupList;
	}
	
}
