package com.extremesolution.appointmentservice;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.extremesolution.appointmentservice.controller.MedicalAppointmentController;
import com.extremesolution.appointmentservice.model.MedicalAppointment;
import com.extremesolution.appointmentservice.service.MedicalAppointmentService;

@RunWith(SpringRunner.class)
@SpringBootTest
//@WebMvcTest(MedicalAppointmentController.class)
@WebAppConfiguration
public class AppointmentServiceApplicationTests {

//	@Value("${database.config.collection.medicalAppointments.collectionName:MedicalAppointments}")
//    String collectionName;
	
//	@Autowired
//	private MockMvc mockMvc;
	
//	@MockBean
//	MedicalAppointmentService appointmentService;
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void getMedicalAppointment_shouldReturnMedicalAppointmentInfo() {
//		BDDMockito.given(appointmentService.get(ArgumentMatchers.anyString()))
//					.willReturn(new MedicalAppointment("qweqwe", LocalDate.now(), 
//							LocalTime.now(), LocalTime.now(), 20, 3));
//		try {
//			mockMvc.perform(MockMvcRequestBuilders.get("/"))
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andExpect(MockMvcResultMatchers.jsonPath("maxPatientCapacity").value("20"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}
