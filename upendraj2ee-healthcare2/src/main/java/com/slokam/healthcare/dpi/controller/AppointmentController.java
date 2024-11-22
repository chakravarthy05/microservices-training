package com.slokam.healthcare.dpi.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.slokam.healthcare.dpi.repo.IAppointmentRepo;
import com.slokam.healthcare.dpi.response.AppointmentResponsePojo;
import com.slokam.healthcare.dpi.service.IAppointmentService;

@RestController
public class AppointmentController {

	// hello how are you by developer 1
	
	@Autowired
	private IAppointmentService appointmentService;
	
	@GetMapping("getAppointmentsByDate/{startDate}/{endDate}")
	public ResponseEntity<List<AppointmentResponsePojo>> getAppointmentsByDate(
			@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate)  throws Exception{
		//Sample Comments
		System.out.println(startDate);
		System.out.println(endDate);
		
		ResponseEntity<List<AppointmentResponsePojo>> rs = null;
		List<AppointmentResponsePojo> results = appointmentService.getAppointmentsByDate(startDate, endDate);
		 if(results!=null && !results.isEmpty()) {
			 rs = new ResponseEntity<List<AppointmentResponsePojo>>(results,HttpStatus.OK);
			
		 }else {
			 rs = new ResponseEntity<List<AppointmentResponsePojo>>(HttpStatus.NOT_FOUND);
		 }
	     return rs;
	}
}
