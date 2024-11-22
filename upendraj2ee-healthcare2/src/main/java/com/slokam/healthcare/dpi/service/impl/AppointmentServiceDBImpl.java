package com.slokam.healthcare.dpi.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slokam.healthcare.dpi.entity.Appointment;
import com.slokam.healthcare.dpi.entity.Doctor;
import com.slokam.healthcare.dpi.entity.Patient;
import com.slokam.healthcare.dpi.repo.IAppointmentRepo;
import com.slokam.healthcare.dpi.request.AppointmentRequestPojo;
import com.slokam.healthcare.dpi.response.AppointmentResponsePojo;
import com.slokam.healthcare.dpi.service.IAppointmentService;

@Service
public class AppointmentServiceDBImpl implements IAppointmentService {
	
	@Autowired
	private IAppointmentRepo appointmentRepo;
	
	
	public void saveAppointment(AppointmentRequestPojo appReqPojo) {
		
		Appointment appointment = new Appointment();
		appointment.setDateAndTimeOfAppointment(appReqPojo.getAppointmentdate());
		appointment.setBookedAt(appReqPojo.getBookedat());
		appointment.setLocation(appReqPojo.getLocation());
		Patient p = new Patient();
		p.setId(appReqPojo.getPatientid());
		appointment.setPatient(p);
		Doctor doc = new Doctor();
		doc.setId(appReqPojo.getDoctorid());
		appointment.setDoctor(doc);
		
		appointmentRepo.save(appointment);
		
	}
	@Override
	public List<AppointmentResponsePojo>  getAppointmentsByDate(Date startDate,Date endDate) {
		
		List<Object[]> data = appointmentRepo.getPatientDoctorDetailsByAppointment(startDate, endDate);
		List<AppointmentResponsePojo>  list = data.stream().map( (arr)->{
			AppointmentResponsePojo resp = new AppointmentResponsePojo();
			resp.setAppointmentDate( (Date)arr[0]);
			resp.setPatientFirstName( (String)arr[1]);
			resp.setPatientLastName( (String)arr[2]);
			resp.setPatientPhone((Long)arr[3]);
			resp.setDoctorFirstName((String)arr[4]);
			resp.setDoctorLastName((String)arr[5]);
			return resp;
		}).collect(Collectors.toList());
		
		return list;
	}
}
