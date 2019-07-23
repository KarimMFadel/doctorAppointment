package com.extremesolution.patientservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extremesolution.commonservice.general.util.exception.BusinessException;
import com.extremesolution.patientservice.model.Patient;
import com.extremesolution.patientservice.repository.PatientRepository;
import com.extremesolution.patientservice.service.PatientService;
import com.google.cloud.firestore.DocumentSnapshot;

@Service
public class PatientServiceImpl implements PatientService {
	
	@Autowired
	PatientRepository patientRepository;

	@Override
	public String save(Patient patient) {
		patient.setRetire(false);
		return patientRepository.save(patient);
	}

	@Override
	public void update(String id,Patient  patient) {
		this.get(id);
		if(patient.getRetire() == null)
			patient.setRetire(false);
		patientRepository.update(id, patient);
	}

	@Override
	public Patient get(String id) {
		DocumentSnapshot documentSnapshot = patientRepository.get(id);
		// convert document to POJO
		if(documentSnapshot.toObject(Patient.class).getRetire())
			throw new BusinessException("General00005",new Object[] {id}); 
		return documentSnapshot.toObject(Patient.class);
	}

	@Override
	public void delete(String id) {
		Patient patient = this.get(id);
		patient.setRetire(true);
		patientRepository.update(id, patient);
	}


}
