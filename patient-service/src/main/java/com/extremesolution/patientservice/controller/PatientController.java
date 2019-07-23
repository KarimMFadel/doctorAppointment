package com.extremesolution.patientservice.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.extremesolution.commonservice.dto.PatientDto;
import com.extremesolution.patientservice.model.Patient;
import com.extremesolution.patientservice.service.PatientService;

@RestController
public class PatientController {

	private static final ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	PatientService patientService;

	@RequestMapping(method = RequestMethod.POST)
	public String create(@RequestBody(required=true) PatientDto patientDto) {
		return patientService.save(convertToObject(patientDto));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<PatientDto> get(@PathVariable("id") String id) {
		return new ResponseEntity<>(convertToDto(patientService.get(id),id), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void update(@RequestBody(required=true) PatientDto patientDto) {
		patientService.update(patientDto.getPatientId(), convertToObject(patientDto));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") String id) {
		patientService.delete(id);
	}
	
	private PatientDto convertToDto(Patient patient, String id) {
		PatientDto patientDto = modelMapper.map(patient, PatientDto.class);
		patientDto.setPatientId(id);
		return patientDto;
	}
	
	private Patient convertToObject(PatientDto patientDto) {
		Patient patient = modelMapper.map(patientDto, Patient.class);
		return patient;
	}
}
