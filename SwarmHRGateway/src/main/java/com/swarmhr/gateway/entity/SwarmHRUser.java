package com.swarmhr.gateway.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "users")
@Entity
public class SwarmHRUser {

//	 @Id
//	 private String id;

	@Id
	private String userName;
	private String password;
	private String orgID;

//	    private String name;
//	    private String lastName;

//	    private Integer active=1;
//	    private boolean isLoacked=false;
//	    private boolean isExpired=false;
//	    private boolean isEnabled=true;

	@OneToMany(targetEntity = SwarmHRRole.class, mappedBy = "role", fetch = FetchType.EAGER)
	private Set<SwarmHRRole> roles;

//		public String getId() {
//			return id;
//		}
//
//		public void setId(String id) {
//			this.id = id;
//		}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//		public String getName() {
//			return name;
//		}
//
//		public void setName(String name) {
//			this.name = name;
//		}
//
//		public String getLastName() {
//			return lastName;
//		}
//
//		public void setLastName(String lastName) {
//			this.lastName = lastName;
//		}

//		public Integer getActive() {
//			return active;
//		}
//
//		public void setActive(Integer active) {
//			this.active = active;
//		}
//
//		public boolean isLoacked() {
//			return isLoacked;
//		}
//
//		public void setLoacked(boolean isLoacked) {
//			this.isLoacked = isLoacked;
//		}
//
//		public boolean isExpired() {
//			return isExpired;
//		}
//
//		public void setExpired(boolean isExpired) {
//			this.isExpired = isExpired;
//		}
//
//		public boolean isEnabled() {
//			return isEnabled;
//		}
//
//		public void setEnabled(boolean isEnabled) {
//			this.isEnabled = isEnabled;
//		}

	public Set<SwarmHRRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<SwarmHRRole> roles) {
		this.roles = roles;
	}

	public String getOrgID() {
		return orgID;
	}

	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}

	
}
