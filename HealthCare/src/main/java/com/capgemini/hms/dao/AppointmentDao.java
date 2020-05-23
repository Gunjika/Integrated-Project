package com.capgemini.hms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.hms.entity.TestAppointment;

public interface AppointmentDao extends JpaRepository<TestAppointment, Integer>{
	@Modifying(clearAutomatically = true)
	@Query("UPDATE TestAppointment testAppointment SET testAppointment.approved= :approved WHERE "
			+ "testAppointment.testAppointmentId= :testAppointmentId")
	 int updateStatus(@Param("approved") boolean approved, @Param("testAppointmentId") Integer testAppointmentId);

}
