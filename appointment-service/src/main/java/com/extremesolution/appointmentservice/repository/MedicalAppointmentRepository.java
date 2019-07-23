package com.extremesolution.appointmentservice.repository;

import java.util.Map;

import com.extremesolution.appointmentservice.model.MedicalAppointment;
import com.google.cloud.firestore.DocumentSnapshot;

public interface MedicalAppointmentRepository {

	
	public String save(MedicalAppointment object);
	public void update(String id, MedicalAppointment object);
	public DocumentSnapshot get(String id);
	public Boolean delete(String id);
	public Map<String,MedicalAppointment> getAllAvailableMedicalAppointmentOfDoctor(String doctor_id);
	
}
