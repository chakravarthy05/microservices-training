package com.slokam.healthcare.dpi.request;

import java.util.List;

public class TreatmentRequestPojo {

	 private Integer disease;
	 private Long appointment;
	 private List<TreatmentItemRequestPojo>  items;
	 
	public Integer getDisease() {
		return disease;
	}
	public void setDisease(Integer disease) {
		this.disease = disease;
	}
	public Long getAppointment() {
		return appointment;
	}
	public void setAppointment(Long appointment) {
		this.appointment = appointment;
	}
	public List<TreatmentItemRequestPojo> getItems() {
		return items;
	}
	public void setItems(List<TreatmentItemRequestPojo> items) {
		this.items = items;
	}
	 
	 
}
