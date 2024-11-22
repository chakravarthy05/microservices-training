package com.slokam.healthcare.dpi.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Appointment {
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "fkpid")
	private Patient patient;
	@ManyToOne
	@JoinColumn(name="fkdid")
	private Doctor doctor;
	private Date  dateAndTimeOfAppointment;
	private Date  bookedAt;
	private String location;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Date getDateAndTimeOfAppointment() {
		return dateAndTimeOfAppointment;
	}
	public void setDateAndTimeOfAppointment(Date dateAndTimeOfAppointment) {
		this.dateAndTimeOfAppointment = dateAndTimeOfAppointment;
	}
	public Date getBookedAt() {
		return bookedAt;
	}
	public void setBookedAt(Date bookedAt) {
		this.bookedAt = bookedAt;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
	
}
