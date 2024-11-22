package com.slokam.healthcare.dpi.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.slokam.healthcare.dpi.entity.Appointment;
import com.slokam.healthcare.dpi.entity.Disease;
import com.slokam.healthcare.dpi.entity.Drug;
import com.slokam.healthcare.dpi.entity.Symptom;
import com.slokam.healthcare.dpi.entity.Treatment;
import com.slokam.healthcare.dpi.entity.Treatment_Item;
import com.slokam.healthcare.dpi.repo.ITreatmentRepo;
import com.slokam.healthcare.dpi.repo.ITreatment_ItemRepo;
import com.slokam.healthcare.dpi.request.TreatmentItemRequestPojo;
import com.slokam.healthcare.dpi.request.TreatmentRequestPojo;
import com.slokam.healthcare.dpi.service.ITreatmentService;

@Service()
@Transactional
public class TreatmentServiceDBImpl implements ITreatmentService{

	@Autowired
	private ITreatmentRepo treamentRepo;
	@Autowired
	private ITreatment_ItemRepo treatment_ItemRepo;
	
	@Override
	@Transactional( readOnly = true ,isolation = Isolation.SERIALIZABLE , propagation = Propagation.MANDATORY )
	public void saveTreatment(TreatmentRequestPojo treatmentReq ) {
		
		  Treatment treatment = new Treatment();
		  
		  Appointment appointment = new Appointment();
		  appointment.setId(treatmentReq.getAppointment());
		  treatment.setAppointment(appointment);
		  
		  Disease disease = new Disease();
		  disease.setId(treatmentReq.getDisease());
		  treatment.setDisease(disease);
		  
		  treamentRepo.save(treatment);
		  
		  List<TreatmentItemRequestPojo> itemReqList = treatmentReq.getItems();
		  
		  
		  
		  
		List<Treatment_Item>  tretments = itemReqList.stream().map((tip)->{
			  Treatment_Item item = new Treatment_Item();
			  item.setDays(tip.getDays());
			  item.setDescription(tip.getDescription());
			  Drug drug = new Drug();
			  drug.setId(tip.getDrug());
			  item.setDrug(drug);
			  
			  Symptom symptom = new Symptom();
			  symptom.setId(tip.getSymptom());
			  item.setSymptom(symptom);
			  item.setTreatment(treatment);
			  return item;
		  }).collect(Collectors.toList());
		  
		 
		  treatment_ItemRepo.saveAll(tretments);
		  
		  
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public Treatment getTreatment(Long id) {
		Optional<Treatment> optional = treamentRepo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
}
