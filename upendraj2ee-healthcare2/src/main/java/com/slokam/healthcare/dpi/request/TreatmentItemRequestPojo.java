package com.slokam.healthcare.dpi.request;

public class TreatmentItemRequestPojo {

	private Integer symptom;
	private Integer drug;
	private Integer days;
	private String description;
	public Integer getSymptom() {
		return symptom;
	}
	public void setSymptom(Integer symptom) {
		this.symptom = symptom;
	}
	public Integer getDrug() {
		return drug;
	}
	public void setDrug(Integer drug) {
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
