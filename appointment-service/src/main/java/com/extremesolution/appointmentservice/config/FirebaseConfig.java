package com.extremesolution.appointmentservice.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import com.extremesolution.commonservice.config.database.AbstractFirebaseConfig;

@RefreshScope
@Configuration
public class FirebaseConfig extends AbstractFirebaseConfig {

	@Value("${database.config.url}")
	private String databaseUrl;

	@Value("${database.config.path}")
	private String configPath;
	
	@PostConstruct
	public void init() {
		initConnection(databaseUrl, configPath);
	}

}
