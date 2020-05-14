package com.extremesolution.doctorservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.extremesolution.commonservice.dto.DoctorDto;
import com.extremesolution.doctorservice.model.Doctor;
import com.extremesolution.doctorservice.service.DoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	DoctorService doctorService;

	@RequestMapping(method = RequestMethod.POST)
	public String create(@RequestBody(required=true) DoctorDto doctorDto) {
		return doctorService.save(convertToObject(doctorDto));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<DoctorDto> get(@PathVariable("id") String id) {
		return new ResponseEntity<>(convertToDto(doctorService.get(id),id), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void update(@RequestBody(required=true) DoctorDto doctorDto) {
		doctorService.update(doctorDto.getDoctorId(), convertToObject(doctorDto));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") String id) {
		doctorService.delete(id);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Object> getAll() {
		return new ResponseEntity<Object>(convertMultiToDto(doctorService.getAllUnretireDocuments()), HttpStatus.OK);
	}
	
	private List<DoctorDto> convertMultiToDto(Map<String,Doctor> doctors) {
		List<DoctorDto> doctorDtos = new ArrayList<>();
		doctors.forEach((k,v)-> doctorDtos.add(convertToDto(v,k)));
		return doctorDtos;
	}
	
	private DoctorDto convertToDto(Doctor doctor, String id) {
		DoctorDto doctorDto = modelMapper.map(doctor, DoctorDto.class);
		doctorDto.setDoctorId(id);
		return doctorDto;
	}
	
	private Doctor convertToObject(DoctorDto doctorDto) {
		Doctor doctor = modelMapper.map(doctorDto, Doctor.class);
		return doctor;
	}
}
