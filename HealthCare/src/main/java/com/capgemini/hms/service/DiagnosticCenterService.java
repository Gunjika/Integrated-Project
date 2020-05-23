 package com.capgemini.hms.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.hms.dao.DiagnosticCenterDao;
import com.capgemini.hms.entity.DiagnosticCenter;

@Transactional
@Service
public class DiagnosticCenterService {
	
	
	@Autowired
	private DiagnosticCenterDao diagnosticCenterDao;
	
	
	public boolean addcenter(DiagnosticCenter diagnosticcenter)
	{
		return diagnosticCenterDao.save(diagnosticcenter) != null;
	}
	
	
	public List<DiagnosticCenter> listallcenter()
	{
		return diagnosticCenterDao.findAll();
	}
	
	public Optional<DiagnosticCenter> listcenterbyId(Integer centerId)
	{
		return diagnosticCenterDao.findById(centerId);
	}
	
	public void deletecenter( Integer centerId)
	{
		diagnosticCenterDao.deleteById(centerId);
	}
	
	
	public boolean updatecenter(DiagnosticCenter diagnosticcenter,Integer centerId)
	{
		diagnosticcenter.setCenterName(diagnosticcenter.getCenterName());
		return diagnosticCenterDao.save(diagnosticcenter) != null;
	}
	
	
}
