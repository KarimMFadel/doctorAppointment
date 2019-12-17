package com.extremesolution.reservationservice.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.extremesolution.commonservice.dto.ReservationDto;
import com.extremesolution.reservationservice.model.Reservation;
import com.extremesolution.reservationservice.restClient.RestTemplateClient;
import com.extremesolution.reservationservice.service.ReservationService;

@RestController
public class ReservationController {

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	ReservationService reservationService;

	@Autowired
	RestTemplateClient restTemplateClient;

//	@Autowired
//	PatientClient patientClient;

	@RequestMapping(method = RequestMethod.POST)
	public String create(@RequestBody(required = true) ReservationDto reservationDto) {
		return reservationService.save(convertToObject(reservationDto));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<ReservationDto> get(@PathVariable("id") String id) {
		return new ResponseEntity<>(convertToDto(reservationService.get(id), id), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void update(@RequestBody(required = true) ReservationDto reservationDto) {
		reservationService.update(reservationDto.getReservationId(), convertToObject(reservationDto));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") String id) {
		reservationService.delete(id);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Object> getAll() {
		return new ResponseEntity<Object>(convertMultiToDto(reservationService.getAllDocuments()), HttpStatus.OK);
	}
	
	private List<ReservationDto> convertMultiToDto(List<Reservation> reservations) {
		List<ReservationDto> reservationDtos = new ArrayList<>();
		for (Reservation reservation : reservations) {
			reservationDtos.add(convertToDto(reservation,null));
		}
		return reservationDtos;
	}

	private ReservationDto convertToDto(Reservation reservation, String id) {
		ReservationDto reservationDto = modelMapper.map(reservation, ReservationDto.class);
		reservationDto.setReservationId(id);
		reservationDto.setPatientDto(restTemplateClient.getPatient(reservation.getPatientId()));
		reservationDto.setMedicalAppointmentDto(
				restTemplateClient.getMedicalAppointment(reservation.getMedicalAppointmentId()));
		return reservationDto;
	}

	private Reservation convertToObject(ReservationDto reservationDto) {
		Reservation reservation = modelMapper.map(reservationDto, Reservation.class);
		if (reservationDto.getPatientDto() != null && reservationDto.getPatientDto().getPatientId() != null
				&& !reservationDto.getPatientDto().getPatientId().equals(""))
			reservation.setPatientId(reservationDto.getPatientDto().getPatientId());
		if (reservationDto.getMedicalAppointmentDto() != null && reservationDto.getMedicalAppointmentDto().getMedicalAppointmentId() != null
				&& !reservationDto.getMedicalAppointmentDto().getMedicalAppointmentId().equals(""))
			reservation.setMedicalAppointmentId(reservationDto.getMedicalAppointmentDto().getMedicalAppointmentId());
		return reservation;
	}
}
