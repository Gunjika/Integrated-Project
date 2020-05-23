package com.capgemini.hms.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class DiagnosticCenter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "centerId_generator")
	@SequenceGenerator(name = "centerId_generator", initialValue = 1001, allocationSize = 1)
	private Integer centerId;
	
	@Column(length = 25)
	private String centerName;

	@OneToMany(cascade = CascadeType.ALL, targetEntity = Test.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "center_no", referencedColumnName = "centerId")
	private List<Test> test;
	
	@OneToMany(fetch = FetchType.EAGER,targetEntity = TestAppointment.class, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "center_id", referencedColumnName = "centerId")
	private List<TestAppointment> appointment;

	

	public Integer getCenterId() {
		return centerId;
	}

	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public List<Test> getTest() {
		return test;
	}

	public void setTest(List<Test> test) {
		this.test = test;
	}

	public List<TestAppointment> getAppointment() {
		return appointment;
	}

	public void setAppointment(List<TestAppointment> appointment) {
		this.appointment = appointment;
	}

	
	public DiagnosticCenter(Integer centerId, String centerName, List<Test> test, List<TestAppointment> appointment) {
		super();
		this.centerId = centerId;
		this.centerName = centerName;
		this.test = test;
		this.appointment = appointment;
	}

	public DiagnosticCenter() {

	}

	
	@Override
	public String toString() {
		return "DiagnosticCenter [centerId=" + centerId + ", centerName=" + centerName + ", test=" + test
				+ ", appointment=" + appointment + "]";
	}

}