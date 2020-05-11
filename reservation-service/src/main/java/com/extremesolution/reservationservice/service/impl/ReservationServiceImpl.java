package com.extremesolution.reservationservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extremesolution.reservationservice.model.Reservation;
import com.extremesolution.reservationservice.repository.ReservationRepository;
import com.extremesolution.reservationservice.restClient.RestTemplateClient;
import com.extremesolution.reservationservice.service.ReservationService;
import com.extremesolution.commonservice.general.util.exception.BusinessException;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	RestTemplateClient restTemplateClient;

	@Override
	public String save(Reservation reservation) {
		
		validateReservation(reservation);
		
		reservation.setRetire(false);
		String reservation_id = reservationRepository.save(reservation);
		
		restTemplateClient.registerPatientOnMedicalAppointment(reservation.getMedicalAppointmentId());
		return reservation_id;
	}

	private void validateReservation(Reservation reservation) {
		if (!restTemplateClient.validateMedicalAppointment(reservation.getMedicalAppointmentId()))
			throw new BusinessException("General00006", new Object[] { reservation.getMedicalAppointmentId() });
		if (restTemplateClient.getPatient(reservation.getPatientId()) == null)
				throw new BusinessException("General00006", new Object[] { reservation.getMedicalAppointmentId() });
	}

	@Override
	public void update(String id, Reservation reservation) {
		this.get(id);
		reservation.setRetire(false);
		reservationRepository.update(id, reservation);
	}

	@Override
	public Reservation get(String id) {
		DocumentSnapshot documentSnapshot = reservationRepository.get(id);

		// convert document to POJO
		if (documentSnapshot.toObject(Reservation.class).getRetire())
			throw new BusinessException("General00005", new Object[] { id });
		return documentSnapshot.toObject(Reservation.class);
	}

	@Override
	public void delete(String id) {
		Reservation reservation = this.get(id);
		reservation.setRetire(true);
		reservationRepository.delete(id);
	}
	
	@Override
	public Map<String,Reservation> getAllUnretireDocuments() {
		List<QueryDocumentSnapshot> documents = reservationRepository.getAllUnretireDocuments();
		Map<String,Reservation> reservations = new HashMap<>();
	    documents.forEach(document->reservations.put(document.getId(),document.toObject(Reservation.class)));
		return reservations;
	}

}
