package com.capgemini.hms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capgemini.hms.exception.ErrorInfo;
import com.capgemini.hms.exception.UserException;

@RestControllerAdvice
public class UserAdvice {
	
		@ExceptionHandler(value = {UserException.class})
		@ResponseStatus(code=HttpStatus.BAD_REQUEST)
		public ErrorInfo handleException1(Exception ex) {
			return new ErrorInfo(ex.getMessage());
		}

}