package com.extremesolution.commonservice.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class MedicalAppointmentDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8778793031979786108L;
	
	String medicalAppointmentId;
	
	DoctorDto doctorDto;
	LocalDate day;
	LocalTime from;
	LocalTime to;
	Integer maxPatientCapacity;
	
	public String getMedicalAppointmentId() {
		return medicalAppointmentId;
	}
	public void setMedicalAppointmentId(String medicalAppointmentId) {
		this.medicalAppointmentId = medicalAppointmentId;
	}
	public DoctorDto getDoctorDto() {
		return doctorDto;
	}
	public void setDoctorDto(DoctorDto doctorDto) {
		this.doctorDto = doctorDto;
	}
	public LocalDate getDay() {
		return day;
	}
	public void setDay(LocalDate day) {
		this.day = day;
	}
	public LocalTime getFrom() {
		return from;
	}
	public void setFrom(LocalTime from) {
		this.from = from;
	}
	public LocalTime getTo() {
		return to;
	}
	public void setTo(LocalTime to) {
		this.to = to;
	}
	public Integer getMaxPatientCapacity() {
		return maxPatientCapacity;
	}
	public void setMaxPatientCapacity(Integer maxPatientCapacity) {
		this.maxPatientCapacity = maxPatientCapacity;
	}
		
}
