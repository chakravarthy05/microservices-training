package com.slokam.healthcare.dpi;

import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.slokam.healthcare.dpi.entity.Patient;
import com.slokam.healthcare.dpi.repo.IPatientRepo;
import com.slokam.healthcare.dpi.service.impl.PatientServiceDBImpl;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class PatientServiceTest {

	@Mock
	private IPatientRepo patientRepo;
	
	@InjectMocks
	private PatientServiceDBImpl patientService;
	
	@Test
	public void testGetPatientMethod() throws ParseException {
		Patient patient = new Patient();
		   String date_string = "01-01-2002";
	       SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	       Date date = formatter.parse(date_string);
	      
		patient.setDob(date);
		when(patientRepo.findById(5L)).thenReturn(Optional.of(patient));
		
		Patient p =patientService.getPatientById(5L);
		
		Assertions.assertEquals(20, p.getAge());
		
	}
}
