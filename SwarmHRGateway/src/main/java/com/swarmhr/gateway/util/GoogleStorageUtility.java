package com.swarmhr.gateway.util;
 
import java.io.ByteArrayInputStream; 

import com.google.cloud.storage.BlobInfo; 
import com.google.cloud.storage.Storage; 

public class GoogleStorageUtility {
		 
	private Storage storage;
	private String currEnv;
	
	public GoogleStorageUtility() {}
	
	public GoogleStorageUtility(Storage storage, String currEnv) {
		this.storage = storage;
		this.currEnv = currEnv;
	}
	
	public String getPublicURL_orgLogo(String orgId,byte[] file,String contentType, String envUrl) {
		String publicURL = null;
		String bucket = "";
		if (envUrl.contains("uat"))
			bucket = "swarmhr-uat_clients-logo";
		if (envUrl.contains("prod"))
			bucket = "swarmhr-clients-logo";
		String type = contentType.substring(contentType.lastIndexOf("/")+1);
		String fileName = orgId+"_logo."+type; 
		String filePath = "Logos/Thumbnails/"+orgId+"/"+fileName;
		try {
			storage.create(BlobInfo.newBuilder(bucket, filePath)
					.setContentType(contentType).build(),
					new ByteArrayInputStream(file));
			publicURL = "https://storage.googleapis.com/"+bucket+"/"+filePath;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return publicURL;
		
	}
	
	public boolean deleteDocument(String orgId, String contentType, String envUrl) {
		String bucket = "";
		String type = contentType.substring(contentType.lastIndexOf("/")+1);
		String fileName = orgId+"_logo."+type; 
		String filePath = "Logos/Thumbnails/"+orgId+"/"+fileName;
		
		if (envUrl.contains("uat"))
			bucket = "swarmhr-uat_clients-logo";
		if (envUrl.contains("prod"))
			bucket = "swarmhr-clients-logo";  

		String path = "https://storage.googleapis.com/"+bucket+"/"+filePath;
		if (storage.get(bucket).get(path) != null) { 
			return storage.delete(storage.get(bucket).get(path).getBlobId());
		}
 
		return false;

	}
}
