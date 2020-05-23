package com.capgemini.hms.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.hms.dto.TestDto;
import com.capgemini.hms.entity.DiagnosticCenter;
import com.capgemini.hms.entity.Test;

@Repository
public class TestDao {
	@PersistenceContext
	EntityManager em;

	public boolean addCenter(TestDto testDto) {
		em.persist(testDto.getCenter());
		return true;
	}

	public List<DiagnosticCenter> getAllCenter() {
		String str = "SELECT diagnosticCenter FROM DiagnosticCenter diagnosticCenter";
		TypedQuery<DiagnosticCenter> query = em.createQuery(str, DiagnosticCenter.class);
		return query.getResultList();
	}

	public DiagnosticCenter getCenter(Integer centerid) {
		String str = "SELECT diagnosticCenter FROM DiagnosticCenter diagnosticCenter WHERE diagnosticCenter.centerId="
				+ centerid;
		TypedQuery<DiagnosticCenter> query = em.createQuery(str, DiagnosticCenter.class);
		return query.getSingleResult();
	}

	public boolean addTest(Integer centerId, Test test) {
		DiagnosticCenter diagnosticCenter = em.find(DiagnosticCenter.class, centerId);
		String testName = test.getTestName();
		List<Test> testList = diagnosticCenter.getTest();

		for (Test t : testList) {
			if (t.getTestName().equalsIgnoreCase(testName)) {
				return false;
			}
		}
		test.setTestName(testName);
		diagnosticCenter.getTest().add(test);
		em.persist(diagnosticCenter);
		return true;

	}

	public boolean removeTest(Integer testId) {
		Test test = em.find(Test.class, testId);
		if (test != null) {
			em.remove(test);
			return true;
		} else {
			return false;
		}
	}

}
