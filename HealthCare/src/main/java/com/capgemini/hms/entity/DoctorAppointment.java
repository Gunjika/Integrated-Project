package com.capgemini.hms.entity;

import java.math.BigInteger;
import java.time.LocalDateTime;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "doc_appoint_info")
public class DoctorAppointment {

	@Id
	@GeneratedValue
	private BigInteger docAppointmentId;
	private LocalDateTime dateTime;
	private boolean approved;
	private String userId;
	private String docId;
	
	public BigInteger getDocAppointmentId() {
		return docAppointmentId;
	}
	public void setDocAppointmentId(BigInteger docAppointmentId) {
		this.docAppointmentId = docAppointmentId;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	
	@Override
	public boolean equals(Object object) {
		if(this == object)
			return true;
		if(object == null || (object instanceof DoctorAppointment))
			return false;
		DoctorAppointment appointment = (DoctorAppointment) object; 
		return this.docAppointmentId.equals(appointment.docAppointmentId);
	}
	
	@Override
	public int hashCode() {
		return docAppointmentId.hashCode();
	}
	
}
