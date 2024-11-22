package com.slokam.healthcare.dpi.response;

import java.util.Date;

public class AppointmentResponsePojo {

	private Date appointmentDate;
	private String patientFirstName;
	private String patientLastName;
	private Long patientPhone;
	private String doctorFirstName;
	private String doctorLastName;
	
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getPatientFirstName() {
		return patientFirstName;
	}
	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}
	public String getPatientLastName() {
		return patientLastName;
	}
	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}
	public Long getPatientPhone() {
		return patientPhone;
	}
	public void setPatientPhone(Long patientPhone) {
		this.patientPhone = patientPhone;
	}
	public String getDoctorFirstName() {
		return doctorFirstName;
	}
	public void setDoctorFirstName(String doctorFirstName) {
		this.doctorFirstName = doctorFirstName;
	}
	public String getDoctorLastName() {
		return doctorLastName;
	}
	public void setDoctorLastName(String doctorLastName) {
		this.doctorLastName = doctorLastName;
	}
	
	
}
