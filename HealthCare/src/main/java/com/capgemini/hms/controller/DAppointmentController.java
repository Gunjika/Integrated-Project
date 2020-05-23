package com.capgemini.hms.controller;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.capgemini.hms.dto.CreateDocAppointmentRequest;
import com.capgemini.hms.dto.DocAppointmentDetailsDto;
import com.capgemini.hms.dto.Doctor;
import com.capgemini.hms.entity.DoctorAppointment;
import com.capgemini.hms.exception.AppointmentNotFound;
import com.capgemini.hms.exception.CenterNotFound;
import com.capgemini.hms.exception.UserNotFound;
import com.capgemini.hms.service.DAppointmentService;
import com.capgemini.hms.util.Util;

@RestController
@RequestMapping("/Docappointments")
public class DAppointmentController {

	private static final Logger log = LoggerFactory.getLogger(DAppointmentController.class); 
	
	@Autowired
	private DAppointmentService service;
	
	@Value("${centertestservice.baseurl}")
	private String centerTestServiceBaseUrl;
	
	@Autowired
	private RestTemplate restTemplate; 
	
	/**
	 * Make Appointment
	 * @param dto
	 * @return
	 */
	@PostMapping("/add")
	public ResponseEntity<String> addAppointment(@RequestBody CreateDocAppointmentRequest dto) {
		DoctorAppointment appointment = convertDto(dto);
		String msg = service.makeAppointment(appointment.getUserId(),appointment.getDocId(),appointment.getDateTime()); 
		ResponseEntity<String> response = new ResponseEntity<String>(msg, HttpStatus.OK);
		return response;
	}
	
	/**
	 * convert from appointment: dto -> entity
	 * @param dto
	 * @return
	 */
	public DoctorAppointment convertDto(CreateDocAppointmentRequest dto) {
		DoctorAppointment app = new DoctorAppointment();
		app.setDocId(dto.getdocId());
		app.setUserId(dto.getUserId());
		LocalDateTime dateTime = Util.convertStringToDate(dto.getDateTime());
		app.setDateTime(dateTime);
		return app;
	}
	
	/**
	 * convert from appointment: entity -> detailsdto
	 * @param app
	 * @return
	 */
	public DocAppointmentDetailsDto convertAppointmentDetails(DoctorAppointment app) {
		DocAppointmentDetailsDto detailsDto = new DocAppointmentDetailsDto();
		String id = app.getDocAppointmentId().toString();
		detailsDto.setAppointmentId(id);
		detailsDto.setdocId(app.getDocId());
		detailsDto.setDateTime(app.getDateTime().toString());
		detailsDto.setApproved(app.isApproved());
		detailsDto.setUserId(app.getUserId());
		return detailsDto;
	}
	
	/**
	 * convert form appointments: entity list -> detailsdto list
	 * @param appointments
	 * @return
	 */
	public List<DocAppointmentDetailsDto> convertAppointmentDetails(List<DoctorAppointment> appointments) {
		List<DocAppointmentDetailsDto> listDetailsDto = new ArrayList<DocAppointmentDetailsDto>();
		for (DoctorAppointment appointment : appointments) {
			DocAppointmentDetailsDto detailsDto = convertAppointmentDetails(appointment);
			listDetailsDto.add(detailsDto);
		}
		return listDetailsDto ;
	}
	
	@GetMapping("/getByCenter/{id}")
	public ResponseEntity<List<DoctorAppointment>> viewDoctorAppointments(@PathVariable("id") String docId) {
		List<DoctorAppointment> list = service.findByDoctor(docId);
		ResponseEntity<List<DoctorAppointment>> response = new ResponseEntity<List<DoctorAppointment>>(list,HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/getByUser/{id}")
	public ResponseEntity<List<DoctorAppointment>> viewUserAppointments(@PathVariable("id") String userId) {
		List<DoctorAppointment> list = service.findByUser(userId);
		ResponseEntity<List<DoctorAppointment>> response = new ResponseEntity<List<DoctorAppointment>>(list,HttpStatus.OK);
		return response;
	}
	
	/**
	 * Approve Appointment
	 * @param appointmentId
	 * @return
	 */
	@PutMapping("/approve/{id}")
	public ResponseEntity<Boolean> approveAppointment(@PathVariable("id") String appointmentId) {
		BigInteger id = new BigInteger(appointmentId);
		DoctorAppointment appoint = service.findById(id);
		boolean status = service.approveAppointment(appoint);
		ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(status, HttpStatus.OK);
		return response;
	}
	
	/**
	 * Fetching all centers
	 * @return
	 */
	@GetMapping("/centers")
	public ResponseEntity<Doctor[]> fetchAllDoctor() {
		String url = centerTestServiceBaseUrl;
		ResponseEntity<Doctor[]> response = restTemplate.getForEntity(url, Doctor[].class);
		return response;
	}
	
	
	@ExceptionHandler(AppointmentNotFound.class)
	public ResponseEntity<String> handleException1(AppointmentNotFound exception){
		log.error("Appointment Exception",exception);
		 String msg = exception.getMessage();
	     ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
	     return response;
	}
	
	@ExceptionHandler(CenterNotFound.class)
	public ResponseEntity<String> handleException2(CenterNotFound exception){
		log.error("Appointment Exception",exception);
		 String msg = exception.getMessage();
	     ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
	     return response;
	}
	
	@ExceptionHandler(UserNotFound.class)
	public ResponseEntity<String> handleException3(UserNotFound exception){
		log.error("Appointment Exception",exception);
		 String msg = exception.getMessage();
	     ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
	     return response;
	}
	
	 @ExceptionHandler(Throwable.class)
	    public ResponseEntity<String> handleAll(Throwable ex) {
	        log.error("exception caught", ex);
	        String msg = ex.getMessage();
	        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
	        return response;
	    }
}
