package com.swarmhr.gateway.vo;

public class SwarmHRAuthResponse {
	private String accessToken ;

    public SwarmHRAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public SwarmHRAuthResponse() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}