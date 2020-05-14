package com.extremesolution.reservationservice.repository;

import org.springframework.stereotype.Repository;

import com.extremesolution.commonservice.repository.base.BaseRepository;
import com.extremesolution.reservationservice.model.Reservation;

@Repository
public interface ReservationRepository extends BaseRepository<Reservation, String>{

	
//	public String save(Reservation object);
	
//	public void update(String id, Reservation object);
	
//	public DocumentSnapshot get(String id);
//	public Boolean delete(String id);
	
	
//	public DocumentSnapshot getAll();
}
