package com.capgemini.hms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.hms.entity.Doctor;

public interface DoctorDao extends JpaRepository<Doctor, Integer>{

}
