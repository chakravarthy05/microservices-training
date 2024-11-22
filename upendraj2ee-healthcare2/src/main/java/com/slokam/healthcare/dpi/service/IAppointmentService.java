package com.slokam.healthcare.dpi.service;

import java.util.Date;
import java.util.List;

import com.slokam.healthcare.dpi.response.AppointmentResponsePojo;

public interface IAppointmentService {

	public List<AppointmentResponsePojo> getAppointmentsByDate(Date startDate,Date endDate);
}
