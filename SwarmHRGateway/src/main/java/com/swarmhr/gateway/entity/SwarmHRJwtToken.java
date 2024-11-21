package com.swarmhr.gateway.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "swarmhr_jwt_token")
@Entity
public class SwarmHRJwtToken implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(name = "token")
	private String token;

	@Column(name = "creation_date_time")
	private String creationDateTime;

	@Column(name = "user_info")
	private String userInfoJson;

	@Column(name = "username")
	private String username;

	public SwarmHRJwtToken() {

	}

	public SwarmHRJwtToken(String token, String creationDateTime, String userInfoJson, String username) {
		this.token = token;
		this.creationDateTime = creationDateTime;
		this.userInfoJson = userInfoJson;
		this.username = username;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCreationDate() {
		return creationDateTime;
	}

	public void setCreationDate(String creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public String getUserInfoJson() {
		return userInfoJson;
	}

	public void setUserInfoJson(String userInfoJson) {
		this.userInfoJson = userInfoJson;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "SwarmHRJwtToken [Id=" + Id + ", token=" + token + ", creationDateTime=" + creationDateTime
				+ ", userInfoJson=" + userInfoJson + "]";
	}

}
