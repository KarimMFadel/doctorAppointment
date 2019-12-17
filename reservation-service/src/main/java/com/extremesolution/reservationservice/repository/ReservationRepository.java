package com.extremesolution.reservationservice.repository;

import java.util.List;

import com.extremesolution.reservationservice.model.Reservation;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;

public interface ReservationRepository {

	
	public String save(Reservation object);
	public void update(String id, Reservation object);
	public DocumentSnapshot get(String id);
	public Boolean delete(String id);
	public List<QueryDocumentSnapshot> getAllDocuments();
	
//	public DocumentSnapshot getAll();
}
