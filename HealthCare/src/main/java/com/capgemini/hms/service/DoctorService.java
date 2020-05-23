package com.capgemini.hms.service;

import java.util.List;

import javax.validation.Valid;

import com.capgemini.hms.entity.Doctor;

public interface DoctorService {
//	Doctor createDoctor(Doctor doctor);
//	Doctor updateDoctor(Doctor doctor);
//	List<Doctor> getAllDoctor();
//	Doctor getDoctorById(long doctorId);
//	void deleteDoctor(long id);
	
	boolean addDoctor(Doctor doctor);

	List<Doctor> showallDoctor();
	
	void deleteDoctor(int doctorId);
	
	boolean updateDoctor(Doctor doctor,int doctorId);
	
	Doctor getDoctorById(int doctorId);
	

}
