package com.slokam.healthcare.dpi.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
@Entity
public class Treatment {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name="fkdsid")
	private Disease disease;
	@OneToOne
	@JoinColumn(name="fkaid")
    private Appointment appointment;
    
	
	public Appointment getAppointment() {
		return appointment;
	}
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Disease getDisease() {
		return disease;
	}
	public void setDisease(Disease disease) {
		this.disease = disease;
	}
	
	
	
	
}
