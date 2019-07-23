package com.extremesolution.reservationservice.repository.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Repository;

import com.extremesolution.reservationservice.model.Reservation;
import com.extremesolution.reservationservice.repository.ReservationRepository;
import com.extremesolution.commonservice.repository.base.AbstractRepository;

@Repository
public class ReservationRepositoryImpl extends AbstractRepository<Reservation> implements ReservationRepository, ApplicationListener<ContextRefreshedEvent> {

	@Value("${database.config.collection.reservations.collectionName}")
	String collectionName;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		setCollectionReference(collectionName);		
	}

}
