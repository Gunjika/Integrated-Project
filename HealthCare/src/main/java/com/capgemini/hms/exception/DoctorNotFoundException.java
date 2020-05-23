package com.capgemini.hms.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class DoctorNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public DoctorNotFoundException(String message){
		super(message);
		
	}
	public DoctorNotFoundException(String message,Throwable throwable){
		super(message,throwable);
		
	}
	public DoctorNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DoctorNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DoctorNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
