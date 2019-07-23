package com.extremesolution.commonservice.dto;

import java.io.Serializable;

public class PatientDto extends UserDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -820575763704689135L;

	String patientId;

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
}
