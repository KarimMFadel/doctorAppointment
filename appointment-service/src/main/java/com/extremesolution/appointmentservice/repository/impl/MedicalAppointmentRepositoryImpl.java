package com.extremesolution.appointmentservice.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Repository;

import com.extremesolution.appointmentservice.model.MedicalAppointment;
import com.extremesolution.appointmentservice.repository.MedicalAppointmentRepository;
import com.extremesolution.commonservice.general.util.exception.BusinessException;
import com.extremesolution.commonservice.repository.base.AbstractRepository;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

@Repository
public class MedicalAppointmentRepositoryImpl extends AbstractRepository<MedicalAppointment>
		implements MedicalAppointmentRepository, ApplicationListener<ContextRefreshedEvent> {

	@Value("${database.config.collection.medicalAppointments.collectionName}")
	String collectionName;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		setCollectionReference(collectionName);
	}

	@Override
	public Map<String,MedicalAppointment> getAllAvailableMedicalAppointmentOfDoctor(String doctor_id) {
		Map<String,MedicalAppointment> medicalAppointments = new HashMap<>();
		ApiFuture<QuerySnapshot> future = collectionReference.whereEqualTo("retire", false)
				.whereEqualTo("doctorId", doctor_id).get();
		// future.get() blocks on response
		List<QueryDocumentSnapshot> documents;
		try {
			documents = future.get().getDocuments();
			for (DocumentSnapshot document : documents) {
				MedicalAppointment medicalAppointment = document.toObject(MedicalAppointment.class);
				if (medicalAppointment.getCurrentPatientCapacity() < medicalAppointment.getMaxPatientCapacity())
					medicalAppointments.put(document.getId(),medicalAppointment);
			}
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException("");
		}
		return medicalAppointments;
	}

}
