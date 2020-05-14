package com.extremesolution.appointmentservice.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

import com.extremesolution.appointmentservice.model.MedicalAppointment;
import com.extremesolution.appointmentservice.restClient.DoctorClient;
import com.extremesolution.appointmentservice.service.MedicalAppointmentService;
import com.extremesolution.commonservice.dto.MedicalAppointmentDto;

@RestController
public class MedicalAppointmentController {

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	MedicalAppointmentService medicalAppointmentService;

	@Autowired
	DoctorClient doctorClient;

	DateTimeFormatter formatterDay = DateTimeFormatter.ofPattern("d-MMM-yyyy");
	DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");

	/**
	 * there is an error with (initiate) creating firebase when use @FeignClient we
	 * will use restTemplate as alternative solution.
	 */
//	@Autowired
//	DoctorClient doctorClient;

	@RequestMapping(value = "/availableMedicalAppointment/{doctorId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<MedicalAppointmentDto>> getAllAvailableMedicalAppointmentOfDoctor(
			@PathVariable("doctorId") String doctor_id) {
		Map<String, MedicalAppointment> medicalAppointments = medicalAppointmentService
				.getAllAvailableMedicalAppointmentOfDoctor(doctor_id);
		return new ResponseEntity<>(convertMapToListDto(medicalAppointments), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@RequestBody(required = true) MedicalAppointmentDto medicalAppointmentDto) {
		return medicalAppointmentService.save(convertToObject(medicalAppointmentDto));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<MedicalAppointmentDto> get(@PathVariable("id") String id) {
		return new ResponseEntity<>(convertToDto(medicalAppointmentService.get(id), id), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void update(@RequestBody(required = true) MedicalAppointmentDto medicalAppointmentDto) {
		medicalAppointmentService.update(medicalAppointmentDto.getMedicalAppointmentId(),
				convertToObject(medicalAppointmentDto));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") String id) {
		medicalAppointmentService.delete(id);
	}

	@RequestMapping(value = "/registerPatientOnMedicalAppointment/{MedicalAppointment_id}", method = RequestMethod.GET)
	public void increaseCurrentPatientCapacity(@PathVariable("MedicalAppointment_id") String MedicalAppointment_id) {
		medicalAppointmentService.increaseCurrentPatientCapacity(MedicalAppointment_id);
	}

	@RequestMapping(value = "/validate/{id}", method = RequestMethod.GET)
	public Boolean validate(@PathVariable("id") String id) {
		return medicalAppointmentService.validateMaxCapacity(id);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Object> getAll() {
		return new ResponseEntity<Object>(convertMultiToDto(medicalAppointmentService.getAllUnretireDocuments()), HttpStatus.OK);
	}
	
	private List<MedicalAppointmentDto> convertMultiToDto(Map<String,MedicalAppointment> appointments) {
		List<MedicalAppointmentDto> appointmentDtos = new ArrayList<>();
		appointments.forEach((k,v)-> appointmentDtos.add(convertToDto(v,k)));
		return appointmentDtos;
	}

	private MedicalAppointmentDto convertToDto(MedicalAppointment medicalAppointment, String id) {
		MedicalAppointmentDto medicalAppointmentDto = modelMapper.map(medicalAppointment, MedicalAppointmentDto.class);
		medicalAppointmentDto.setMedicalAppointmentId(id);

		medicalAppointmentDto.setDay(LocalDate.parse(medicalAppointment.getDay(), formatterDay));
		medicalAppointmentDto.setFrom(LocalTime.parse(medicalAppointment.getFrom(), formatterTime));
		medicalAppointmentDto.setTo(LocalTime.parse(medicalAppointment.getTo(), formatterTime));

		medicalAppointmentDto.setDoctorDto(doctorClient.get(medicalAppointment.getDoctorId()));

		return medicalAppointmentDto;
	}

	private MedicalAppointment convertToObject(MedicalAppointmentDto medicalAppointmentDto) {
		MedicalAppointment medicalAppointment = modelMapper.map(medicalAppointmentDto, MedicalAppointment.class);
		medicalAppointment.setDay(formatterDay.format(medicalAppointmentDto.getDay()));
		medicalAppointment.setFrom(formatterTime.format(medicalAppointmentDto.getFrom()));
		medicalAppointment.setTo(formatterTime.format(medicalAppointmentDto.getTo()));
		if (medicalAppointmentDto.getDoctorDto() != null && medicalAppointmentDto.getDoctorDto().getDoctorId() != null
				&& !medicalAppointmentDto.getDoctorDto().getDoctorId().equals(""))
			medicalAppointment.setDoctorId(medicalAppointmentDto.getDoctorDto().getDoctorId());
		return medicalAppointment;
	}

	private List<MedicalAppointmentDto> convertMapToListDto(Map<String, MedicalAppointment> map) {
		List<MedicalAppointmentDto> medicalAppointments = new ArrayList<>();
		map.forEach((key, value) -> medicalAppointments.add(convertToDto(value, key)));
		return medicalAppointments;
	}
}
