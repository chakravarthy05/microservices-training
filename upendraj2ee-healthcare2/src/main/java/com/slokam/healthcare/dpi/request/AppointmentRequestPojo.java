package com.slokam.healthcare.dpi.request;

import java.util.Date;

public class AppointmentRequestPojo {
  private Long patientid;
  private Integer doctorid;
  private Date appointmentdate;
  private String location;
  private Date bookedat;
  
public Long getPatientid() {
	return patientid;
}
public void setPatientid(Long patientid) {
	this.patientid = patientid;
}

public Integer getDoctorid() {
	return doctorid;
}
public void setDoctorid(Integer doctorid) {
	this.doctorid = doctorid;
}
public Date getAppointmentdate() {
	return appointmentdate;
}
public void setAppointmentdate(Date appointmentdate) {
	this.appointmentdate = appointmentdate;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
public Date getBookedat() {
	return bookedat;
}
public void setBookedat(Date bookedat) {
	this.bookedat = bookedat;
}
  
  
}
