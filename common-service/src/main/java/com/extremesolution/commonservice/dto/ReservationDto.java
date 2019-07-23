package com.extremesolution.commonservice.dto;

import java.io.Serializable;

public class ReservationDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1589146011875011434L;
	
	String reservationId;
	
	PatientDto patientDto;
	MedicalAppointmentDto medicalAppointmentDto;
	
	
	public String getReservationId() {
		return reservationId;
	}
	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}
	public PatientDto getPatientDto() {
		return patientDto;
	}
	public void setPatientDto(PatientDto patientDto) {
		this.patientDto = patientDto;
	}
	public MedicalAppointmentDto getMedicalAppointmentDto() {
		return medicalAppointmentDto;
	}
	public void setMedicalAppointmentDto(MedicalAppointmentDto medicalAppointmentDto) {
		this.medicalAppointmentDto = medicalAppointmentDto;
	}
	
}
