package com.extremesolution.doctorservice.repository.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Repository;

import com.extremesolution.commonservice.repository.base.AbstractRepository;
import com.extremesolution.doctorservice.model.Doctor;
import com.extremesolution.doctorservice.repository.DoctorRepository;

@Repository
public class DoctorRepositoryImpl extends AbstractRepository<Doctor>
		implements DoctorRepository, ApplicationListener<ContextRefreshedEvent> {

	@Value("${database.config.collection.doctors.collectionName}")
	String collectionName;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		setCollectionReference(collectionName);
	}
}
