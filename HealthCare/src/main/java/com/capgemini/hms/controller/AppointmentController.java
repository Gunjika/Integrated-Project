package com.capgemini.hms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.hms.exception.TestException;
import com.capgemini.hms.service.AppointmentService;

@RestController
public class AppointmentController {

	@Autowired
	AppointmentService appointmentService;

	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/approveAppointment/{testAppointmentId}")
	public ResponseEntity<String> approveappointment(@PathVariable Integer testAppointmentId) throws TestException {
		try {

			appointmentService.approveAppointment(testAppointmentId, true);
			return new ResponseEntity<String>("Appointment approved", HttpStatus.OK);
		} catch (Exception exception) {
			throw new TestException(exception.getMessage());
		}
	}
}