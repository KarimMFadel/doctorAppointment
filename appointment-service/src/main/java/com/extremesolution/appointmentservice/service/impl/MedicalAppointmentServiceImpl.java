package com.extremesolution.appointmentservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extremesolution.appointmentservice.model.MedicalAppointment;
import com.extremesolution.appointmentservice.repository.MedicalAppointmentRepository;
import com.extremesolution.appointmentservice.restClient.DoctorClient;
import com.extremesolution.appointmentservice.service.MedicalAppointmentService;
import com.extremesolution.commonservice.dto.DoctorDto;
import com.extremesolution.commonservice.general.util.exception.BusinessException;

@Service
public class MedicalAppointmentServiceImpl implements MedicalAppointmentService {
	
	@Autowired
	MedicalAppointmentRepository medicalAppointmentRepository;

	@Autowired
	DoctorClient doctorClient;
	
	@Override
	public String save(MedicalAppointment medicalAppointment) {
		validateMedicalAppointment(medicalAppointment);
		medicalAppointment.setRetire(false);
		// there is no any patient register yet on this MedicalAppointment
		medicalAppointment.setCurrentPatientCapacity(0);
		return medicalAppointmentRepository.save(medicalAppointment).getId();
	}

	private void validateMedicalAppointment(MedicalAppointment medicalAppointment) {
		DoctorDto doctorDto= doctorClient.get(medicalAppointment.getDoctorId());
		if( doctorDto == null)
			throw new BusinessException("General00005",new Object[] {medicalAppointment.getDoctorId()});
		
	}

	@Override
	public void update(String id,MedicalAppointment  object) {
		MedicalAppointment oldobject = this.get(id);
		if(object.getRetire() == null)
			object.setRetire(false);
		if(object.getCurrentPatientCapacity() == null)
			object.setCurrentPatientCapacity(oldobject.getCurrentPatientCapacity());
		medicalAppointmentRepository.save(object);
	}

	@Override
	public MedicalAppointment get(String id) {
		MedicalAppointment medicalAppointment = medicalAppointmentRepository.findById(id).orElse(null);

		if(medicalAppointment==null||medicalAppointment.getRetire())
			throw new BusinessException("General00005",new Object[] {id});
		return medicalAppointment;
	}

	@Override
	public void delete(String id) {
		MedicalAppointment medicalAppointment= this.get(id);
		medicalAppointment.setRetire(true);
		medicalAppointmentRepository.save(medicalAppointment);
	}

	@Override
	public Map<String,MedicalAppointment> getAllAvailableMedicalAppointmentOfDoctor(String doctor_id) {
		Map<String,MedicalAppointment> medicalAppointments = new HashMap<>();
		List<MedicalAppointment> appointments = medicalAppointmentRepository.findByRetireFalse();
		appointments.forEach(obj->{
			if (obj.getCurrentPatientCapacity() < obj.getMaxPatientCapacity())
				medicalAppointments.put(obj.getId(),obj);
		});
		return medicalAppointments;
		
	}
	
	@Override
	public void increaseCurrentPatientCapacity(String medicalAppointment_id) {
		if(! validateMaxCapacity(medicalAppointment_id))
			throw new BusinessException("General00006",new Object[] {medicalAppointment_id});

		MedicalAppointment medicalAppointment= this.get(medicalAppointment_id);
		medicalAppointment.setCurrentPatientCapacity(medicalAppointment.getCurrentPatientCapacity()+1);
		medicalAppointmentRepository.save(medicalAppointment);
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

	@Override
	public Map<String,MedicalAppointment> getAllUnretireDocuments() {
		List<MedicalAppointment> documents = medicalAppointmentRepository.findByRetireFalse();
		Map<String,MedicalAppointment> medicalAppointments = new HashMap<>();
	    documents.forEach(document->medicalAppointments.put(document.getId(),document));
		return medicalAppointments;
	}

}
