package com.capgemini.hms.exception;

public class UserNotFound extends RuntimeException {
	
	public UserNotFound(String msg) {
		super(msg);
	}
}