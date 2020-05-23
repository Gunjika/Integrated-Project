package com.capgemini.hms.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.hms.dao.DiagnosticCenterDao;
import com.capgemini.hms.dao.TestDao;
import com.capgemini.hms.dao.TestDao1;
import com.capgemini.hms.entity.DiagnosticCenter;
import com.capgemini.hms.entity.Test;
import com.capgemini.hms.exception.TestException;

@Service
@Transactional
public class TestService {

	@Autowired
	TestDao testDao;
	@Autowired
	TestDao1 testDao1;
	@Autowired
	DiagnosticCenterDao diagnosticCenterDao;

	public boolean addCenter(DiagnosticCenter diagnosticCenter) throws TestException {
	
		if (diagnosticCenterDao.save(diagnosticCenter) != null)
			return true;
		else
			throw new TestException("Center not added.");
	}

	public String addTest(Integer centerId, Test test) throws TestException {
		if (testDao.addTest(centerId, test))
			return "Test Added Successfully";
		else
			throw new TestException("Test already exists");
	}

	public void removeTest(Integer testId) {
		testDao1.deleteById(testId);
		}

	public List<DiagnosticCenter> getAllCenter() throws TestException {
		if(diagnosticCenterDao.findAll() != null)
			return diagnosticCenterDao.findAll();
		else
			throw new TestException("Diagnostic Centers not present. ");
	}

	public Optional<DiagnosticCenter> getCenter(Integer centerId) throws TestException {
		if(diagnosticCenterDao.findById(centerId)!=null) {
			return diagnosticCenterDao.findById(centerId);
		}
		else {
			throw new TestException("Diagnostic Center not found.");
		}
	}

}