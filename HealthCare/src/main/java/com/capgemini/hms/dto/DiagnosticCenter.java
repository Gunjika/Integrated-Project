package com.capgemini.hms.dto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class DiagnosticCenter {
	private String centerId;
	private String centerName;
	private String address;
	private BigInteger contactNo;
	private List<Test> tests = new ArrayList<>();
	
	public String getCenterId() {
		return centerId;
	}
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public BigInteger getContactNo() {
		return contactNo;
	}
	public void setContactNo(BigInteger contactNo) {
		this.contactNo = contactNo;
	}
	public List<Test> getTests() {
		return tests;
	}
	public void setTests(List<Test> tests) {
		this.tests = tests;
	}
}
