package com.extremesolution.patientservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Service;

import com.extremesolution.commonservice.general.util.exception.BusinessException;
import com.extremesolution.patientservice.model.Patient;
import com.extremesolution.patientservice.repository.PatientRepository;
import com.extremesolution.patientservice.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService, HealthIndicator {
	
	@Autowired
	PatientRepository patientRepository;

	@Override
	public String save(Patient patient) {
		patient.setRetire(false);
		return patientRepository.save(patient).getId();
	}

	@Override
	public void update(String id,Patient  patient) {
		this.get(id);
		if(patient.getRetire() == null)
			patient.setRetire(false);
		patientRepository.save(patient);
	}

	@Override
	public Patient get(String id) {
		Patient patient = patientRepository.findById(id).orElse(null);
		
		if(patient==null || patient.getRetire())
			throw new BusinessException("General00005",new Object[] {id}); 
		return patient;
	}

	@Override
	public void delete(String id) {
		Patient patient = this.get(id);
		patient.setRetire(true);
		patientRepository.save(patient);
	}

	@Override
	public Map<String,Patient> getAllUnretireDocuments() {
		List<Patient> documents = patientRepository.findByRetireFalse();
		Map<String,Patient> patients = new HashMap<>();
	    documents.forEach(document->patients.put(document.getId(),document));
		return patients;
	}

	/**
	 * it will be use in "http://hostname/actuator/health"
	 * @see org.springframework.boot.actuate.health.HealthIndicator#health()
	 */
	@Override
	public Health health() {
		List<Patient> patients = patientRepository.findByRetireFalse();
		if(patients != null)
			return Health.up().build();
		return Health.down().build();
	}

}
