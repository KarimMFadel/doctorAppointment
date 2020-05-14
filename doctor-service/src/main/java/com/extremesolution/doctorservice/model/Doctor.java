package com.extremesolution.doctorservice.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.extremesolution.commonservice.model.base.User;

@Document(collection = "Doctor")
public class Doctor extends User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3845953701912473935L;

	
	
}
