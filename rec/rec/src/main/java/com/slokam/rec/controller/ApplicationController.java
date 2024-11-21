package com.slokam.rec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.http.HttpStatus;

import com.slokam.rec.entity.Application;
import com.slokam.rec.service.ApplicationService;

@RestController
@RequestMapping("Application") 
public class ApplicationController {
	 @Autowired
	 private ApplicationService service;
	 


	 @PostMapping("/save") 
	 public ResponseEntity<Application>	 saveApplication(@RequestBody Application obj)
	 throws Exception{
		  service.save(obj);
		  return new ResponseEntity<Application>(obj,HttpStatus.CREATED);
	 }
	
	 @PostMapping("/delete") 
	 public ResponseEntity<Application>	 deleteApplication(@RequestBody Application obj)
	 throws Exception{
		  service.remove(obj);
		  return new ResponseEntity<Application>(obj,HttpStatus.CREATED);
	 }
	 @GetMapping("/all")
	 public ResponseEntity<List<Application>> getAllApplication(){
		 List<Application> list = service.getAll();
		 return new ResponseEntity<List<Application>>(list,HttpStatus.OK);
	 }
	 
	 @GetMapping("/id")
	 public ResponseEntity<Application> getByIdApplication(Integer id) {
		  Application obj =  service.getById(id);
		  return new ResponseEntity<Application>(obj,HttpStatus.OK);
	 }
	
}

