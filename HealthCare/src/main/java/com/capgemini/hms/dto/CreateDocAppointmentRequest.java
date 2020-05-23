package com.capgemini.hms.dto;

public class CreateDocAppointmentRequest {

	private String dateTime;
	private String userId;
	private String docId;
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
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
