package com.capgemini.hms.dto;

import java.math.BigInteger;

public class Doctor {
	private String docId;
	private String doctorName;
	private String doctorSpecialization;
	private BigInteger contactNo;
	public String getdocId() {
		return docId;
	}
	public void setdocId(String docId) {
		this.docId = docId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getDoctorSpecialization() {
		return doctorSpecialization;
	}
	public void setDoctorSpecialization(String doctorSpecialization) {
		this.doctorSpecialization = doctorSpecialization;
	}
	public BigInteger getContactNo() {
		return contactNo;
	}
	public void setContactNo(BigInteger contactNo) {
		this.contactNo = contactNo;
	}

	
}