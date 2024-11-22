package com.slokam.healthcare.dpi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnCloudPlatform;
import org.springframework.boot.cloud.CloudPlatform;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slokam.healthcare.dpi.entity.Treatment;
import com.slokam.healthcare.dpi.request.TreatmentRequestPojo;
import com.slokam.healthcare.dpi.service.ITreatmentService;

@RestController
@RequestMapping("treatment")
//@ConditionalOnCloudPlatform(CloudPlatform.AZURE_APP_SERVICE)
//@conditionalon
public class TreatmentController {
	//hello chanages by developer 1
	// changes in feature 1 branch.
	// changes in feature 1 branch second time.
	@Autowired
	@Qualifier("treatmentServiceDBImpl")
	private ITreatmentService treatementService;
	// changes by developer 2
	//changes  by developer 1
	@GetMapping("/{tid}")
	public ResponseEntity<Treatment> getTreatment(@PathVariable("tid") Long id){
		Treatment treatment = treatementService.getTreatment(id);
		return new ResponseEntity<Treatment>(treatment,HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Treatment> saveTreatment(@RequestBody TreatmentRequestPojo treatment){
		treatementService.saveTreatment(treatment);
		//ResponseEntity<Treatment> re = new ResponseEntity<Treatment>(treatment,HttpStatus.OK);
		return null;
	}
	
	// changes by developer 2
	
}
