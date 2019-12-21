package com.extremesolution.doctorservice.repository;

import java.util.List;

import com.extremesolution.doctorservice.model.Doctor;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;

public interface DoctorRepository {

	public String save(Doctor object);
	public void update(String id, Doctor object);
	public DocumentSnapshot get(String id);
	public Boolean delete(String id);
	public List<QueryDocumentSnapshot> getAllUnretireDocuments();

}
