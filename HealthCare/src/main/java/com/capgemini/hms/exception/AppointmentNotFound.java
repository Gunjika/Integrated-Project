package com.capgemini.hms.exception;

public class AppointmentNotFound extends RuntimeException {

	public AppointmentNotFound(String msg) {
		super(msg);
	}
}