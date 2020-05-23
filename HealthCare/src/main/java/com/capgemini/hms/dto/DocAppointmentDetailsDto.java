package com.capgemini.hms.dto;

public class DocAppointmentDetailsDto {

	private String appointmentId;
	private String dateTime;
	private boolean approved;
	private String userId;
	private String docId;
	
	public String getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(String appointmentId) {
		this.appointmentId = appointmentId;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
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
	public String getdocId() {
		return docId;
	}
	public void setdocId(String docId) {
		this.docId = docId;
	}
	
}
