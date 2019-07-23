package com.extremesolution.appointmentservice.model;

import java.io.Serializable;

import com.extremesolution.commonservice.model.base.AbstractEntity;

public class MedicalAppointment extends AbstractEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3382069415854546355L;
	
	String doctorId;
	String day;
	String from;
	String to;
	Integer maxPatientCapacity;
	Integer currentPatientCapacity;
	
	public MedicalAppointment() {
		super();
	}

	public MedicalAppointment(String doctorId, String day, String from, String to,
			Integer maxPatientCapacity, Integer currentPatientCapacity) {
		super();
		this.doctorId = doctorId;
		this.day = day;
		this.from = from;
		this.to = to;
		this.maxPatientCapacity = maxPatientCapacity;
		this.currentPatientCapacity = currentPatientCapacity;
	}
	
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public Integer getMaxPatientCapacity() {
		return maxPatientCapacity;
	}
	public void setMaxPatientCapacity(Integer maxPatientCapacity) {
		this.maxPatientCapacity = maxPatientCapacity;
	}
	public Integer getCurrentPatientCapacity() {
		return currentPatientCapacity;
	}
	public void setCurrentPatientCapacity(Integer currentPatientCapacity) {
		this.currentPatientCapacity = currentPatientCapacity;
	}
	
	
}
