package com.slokam.healthcare.dpi.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.slokam.healthcare.dpi.entity.Appointment;
@Repository
public interface IAppointmentRepo extends JpaRepository<Appointment, Long> {

	@Query("select a.dateAndTimeOfAppointment, p.firstName ,p.lastName,p.phone,d.firstName,d.lastName from Appointment a join a.patient p join a.doctor d where a.dateAndTimeOfAppointment >?1 and a.dateAndTimeOfAppointment < ?2")
	public List<Object[]> getPatientDoctorDetailsByAppointment(Date apointmentStartDate , Date apointmentEndDate); 
	
}
