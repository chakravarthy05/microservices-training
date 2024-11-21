package com.slokam.rec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.http.HttpStatus;

import com.slokam.rec.entity.Candidate;
import com.slokam.rec.service.CandidateService;

@RestController
@RequestMapping("Candidate") 
public class CandidateController {
	 @Autowired
	 private CandidateService service;
	 


	 @PostMapping("/save") 
	 public ResponseEntity<Candidate>	 saveCandidate(@RequestBody Candidate obj)
	 throws Exception{
		  service.save(obj);
		  return new ResponseEntity<Candidate>(obj,HttpStatus.CREATED);
	 }
	
	 @PostMapping("/delete") 
	 public ResponseEntity<Candidate>	 deleteCandidate(@RequestBody Candidate obj)
	 throws Exception{
		  service.remove(obj);
		  return new ResponseEntity<Candidate>(obj,HttpStatus.CREATED);
	 }
	 @GetMapping("/all")
	 public ResponseEntity<List<Candidate>> getAllCandidate(){
		 List<Candidate> list = service.getAll();
		 return new ResponseEntity<List<Candidate>>(list,HttpStatus.OK);
	 }
	 
	 @GetMapping("/id")
	 public ResponseEntity<Candidate> getByIdCandidate(Integer id) {
		  Candidate obj =  service.getById(id);
		  return new ResponseEntity<Candidate>(obj,HttpStatus.OK);
	 }
	
}

