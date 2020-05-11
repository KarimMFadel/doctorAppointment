package com.extremesolution.patientservice.repository;

import java.util.List;

import com.extremesolution.patientservice.model.Patient;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;

public interface PatientRepository {


	public String save(Patient object);
	public void update(String id, Patient object);
	public DocumentSnapshot get(String id);
	public Boolean delete(String id);
	public List<QueryDocumentSnapshot> getAllUnretireDocuments();
	
//	public DocumentSnapshot getAll();
}
