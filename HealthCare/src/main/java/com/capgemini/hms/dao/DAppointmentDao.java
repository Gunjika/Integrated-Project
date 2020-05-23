package com.capgemini.hms.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.hms.entity.DoctorAppointment;

public interface DAppointmentDao extends JpaRepository<DoctorAppointment, BigInteger> {

	@Query("FROM DoctorAppointment WHERE docId=:docId AND approved=false")
	public List<DoctorAppointment> findUnApprovedAppointments(@Param("docId")String docId); 
	
	public List<DoctorAppointment> findByUserId(String userId); 
}
