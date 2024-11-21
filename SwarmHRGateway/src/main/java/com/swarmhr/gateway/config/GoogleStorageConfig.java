package com.swarmhr.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "google.cloud")
public class GoogleStorageConfig {

	private String bucketname;
    private String clientId;
    private String clientemail;
    private String privatekeyid; 
    private String privatekey;
    private String projectId;
    
    public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getBucketname() {
		return bucketname;
	}
	public void setBucketname(String bucketname) {
		this.bucketname = bucketname;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientemail() {
		return clientemail;
	}
	public void setClientemail(String clientemail) {
		this.clientemail = clientemail;
	}
	public String getPrivatekeyid() {
		return privatekeyid;
	}
	public void setPrivatekeyid(String privatekeyid) {
		this.privatekeyid = privatekeyid;
	}
	public String getPrivatekey() {
		return privatekey;
	}
	public void setPrivatekey(String privatekey) {
		this.privatekey = privatekey;
	}
	@Override
	public String toString() {
		return "GoogleStorageConfig [bucketname=" + bucketname + ", clientId=" + clientId + ", clientemail="
				+ clientemail + ", privatekeyid=" + privatekeyid + ", privatekey=" + privatekey + ", projectId="
				+ projectId + "]";
	}
}
