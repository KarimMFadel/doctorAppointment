package com.extremesolution.doctorservice.repository;

import com.extremesolution.doctorservice.model.Doctor;
import com.google.cloud.firestore.DocumentSnapshot;

public interface DoctorRepository {

	public String save(Doctor object);
	public void update(String id, Doctor object);
	public DocumentSnapshot get(String id);
	public Boolean delete(String id);

}
