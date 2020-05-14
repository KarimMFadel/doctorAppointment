package com.extremesolution.appointmentservice.restClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.extremesolution.commonservice.dto.DoctorDto;

@FeignClient(name = "doctor-service")
public interface DoctorClient {

	@RequestMapping(method = RequestMethod.GET, value = "/doctor/{id}")
	DoctorDto get(@PathVariable("id") String id);

}