package com.capgemini.hms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.hms.entity.DiagnosticCenter;
import com.capgemini.hms.entity.Test;
import com.capgemini.hms.exception.TestException;
import com.capgemini.hms.service.TestService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TestController {

	@Autowired
	TestService testService;

	@PostMapping("/addCenter")
	public ResponseEntity<String> addCenter(@RequestBody DiagnosticCenter diagnosticCenter) throws TestException {
		try {
			testService.addCenter(diagnosticCenter);
			return new ResponseEntity<String>("Center added successfully", HttpStatus.OK);
		} catch (Exception exception) {
			throw new TestException("Center ID already exists");
		}

	}

	@PostMapping("/addTest/{centerId}")
	public ResponseEntity<String> addTest(@PathVariable Integer centerId, @RequestBody Test test,
			BindingResult bindingResult) throws TestException {
		String err = "";
		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			for (FieldError error : errors)
				err += error.getDefaultMessage() + "<br/>";
			throw new TestException(err);
		}
		try {
			testService.addTest(centerId, test);
			return new ResponseEntity<String>("Test added in center successfully", HttpStatus.OK);

		} catch (DataIntegrityViolationException ex) {
			throw new TestException("Test ID already exists");
		}
	}

//	@DeleteMapping("/removeTest/{testId}")
//	public ResponseEntity<String> removeTest(@PathVariable Integer testId) throws TestException {
//		try {
//			testService.removeTest(testId);
//			return new ResponseEntity<String>("Test deleted successfully", HttpStatus.OK);
//		} catch (Exception exception) {
//			throw new TestException(exception.getMessage());
//		}
//
//	}
	@DeleteMapping(value = "/removeTest/{testId}")
	public ResponseEntity<String> removeTest(@PathVariable Integer testId)
			throws TestException {
		try {
			testService.removeTest(testId);
			return new ResponseEntity<String>("Test deleted successfully", HttpStatus.OK);

		} catch (Exception exception) {
			throw new TestException(exception.getMessage());
		}
	}

	@GetMapping("/centers")
	public ResponseEntity<List<DiagnosticCenter>> getAllCenter() throws TestException {
		try {
			List<DiagnosticCenter> diagnosticCenterList = testService.getAllCenter();
			return new ResponseEntity<List<DiagnosticCenter>>(diagnosticCenterList, HttpStatus.OK);
		} catch (Exception exception) {
			throw new TestException(exception.getMessage());
		}
	}

	@GetMapping("/center/{centerId}")
	public Optional<DiagnosticCenter> getCenter(@PathVariable Integer centerId) throws TestException {
		try {
			return testService.getCenter(centerId);
		} catch (Exception exception) {
			throw new TestException(exception.getMessage());
		}
	}
}