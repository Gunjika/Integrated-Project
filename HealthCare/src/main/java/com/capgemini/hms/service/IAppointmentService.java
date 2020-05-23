package com.capgemini.hms.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import com.capgemini.hms.entity.Appointment;

public interface IAppointmentService {
boolean approveAppointment(Appointment app);
	
	String makeAppointment(String userId, String centerId, String testId, LocalDateTime dateTime);
	
	Appointment findById(BigInteger appointmentId);
	
	List<Appointment> findByCenter(String centerId);
	
	List<Appointment> findByUser(String userId);

}
