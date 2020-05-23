package com.capgemini.hms.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.hms.dao.DAppointmentDao;
import com.capgemini.hms.entity.DoctorAppointment;
import com.capgemini.hms.exception.AppointmentNotFound;
import com.capgemini.hms.exception.CenterNotFound;
import com.capgemini.hms.exception.UserNotFound;

@Service
@Transactional
public class DAppointmentServiceImpl implements DAppointmentService {
	
	@Autowired
	private DAppointmentDao dao;

	@Override
	public String makeAppointment(String userId, String docId, LocalDateTime dateTime) {
		DoctorAppointment appointment = new DoctorAppointment();
		appointment.setUserId(userId);
		appointment.setDocId(docId);
		appointment.setDateTime(dateTime);
		DoctorAppointment app = dao.save(appointment);
		return app.getDocAppointmentId().toString();
	}

	@Override
	public DoctorAppointment findById(BigInteger appointmentId) {
		Optional<DoctorAppointment> optional = dao.findById(appointmentId);
		if(optional.isPresent())
		{
			DoctorAppointment appoint = optional.get();
			return appoint;
		}
		throw new AppointmentNotFound("Appointment not found");
	}

	@Override
	public List<DoctorAppointment> findByDoctor(String docId) {
		List<DoctorAppointment> list = dao.findUnApprovedAppointments(docId);
		if(list.isEmpty())
			throw new CenterNotFound("No Appointments found with this doctor-id:" + docId);
		return list;
	}

	@Override
	public List<DoctorAppointment> findByUser(String userId) {
		List<DoctorAppointment> list = dao.findByUserId(userId);
		if(list.isEmpty())
			throw new UserNotFound("No Appointments found with this user-id: "+userId);
		return list;
	}

	@Override
	public boolean approveAppointment(DoctorAppointment app) {
		app.setApproved(true);
		dao.save(app);
		return true;
	}

}