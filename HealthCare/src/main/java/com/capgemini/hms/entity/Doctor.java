package com.capgemini.hms.entity;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="Doctor")
public class Doctor {
	@Id
	@Column(name="docId")
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="doctor_seq")
	@SequenceGenerator(sequenceName="doctor_seq",initialValue=1010,allocationSize=1,name="doctor_seq")
	private int docId;
	
	@NotEmpty(message="doctor name is mendatory")
	@Column(name="doctorName")
	private String doctorName;

	@NotEmpty(message="doctor Specialization is mendatory")
	@Column(name="doctorSpecialization")
	private String doctorSpecialization;
	
	@NotNull(message="contact must be mendatory")
	@Column(name="contactNo")
	private BigInteger contactNo;
	
	@OneToMany(fetch=FetchType.EAGER,targetEntity = DoctorAppointment.class, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "docId", referencedColumnName = "docId")
	private List<DoctorAppointment> docAppointment;

	public Doctor(int docId, @NotEmpty(message = "doctor name is mendatory") String doctorName,
			@NotEmpty(message = "doctor Specialization is mendatory") String doctorSpecialization,
			@NotNull(message = "contact must be mendatory") BigInteger contactNo, List<DoctorAppointment> docAppointment) {
		super();
		this.docId = docId;
		this.doctorName = doctorName;
		this.doctorSpecialization = doctorSpecialization;
		this.contactNo = contactNo;
		this.docAppointment = docAppointment;
	}

	public int getdocId() {
		return docId;
	}

	public List<DoctorAppointment> getDocAppointment() {
		return docAppointment;
	}

	public void setDocAppointment(List<DoctorAppointment> docAppointment) {
		this.docAppointment = docAppointment;
	}

	public void setdocId(int docId) {
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

	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Doctor [doctorId=" + docId + ", doctorName=" + doctorName + ", doctorSpecialization="
				+ doctorSpecialization + ", contactNo=" + contactNo + ", docAppointment=" + docAppointment + "]";
	}

	public Doctor(int docId, String doctorName, String doctorSpecialization, BigInteger contactNo) {
		super();
		this.docId = docId;
		this.doctorName = doctorName;
		this.doctorSpecialization = doctorSpecialization;
		this.contactNo = contactNo;
	}

}
