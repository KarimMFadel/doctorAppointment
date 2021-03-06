package com.extremesolution.reservationservice.model;

import java.io.Serializable;

import com.extremesolution.commonservice.model.base.AbstractEntity;

public class Reservation extends AbstractEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4469397952630126525L;
	
	String patientId;
	String medicalAppointmentId;
	
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getMedicalAppointmentId() {
		return medicalAppointmentId;
	}
	public void setMedicalAppointmentId(String medicalAppointmentId) {
		this.medicalAppointmentId = medicalAppointmentId;
	}
	
}
