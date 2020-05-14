package com.extremesolution.reservationservice.restClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.extremesolution.commonservice.dto.PatientDto;

@FeignClient(name = "patient-service")
public interface PatientClient {

	@RequestMapping(method = RequestMethod.GET, value = "/patient/{id}")
	PatientDto get(@PathVariable("id") String id);
	
}
