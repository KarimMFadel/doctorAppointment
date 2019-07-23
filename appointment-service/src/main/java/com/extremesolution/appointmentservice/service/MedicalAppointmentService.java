package com.extremesolution.appointmentservice.service;

import java.util.Map;

import com.extremesolution.appointmentservice.model.MedicalAppointment;
import com.extremesolution.commonservice.service.base.BaseService;

public interface MedicalAppointmentService extends BaseService<MedicalAppointment> {

	Map<String,MedicalAppointment> getAllAvailableMedicalAppointmentOfDoctor(String doctor_id);
	public void increaseCurrentPatientCapacity(String MedicalAppointment_id);
	public boolean validateMaxCapacity(String medicalAppointment_id);

}
