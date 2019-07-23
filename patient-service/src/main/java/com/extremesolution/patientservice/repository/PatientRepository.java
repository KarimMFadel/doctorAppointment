package com.extremesolution.patientservice.repository;

import com.extremesolution.patientservice.model.Patient;
import com.google.cloud.firestore.DocumentSnapshot;

public interface PatientRepository {


	public String save(Patient object);
	public void update(String id, Patient object);
	public DocumentSnapshot get(String id);
	public Boolean delete(String id);
	
//	public DocumentSnapshot getAll();
}
