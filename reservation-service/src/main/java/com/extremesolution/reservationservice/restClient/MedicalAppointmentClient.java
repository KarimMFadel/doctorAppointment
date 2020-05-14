package com.extremesolution.reservationservice.restClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.extremesolution.commonservice.dto.MedicalAppointmentDto;;

@FeignClient(name = "appointment-service")
public interface MedicalAppointmentClient {

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	MedicalAppointmentDto get(@PathVariable("id") String id);
	
	@RequestMapping(method = RequestMethod.GET, value = "/validate/{id}")
	Boolean validate(@PathVariable("id") String id);
	
	@RequestMapping(method = RequestMethod.GET, value = "/registerPatientOnMedicalAppointment/{id}")
	void registerPatientOnMedicalAppointment(@PathVariable("id") String id);
}
