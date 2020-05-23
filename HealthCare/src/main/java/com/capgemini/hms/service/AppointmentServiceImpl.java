package com.capgemini.hms.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.hms.dao.IAppointmentDao;
import com.capgemini.hms.entity.Appointment;
import com.capgemini.hms.exception.AppointmentNotFound;
import com.capgemini.hms.exception.CenterNotFound;
import com.capgemini.hms.exception.UserNotFound;

@Service
@Transactional
public class AppointmentServiceImpl implements IAppointmentService {
	
	@Autowired
	private IAppointmentDao dao;

	@Override
	public String makeAppointment(String userId, String centerId, String testId, LocalDateTime dateTime) {
		Appointment appointment = new Appointment();
		appointment.setUserId(userId);
		appointment.setCenterId(centerId);
		appointment.setTestId(testId);
		appointment.setDateTime(dateTime);
		Appointment app = dao.save(appointment);
		return app.getAppointmentId().toString();
	}

	@Override
	public Appointment findById(BigInteger appointmentId) {
		Optional<Appointment> optional = dao.findById(appointmentId);
		if(optional.isPresent())
		{
			Appointment appoint = optional.get();
			return appoint;
		}
		throw new AppointmentNotFound("Appointment not found");
	}

	@Override
	public List<Appointment> findByCenter(String centerId) {
		List<Appointment> list = dao.findUnApprovedAppointments(centerId);
		if(list.isEmpty())
			throw new CenterNotFound("No Appointments found with this center-id:" + centerId);
		return list;
	}

	@Override
	public List<Appointment> findByUser(String userId) {
		List<Appointment> list = dao.findByUserId(userId);
		if(list.isEmpty())
			throw new UserNotFound("No Appointments found with this user-id: "+userId);
		return list;
	}

	@Override
	public boolean approveAppointment(Appointment app) {
		app.setStatus(true);
		dao.save(app);
		return true;
	}

}
