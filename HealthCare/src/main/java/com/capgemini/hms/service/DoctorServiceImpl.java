package com.capgemini.hms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.hms.dao.DoctorDao;
import com.capgemini.hms.entity.Doctor;
import com.capgemini.hms.exception.DoctorNotFoundException;
@Service
@Transactional
public class DoctorServiceImpl implements DoctorService{
	@Autowired
	private DoctorDao doctorDao;

	@Override
	public boolean addDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		Doctor doctorResult = doctorDao.save(doctor);
		if (doctorResult != null) {
			return true;
		} else
			return false;
	}

	@Override
	public List<Doctor> showallDoctor() {
		// TODO Auto-generated method stub
		return doctorDao.findAll();
	}
	

	@Override
	public void deleteDoctor(int doctorId) {
		doctorDao.deleteById(doctorId);
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean updateDoctor(Doctor doctor, int doctorId) {
		// TODO Auto-generated method stub
		doctor.setDoctorName(doctor.getDoctorName());
		return doctorDao.save(doctor) != null;
	}

	@Override
	public Doctor getDoctorById(int doctorId) {
		// TODO Auto-generated method stub
		Optional<Doctor> doctorDb=this.doctorDao.findById(doctorId);
		if(doctorDb.isPresent()) {
			return doctorDb.get();

		}
		else
		{
			throw new DoctorNotFoundException("Record not found with id : "+doctorId);
		}
	}

//	@Override
//	public Doctor createDoctor(Doctor doctor) {
//		// TODO Auto-generated method stub
//		return doctorDao.save(doctor);
//	}
//
//	@Override
//	public Doctor updateDoctor(Doctor doctor) {
//		// TODO Auto-generated method stub
//		Optional<Doctor> doctorDb=this.doctorDao.findById(doctor.getDoctorId());
//		if(doctorDb.isPresent()) {
//			Doctor doctorUpdate=doctorDb.get();
//			doctorUpdate.setDoctorId(doctor.getDoctorId());
//			doctorUpdate.setDoctorName(doctor.getDoctorName());
//			doctorUpdate.setDoctorSpecialization(doctor.getDoctorSpecialization());
//			doctorUpdate.setContactNo(doctor.getContactNo());
//			doctorDao.save(doctorUpdate);
//			return doctorUpdate;
//		}
//		else {
//			throw new DoctorNotFoundException("Record not found with id : "+doctor.getDoctorId());
//		}
//	}
//
//	@Override
//	public List<Doctor> getAllDoctor() {
//		// TODO Auto-generated method stub
//		return this.doctorDao.findAll();
//	}
//
//	@Override
//	public Doctor getDoctorById(long doctorId) {
//		// TODO Auto-generated method stub
//		Optional<Doctor> doctorDb=this.doctorDao.findById(doctorId);
//		if(doctorDb.isPresent()) {
//			return doctorDb.get();
//
//		}
//		else
//		{
//			throw new DoctorNotFoundException("Record not found with id : "+doctorId);
//		}
//	}
//
//	@Override
//	public void deleteDoctor(long doctorId) {
//		// TODO Auto-generated method stub
//		
//		Optional<Doctor> doctorDb=this.doctorDao.findById(doctorId);
//		if(doctorDb.isPresent()) {
//			this.doctorDao.delete(doctorDb.get());
//		}
//		else
//		{
//			throw new DoctorNotFoundException("Record not found with id : "+doctorId);
//		}
//	}

}
