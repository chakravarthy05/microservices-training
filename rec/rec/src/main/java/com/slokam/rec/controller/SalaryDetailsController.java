package com.slokam.rec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.http.HttpStatus;

import com.slokam.rec.entity.SalaryDetails;
import com.slokam.rec.service.SalaryDetailsService;

@RestController
@RequestMapping("SalaryDetails") 
public class SalaryDetailsController {
	 @Autowired
	 private SalaryDetailsService service;
	 


	 @PostMapping("/save") 
	 public ResponseEntity<SalaryDetails>	 saveSalaryDetails(@RequestBody SalaryDetails obj)
	 throws Exception{
		  service.save(obj);
		  return new ResponseEntity<SalaryDetails>(obj,HttpStatus.CREATED);
	 }
	
	 @PostMapping("/delete") 
	 public ResponseEntity<SalaryDetails>	 deleteSalaryDetails(@RequestBody SalaryDetails obj)
	 throws Exception{
		  service.remove(obj);
		  return new ResponseEntity<SalaryDetails>(obj,HttpStatus.CREATED);
	 }
	 @GetMapping("/all")
	 public ResponseEntity<List<SalaryDetails>> getAllSalaryDetails(){
		 List<SalaryDetails> list = service.getAll();
		 return new ResponseEntity<List<SalaryDetails>>(list,HttpStatus.OK);
	 }
	 
	 @GetMapping("/id")
	 public ResponseEntity<SalaryDetails> getByIdSalaryDetails(Integer id) {
		  SalaryDetails obj =  service.getById(id);
		  return new ResponseEntity<SalaryDetails>(obj,HttpStatus.OK);
	 }
	
}

