package com.extremesolution.commonservice.config.database;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.util.ResourceUtils;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public abstract class AbstractFirebaseConfig {

	abstract public void init();
	
	public void initConnection(String databaseUrl, String configPath) {

		/**
		 * https://firebase.google.com/docs/server/setup
		 * 
		 * Create service account , download json
		 */
		FirebaseOptions options;
		try {
			FileInputStream refreshToken = new FileInputStream(ResourceUtils.getFile(
				      "classpath:"+configPath));
			
			options = new FirebaseOptions.Builder()
				    .setCredentials(GoogleCredentials.fromStream(refreshToken))
				    .setDatabaseUrl(databaseUrl)
				    .build();	
			if(FirebaseApp.getApps().isEmpty()) 
				FirebaseApp.initializeApp(options);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
