package com.swarmhr.gateway.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "employee")
@Entity
public class SwarmHRUserInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String orgId;
	private String deptId;
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;

	public SwarmHRUserInfo() {

	}

	public SwarmHRUserInfo(String orgId, String deptId, String firstName, String lastName, String email, String mobile) {
		super();
		this.orgId = orgId;
		this.deptId = deptId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile; 
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
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

}
