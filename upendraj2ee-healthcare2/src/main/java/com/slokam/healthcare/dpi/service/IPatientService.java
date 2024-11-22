package com.slokam.healthcare.dpi.service;

import com.slokam.healthcare.dpi.entity.Patient;

public interface IPatientService {

	public void savePatient(Patient patient);
	
	public Patient getPatientById(Long id);
	
	public void sendMail();
}
