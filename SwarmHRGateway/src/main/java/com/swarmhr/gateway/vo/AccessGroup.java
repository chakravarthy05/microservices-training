package com.swarmhr.gateway.vo;

import java.util.List;

public class AccessGroup {
	private String groupName;
	private String groupID;
	private String organizationID;
	private List<SwarmHRFeatureVo> featureList;
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupID() {
		return groupID;
	}
	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}
	public String getOrganizationID() {
		return organizationID;
	}
	public void setOrganizationID(String organizationID) {
		this.organizationID = organizationID;
	}
	public List<SwarmHRFeatureVo> getFeatureList() {
		return featureList;
	}
	public void setFeatureList(List<SwarmHRFeatureVo> featureList) {
		this.featureList = featureList;
	}

}
