package com.slokam.rec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.http.HttpStatus;

import com.slokam.rec.entity.Openning;
import com.slokam.rec.service.OpenningService;

@RestController
@RequestMapping("Openning") 
public class OpenningController {
	 @Autowired
	 private OpenningService service;
	 


	 @PostMapping("/save") 
	 public ResponseEntity<Openning>	 saveOpenning(@RequestBody Openning obj)
	 throws Exception{
		  service.save(obj);
		  return new ResponseEntity<Openning>(obj,HttpStatus.CREATED);
	 }
	
	 @PostMapping("/delete") 
	 public ResponseEntity<Openning>	 deleteOpenning(@RequestBody Openning obj)
	 throws Exception{
		  service.remove(obj);
		  return new ResponseEntity<Openning>(obj,HttpStatus.CREATED);
	 }
	 @GetMapping("/all")
	 public ResponseEntity<List<Openning>> getAllOpenning(){
		 List<Openning> list = service.getAll();
		 return new ResponseEntity<List<Openning>>(list,HttpStatus.OK);
	 }
	 
	 @GetMapping("/id")
	 public ResponseEntity<Openning> getByIdOpenning(Integer id) {
		  Openning obj =  service.getById(id);
		  return new ResponseEntity<Openning>(obj,HttpStatus.OK);
	 }
	
}

