package com.slokam.healthcare.dpi.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.slokam.healthcare.dpi.entity.Patient;
import com.slokam.healthcare.dpi.repo.IPatientRepo;
import com.slokam.healthcare.dpi.service.IPatientService;

@Service
public class PatientServiceDBImpl implements IPatientService{

	private Logger LOGGER = LoggerFactory.getLogger(PatientServiceDBImpl.class);
	@Autowired
	private IPatientRepo patientRepo;
	
	@Autowired
	private MailSender mailSender;
	
	
	@Override
	public void savePatient(Patient patient) {
		 LOGGER.info("Entered into savePatient");
		 patientRepo.save(patient);
		 LOGGER.info("Exit from savePatient");
	}
	
	@Override
	public Patient getPatientById(Long id) {
		Optional<Patient> opt = patientRepo.findById(id);
		if(opt.isPresent()) {
			Patient p = opt.get();
			LocalDate localDate = p.getDob().toInstant()
		      .atZone(ZoneId.systemDefault())
		      .toLocalDate();
			long age = ChronoUnit.YEARS.between(localDate, LocalDate.now());
			p.setAge( (int) age);
			return p;
		}
		return null;
	}
	
	public void sendMail() {
		
	    SimpleMailMessage message = new SimpleMailMessage();
	    message.setFrom("upendra.j2ee@gmail.com");
	    message.setText("Hello How Are You ?");
	    message.setSubject("Test Subject");
	    message.setTo("slokamtechprivatelimited@gmail.com");
		mailSender.send(message);
		
	}
}
