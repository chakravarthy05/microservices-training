package com.slokam.healthcare.dpi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Treatment_Item {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name="fktid")
	private Treatment treatment;
	@ManyToOne()
	@JoinColumn(name="fksyid")
	private Symptom symptom;
	@ManyToOne
	@JoinColumn(name="fkdrid")
	private Drug drug;
	private Integer days;
	private String description;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Treatment getTreatment() {
		return treatment;
	}
	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}
	public Symptom getSymptom() {
		return symptom;
	}
	public void setSymptom(Symptom symptom) {
		this.symptom = symptom;
	}
	public Drug getDrug() {
		return drug;
	}
	public void setDrug(Drug drug) {
		this.drug = drug;
	}
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
