package com.extremesolution.doctorservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extremesolution.commonservice.general.util.exception.BusinessException;
import com.extremesolution.doctorservice.model.Doctor;
import com.extremesolution.doctorservice.repository.DoctorRepository;
import com.extremesolution.doctorservice.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService{

	@Autowired
	DoctorRepository doctorRepository; 
	@Override
	public String save(Doctor medicalAppointment) {
		medicalAppointment.setRetire(false);
		return doctorRepository.save(medicalAppointment).getId();
	}

	@Override
	public void update(String id,Doctor  object) {
		this.get(id);
		object.setRetire(false);
		doctorRepository.save(object);
	}

	@Override
	public Doctor get(String id) {
		Doctor doctor = doctorRepository.findById(id).orElse(null);

		// convert document to POJO
		if(doctor == null || doctor.getRetire())
			throw new BusinessException("General00005",new Object[] {id}); 
		return doctor;
	}

	@Override
	public void delete(String id) {
		Doctor doctor = this.get(id);
		doctor.setRetire(true);
		doctorRepository.save(doctor);
	}

	@Override
	public Map<String,Doctor> getAllUnretireDocuments() {
		List<Doctor> documents = doctorRepository.findByRetireFalse();
		Map<String,Doctor> doctors = new HashMap<>();
	    documents.forEach(document->doctors.put(document.getId(),document));
		return doctors;
	}

}
