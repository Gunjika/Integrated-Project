package com.capgemini.hms.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.capgemini.hms.dao.AppointmentDao;
import com.capgemini.hms.entity.TestAppointment;
import com.capgemini.hms.exception.TestException;

@Transactional
@Service
public class AppointmentService {

	@Autowired
	AppointmentDao appointmentDao;

	@SuppressWarnings("null")
	public void approveAppointment(Integer testAppointmentId, boolean approved) throws TestException {

		Optional<TestAppointment> optionalTest = appointmentDao.findById(testAppointmentId);
		if (optionalTest.isPresent()) {
			try {

				appointmentDao.updateStatus(approved, testAppointmentId);
			} catch (DataIntegrityViolationException exception) {
				throw new TestException(exception.getMessage());
			}
		}

	}
}
