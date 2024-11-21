package com.swarmhr.gateway.vo;



import com.google.cloud.datastore.Key;

public class CustomPropertyConfiguration {
	private String requestID;
	private String organizationID;
	private String username;
	private String propertyName;
	private String propertyGroupName;
	private String optionsType;
	private String propertyValue;
	private String createdByUsername;
	private String createdByFullName;
	private String createdBySqlTimestamp;
	private String createdByTimestamp;
	private Key datastoreEntityKey;

	public CustomPropertyConfiguration() {

	}
		
	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

	public String getOrganizationID() {
		return organizationID;
	}

	public void setOrganizationID(String organizationID) {
		this.organizationID = organizationID;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyGroupName() {
		return propertyGroupName;
	}

	public void setPropertyGroupName(String propertyGroupName) {
		this.propertyGroupName = propertyGroupName;
	}

	public String getOptionsType() {
		return optionsType;
	}

	public void setOptionsType(String optionsType) {
		this.optionsType = optionsType;
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}

	public String getCreatedByUsername() {
		return createdByUsername;
	}

	public void setCreatedByUsername(String createdByUsername) {
		this.createdByUsername = createdByUsername;
	}

	public String getCreatedByFullName() {
		return createdByFullName;
	}

	public void setCreatedByFullName(String createdByFullName) {
		this.createdByFullName = createdByFullName;
	}

	public String getCreatedBySqlTimestamp() {
		return createdBySqlTimestamp;
	}

	public void setCreatedBySqlTimestamp(String createdBySqlTimestamp) {
		this.createdBySqlTimestamp = createdBySqlTimestamp;
	}

	public String getCreatedByTimestamp() {
		return createdByTimestamp;
	}

	public void setCreatedByTimestamp(String createdByTimestamp) {
		this.createdByTimestamp = createdByTimestamp;
	}

	public Key getDatastoreEntityKey() {
		return datastoreEntityKey;
	}

	public void setDatastoreEntityKey(Key datastoreEntityKey) {
		this.datastoreEntityKey = datastoreEntityKey;
	}
	
	

}
