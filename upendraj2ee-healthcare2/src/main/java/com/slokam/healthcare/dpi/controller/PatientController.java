package com.slokam.healthcare.dpi.controller;

import java.io.File;
import java.io.IOException;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.slokam.healthcare.dpi.entity.Patient;
import com.slokam.healthcare.dpi.service.IPatientService;

@RestController
@RequestMapping("patient")
//@Profile("qa")
//@ConditionalOnProperty(value = "spring.datasource.username",
//havingValue = "root",
//matchIfMissing = true	)
public class PatientController {

	// Hii how are you by Developer 2
	private Logger LOGGER = LoggerFactory.getLogger(PatientController.class);
	
	@Value("${file.upload.folder}")
	private String uploadFolderPath ;
	
	@Autowired
	private IPatientService patientService;

	@PostMapping("/image") 
	public ResponseEntity savePatientImage( @RequestParam("image") MultipartFile fileData,
			@RequestParam("id") Long pid) 
	throws IOException {
		
		LOGGER.info("Entered into savePatientImage");
		
		LOGGER.info(fileData.getOriginalFilename());
		LOGGER.info(fileData.getSize()+"");
	
		fileData.transferTo(new File(uploadFolderPath+fileData.getOriginalFilename()));
		Patient patient = patientService.getPatientById(pid);
		patient.setImageName(fileData.getOriginalFilename());
		patientService.savePatient(patient);
		
		patientService.sendMail();
		LOGGER.info("Exit from savePatientImage");
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	@PostMapping()
	public ResponseEntity<Patient> savePatient(@Valid @RequestBody Patient patient){
	 	 patientService.savePatient(patient);
		return new ResponseEntity<Patient>(patient,HttpStatus.OK);
	}
	
}
