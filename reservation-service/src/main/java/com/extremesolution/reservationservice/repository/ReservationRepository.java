package com.extremesolution.reservationservice.repository;

import com.extremesolution.reservationservice.model.Reservation;
import com.google.cloud.firestore.DocumentSnapshot;

public interface ReservationRepository {

	
	public String save(Reservation object);
	public void update(String id, Reservation object);
	public DocumentSnapshot get(String id);
	public Boolean delete(String id);
	
//	public DocumentSnapshot getAll();
}
