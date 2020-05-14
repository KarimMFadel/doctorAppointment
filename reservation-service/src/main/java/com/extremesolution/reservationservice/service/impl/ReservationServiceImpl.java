package com.extremesolution.reservationservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extremesolution.reservationservice.model.Reservation;
import com.extremesolution.reservationservice.repository.ReservationRepository;
import com.extremesolution.reservationservice.restClient.MedicalAppointmentClient;
import com.extremesolution.reservationservice.restClient.PatientClient;
import com.extremesolution.reservationservice.service.ReservationService;
import com.extremesolution.commonservice.general.util.exception.BusinessException;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	PatientClient patientClient;
	
	@Autowired
	MedicalAppointmentClient medicalAppointmentClient;

	@Override
	public String save(Reservation reservation) {
		
		validateReservation(reservation);
		
		// TODO we need to check if the same patient reserve on the same appointment.
		
		reservation.setRetire(false);
		Reservation newReservation = reservationRepository.save(reservation);
		
		medicalAppointmentClient.registerPatientOnMedicalAppointment(reservation.getMedicalAppointmentId());
		return newReservation.getId();
	}

	private void validateReservation(Reservation reservation) {
		if (!medicalAppointmentClient.validate(reservation.getMedicalAppointmentId()))
			throw new BusinessException("General00006", new Object[] { reservation.getMedicalAppointmentId() });
		if (patientClient.get(reservation.getPatientId()) == null)
				throw new BusinessException("General00006", new Object[] { reservation.getMedicalAppointmentId() });
	}

	@Override
	public void update(String id, Reservation newReservation) {
		Reservation reservation = this.get(id);
		reservation.setRetire(false);
//		reservation.setMedicalAppointmentId(newReservation.getMedicalAppointmentId());
//		reservation.setPatientId(newReservation.getPatientId());
		reservationRepository.save(reservation);
	}

	@Override
	public Reservation get(String id) {
		Reservation reservation = reservationRepository.findById(id).orElse(null);

		if (reservation.getRetire())
			throw new BusinessException("General00005", new Object[] { id });
		return reservation;
	}

	@Override
	public void delete(String id) {
		Reservation reservation = this.get(id);
		reservation.setRetire(true);
		reservationRepository.deleteById(id);
	}
	
	@Override
	public Map<String,Reservation> getAllUnretireDocuments() {
		List<Reservation> ReturnedReservations = reservationRepository.findByRetireFalse();
		Map<String,Reservation> reservations = new HashMap<>();
		ReturnedReservations.forEach(document->reservations.put(document.getId(),document));
		return reservations;
	}

}
