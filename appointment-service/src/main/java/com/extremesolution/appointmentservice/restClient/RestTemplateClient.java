package com.extremesolution.appointmentservice.restClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.extremesolution.commonservice.dto.DoctorDto;

@Component
public class RestTemplateClient {


	@Value("${services.doctor.config.url}")
	private String doctorURL;
	
	public DoctorDto getDoctor(String doctorId) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			DoctorDto doctorDto = restTemplate.getForObject(doctorURL+doctorId,DoctorDto.class);
			if(doctorDto!= null)
				return doctorDto;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
