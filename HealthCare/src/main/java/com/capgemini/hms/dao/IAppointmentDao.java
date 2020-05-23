package com.capgemini.hms.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.hms.entity.Appointment;

public interface IAppointmentDao extends JpaRepository<Appointment, BigInteger> {

	@Query("FROM Appointment WHERE centerId=:centerId AND status=false")
	public List<Appointment> findUnApprovedAppointments(@Param("centerId")String centerId); 
	
	public List<Appointment> findByUserId(String userId); 
}
