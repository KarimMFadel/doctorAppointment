package com.extremesolution.patientservice.repository.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Repository;

import com.extremesolution.commonservice.repository.base.AbstractRepository;
import com.extremesolution.patientservice.model.Patient;
import com.extremesolution.patientservice.repository.PatientRepository;

@Repository
public class PatientRepositoryImpl extends AbstractRepository<Patient> implements PatientRepository, ApplicationListener<ContextRefreshedEvent> {

	@Value("${database.config.collection.patients.collectionName}")
	String collectionName;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		setCollectionReference(collectionName);		
	}
}
