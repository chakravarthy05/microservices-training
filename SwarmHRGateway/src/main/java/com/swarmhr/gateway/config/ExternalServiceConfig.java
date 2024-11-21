package com.swarmhr.gateway.config;

import java.io.FileInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils; 

import com.google.auth.Credentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Configuration
public class ExternalServiceConfig {

	private static Logger log = LoggerFactory.getLogger(ExternalServiceConfig.class);

	@Autowired
	GoogleStorageConfig googleStorageConfig;

	@Value("${google.cloud.datastore.config-file-name}")
	String gooleCloudDSConfigFileName;

	@Value("${google.cloud.storage.config-file-name}")
	String gooleCloudStorageConfigFileName;

	@Value("${google.cloud.projectId}")
	String projectId;


	@Bean
	public Datastore googleDatastore() throws IOException {
		Credentials credentials = null;
		try {
			log.debug(" Datastore File Name => " + gooleCloudDSConfigFileName);
			credentials = ServiceAccountCredentials
					.fromStream(new FileInputStream(ResourceUtils.getFile(gooleCloudDSConfigFileName)));
		} catch (Exception e) {
			e.printStackTrace();
			credentials = ServiceAccountCredentials.fromStream(new FileInputStream(gooleCloudDSConfigFileName));
		}
		Datastore datastore = DatastoreOptions.newBuilder().setCredentials(credentials)
				.setProjectId(projectId).build().getService();
		return datastore;
	}
	
	@Bean
	public Storage googleCloudStorage() throws IOException {

		log.debug(" Google Cloud Storage Properties => " + googleStorageConfig.toString());

		log.debug(" Creating credentials ");


		log.debug(" Storage File Name => " + gooleCloudStorageConfigFileName);
		Credentials credentials = null;
		try {
			credentials = ServiceAccountCredentials
					.fromStream(new FileInputStream(ResourceUtils.getFile(gooleCloudStorageConfigFileName)));
		} catch (Exception e) {
			e.printStackTrace();
			// Linux server...
			credentials = ServiceAccountCredentials.fromStream(new FileInputStream(gooleCloudStorageConfigFileName));
		}

		Storage storage = StorageOptions.newBuilder().setCredentials(credentials)
				.setProjectId(googleStorageConfig.getProjectId()).build().getService();
		// Blob blob = storage.get("swarm_hr_test01","abc.txt.txt");
		// log.debug(" File Content >>>> "+new String(blob.getContent()));
		return storage;

	}

}
