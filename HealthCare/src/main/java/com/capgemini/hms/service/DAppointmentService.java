package com.capgemini.hms.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import com.capgemini.hms.entity.DoctorAppointment;

public interface DAppointmentService {
boolean approveAppointment(DoctorAppointment app);
	
	String makeAppointment(String userId, String docId, LocalDateTime dateTime);
	
	DoctorAppointment findById(BigInteger appointmentId);
	
	List<DoctorAppointment> findByDoctor(String docId);
	
	List<DoctorAppointment> findByUser(String userId);
}
