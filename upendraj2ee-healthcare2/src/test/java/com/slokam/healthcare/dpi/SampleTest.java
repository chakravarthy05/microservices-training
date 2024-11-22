package com.slokam.healthcare.dpi;

import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.slokam.healthcare.dpi.entity.Appointment;
import com.slokam.healthcare.dpi.entity.Disease;
import com.slokam.healthcare.dpi.entity.Treatment;
import com.slokam.healthcare.dpi.repo.ITreatmentRepo;
import com.slokam.healthcare.dpi.service.impl.TreatmentServiceDBImpl;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class SampleTest {

	@InjectMocks
	private TreatmentServiceDBImpl service;
	
	@Mock
	private ITreatmentRepo repo;
	
	@Test
	public void sampleTest() {
		System.out.println("I am in sample Test");
		Treatment treatment = new Treatment();
		treatment.setAppointment(null);
		Appointment appointment =new Appointment();
		appointment.setBookedAt(new Date());
		appointment.setDateAndTimeOfAppointment(new Date());
		treatment.setAppointment(appointment);
		Disease dis = new Disease();
		dis.setId(2);
		dis.setDescription("Description");
		dis.setName("Name");
		treatment.setDisease(dis);
		
		when(repo.findById(3L)).thenReturn( Optional.of(treatment));
		
		Treatment t =  service.getTreatment(3L);
		
		// verify(t.getDisease().getName().equals("Name"));
		
		Assertions.assertEquals("Name", t.getDisease().getName());
	}
}
