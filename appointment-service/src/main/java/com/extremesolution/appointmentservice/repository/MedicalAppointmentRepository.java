package com.extremesolution.appointmentservice.repository;

import java.util.List;
import java.util.Map;

import com.extremesolution.appointmentservice.model.MedicalAppointment;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;

public interface MedicalAppointmentRepository {

	
	public String save(MedicalAppointment object);
	public void update(String id, MedicalAppointment object);
	public DocumentSnapshot get(String id);
	public Boolean delete(String id);
	public List<QueryDocumentSnapshot> getAllDocuments();
	public Map<String,MedicalAppointment> getAllAvailableMedicalAppointmentOfDoctor(String doctor_id);
	
}
