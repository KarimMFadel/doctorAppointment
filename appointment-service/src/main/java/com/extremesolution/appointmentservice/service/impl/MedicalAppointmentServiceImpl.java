package com.extremesolution.appointmentservice.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extremesolution.appointmentservice.model.MedicalAppointment;
import com.extremesolution.appointmentservice.repository.MedicalAppointmentRepository;
import com.extremesolution.appointmentservice.restClient.RestTemplateClient;
import com.extremesolution.appointmentservice.service.MedicalAppointmentService;
import com.extremesolution.commonservice.general.util.exception.BusinessException;
import com.google.cloud.firestore.DocumentSnapshot;

@Service
public class MedicalAppointmentServiceImpl implements MedicalAppointmentService {
	
	@Autowired
	MedicalAppointmentRepository medicalAppointmentRepository;

	@Autowired
	RestTemplateClient restTemplateClient;
	
	@Override
	public String save(MedicalAppointment medicalAppointment) {
		validateMedicalAppointment(medicalAppointment);
		medicalAppointment.setRetire(false);
		// there is no any patient register yet on this MedicalAppointment
		medicalAppointment.setCurrentPatientCapacity(0);
		return medicalAppointmentRepository.save(medicalAppointment);
	}

	private void validateMedicalAppointment(MedicalAppointment medicalAppointment) {

		if(restTemplateClient.getDoctor(medicalAppointment.getDoctorId()) == null)
			throw new BusinessException("General00005",new Object[] {medicalAppointment.getDoctorId()});
		
	}

	@Override
	public void update(String id,MedicalAppointment  object) {
		MedicalAppointment oldobject = this.get(id);
		if(object.getRetire() == null)
			object.setRetire(false);
		if(object.getCurrentPatientCapacity() == null)
			object.setCurrentPatientCapacity(oldobject.getCurrentPatientCapacity());
		medicalAppointmentRepository.update(id, object);
	}

	@Override
	public MedicalAppointment get(String id) {
		DocumentSnapshot documentSnapshot = medicalAppointmentRepository.get(id);

		// convert document to POJO
		MedicalAppointment medicalAppointment= documentSnapshot.toObject(MedicalAppointment.class);
		if(medicalAppointment.getRetire())
			throw new BusinessException("General00005",new Object[] {id});
		return documentSnapshot.toObject(MedicalAppointment.class);
	}

	@Override
	public void delete(String id) {
		MedicalAppointment medicalAppointment= this.get(id);
		medicalAppointment.setRetire(true);
		medicalAppointmentRepository.update(id,medicalAppointment);
	}

	@Override
	public Map<String,MedicalAppointment> getAllAvailableMedicalAppointmentOfDoctor(String doctor_id) {
		return medicalAppointmentRepository.getAllAvailableMedicalAppointmentOfDoctor(doctor_id);
		
	}
	
	@Override
	public void increaseCurrentPatientCapacity(String medicalAppointment_id) {
		if(! validateMaxCapacity(medicalAppointment_id))
			throw new BusinessException("General00006",new Object[] {medicalAppointment_id});

		MedicalAppointment medicalAppointment= this.get(medicalAppointment_id);
		medicalAppointment.setCurrentPatientCapacity(medicalAppointment.getCurrentPatientCapacity()+1);
		medicalAppointmentRepository.update(medicalAppointment_id, medicalAppointment);
	}

	@Override
	public boolean validateMaxCapacity(String medicalAppointment_id) {
		MedicalAppointment medicalAppointment = null;
		try {
			medicalAppointment= this.get(medicalAppointment_id);
		}catch (BusinessException e) {
			return false;
		}
		return (medicalAppointment.getCurrentPatientCapacity() < medicalAppointment.getMaxPatientCapacity())? true : false;
	}

}
