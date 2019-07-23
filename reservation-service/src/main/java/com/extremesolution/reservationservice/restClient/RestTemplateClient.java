package com.extremesolution.reservationservice.restClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.extremesolution.commonservice.dto.MedicalAppointmentDto;
import com.extremesolution.commonservice.dto.PatientDto;

@Component
public class RestTemplateClient {

	@Value("${services.patient.config.url}")
	private String patientURL;

	@Value("${services.appointment.config.url}")
	private String appointmentURL;

	public PatientDto getPatient(String patientId){
		try {
			RestTemplate restTemplate = new RestTemplate();
			PatientDto patientDto = restTemplate.getForObject(patientURL+patientId,PatientDto.class);	
			if(patientDto!=null)
				return patientDto;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public MedicalAppointmentDto getMedicalAppointment(String MedicalAppointmentId){
		try {
			RestTemplate restTemplate = new RestTemplate();
			MedicalAppointmentDto medicalAppointmentDto = restTemplate.getForObject(appointmentURL+MedicalAppointmentId,MedicalAppointmentDto.class);	
			if(medicalAppointmentDto!=null)
				return medicalAppointmentDto;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Boolean validateMedicalAppointment(String MedicalAppointmentId) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			return restTemplate
					.getForObject(appointmentURL + "validate/" + MedicalAppointmentId, Boolean.class);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void registerPatientOnMedicalAppointment(String MedicalAppointmentId) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForObject(
				appointmentURL + "registerPatientOnMedicalAppointment/" + MedicalAppointmentId, void.class);
	}
}
