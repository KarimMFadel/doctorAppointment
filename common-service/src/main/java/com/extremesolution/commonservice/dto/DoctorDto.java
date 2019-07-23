package com.extremesolution.commonservice.dto;

import java.io.Serializable;

public class DoctorDto extends UserDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 932738644332042284L;

	String doctorId;

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	
}
