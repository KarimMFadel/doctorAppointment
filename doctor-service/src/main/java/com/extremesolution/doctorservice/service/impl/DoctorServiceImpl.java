package com.extremesolution.doctorservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extremesolution.commonservice.general.util.exception.BusinessException;
import com.extremesolution.doctorservice.model.Doctor;
import com.extremesolution.doctorservice.repository.DoctorRepository;
import com.extremesolution.doctorservice.service.DoctorService;
import com.google.cloud.firestore.DocumentSnapshot;

@Service
public class DoctorServiceImpl implements DoctorService{

	@Autowired
	DoctorRepository doctorRepository; 
	@Override
	public String save(Doctor medicalAppointment) {
		medicalAppointment.setRetire(false);
		return doctorRepository.save(medicalAppointment);
	}

	@Override
	public void update(String id,Doctor  object) {
		this.get(id);
		object.setRetire(false);
		doctorRepository.update(id, object);
	}

	@Override
	public Doctor get(String id) {
		DocumentSnapshot documentSnapshot = doctorRepository.get(id);

		// convert document to POJO
		if(documentSnapshot.toObject(Doctor.class).getRetire())
			throw new BusinessException("General00005",new Object[] {id}); 
		return documentSnapshot.toObject(Doctor.class);
	}

	@Override
	public void delete(String id) {
		Doctor doctor = this.get(id);
		doctor.setRetire(true);
		doctorRepository.update(id,doctor);
	}


}
