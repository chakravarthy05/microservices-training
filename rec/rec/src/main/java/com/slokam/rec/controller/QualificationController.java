package com.slokam.rec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.http.HttpStatus;

import com.slokam.rec.entity.Qualification;
import com.slokam.rec.service.QualificationService;

@RestController
@RequestMapping("Qualification") 
public class QualificationController {
	 @Autowired
	 private QualificationService service;
	 


	 @PostMapping("/save") 
	 public ResponseEntity<Qualification>	 saveQualification(@RequestBody Qualification obj)
	 throws Exception{
		  service.save(obj);
		  return new ResponseEntity<Qualification>(obj,HttpStatus.CREATED);
	 }
	
	 @PostMapping("/delete") 
	 public ResponseEntity<Qualification>	 deleteQualification(@RequestBody Qualification obj)
	 throws Exception{
		  service.remove(obj);
		  return new ResponseEntity<Qualification>(obj,HttpStatus.CREATED);
	 }
	 @GetMapping("/all")
	 public ResponseEntity<List<Qualification>> getAllQualification(){
		 List<Qualification> list = service.getAll();
		 return new ResponseEntity<List<Qualification>>(list,HttpStatus.OK);
	 }
	 
	 @GetMapping("/id")
	 public ResponseEntity<Qualification> getByIdQualification(Integer id) {
		  Qualification obj =  service.getById(id);
		  return new ResponseEntity<Qualification>(obj,HttpStatus.OK);
	 }
	
}

