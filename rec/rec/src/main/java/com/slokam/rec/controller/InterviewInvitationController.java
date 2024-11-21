package com.slokam.rec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.http.HttpStatus;

import com.slokam.rec.entity.InterviewInvitation;
import com.slokam.rec.service.InterviewInvitationService;

@RestController
@RequestMapping("InterviewInvitation") 
public class InterviewInvitationController {
	 @Autowired
	 private InterviewInvitationService service;
	 


	 @PostMapping("/save") 
	 public ResponseEntity<InterviewInvitation>	 saveInterviewInvitation(@RequestBody InterviewInvitation obj)
	 throws Exception{
		  service.save(obj);
		  return new ResponseEntity<InterviewInvitation>(obj,HttpStatus.CREATED);
	 }
	
	 @PostMapping("/delete") 
	 public ResponseEntity<InterviewInvitation>	 deleteInterviewInvitation(@RequestBody InterviewInvitation obj)
	 throws Exception{
		  service.remove(obj);
		  return new ResponseEntity<InterviewInvitation>(obj,HttpStatus.CREATED);
	 }
	 @GetMapping("/all")
	 public ResponseEntity<List<InterviewInvitation>> getAllInterviewInvitation(){
		 List<InterviewInvitation> list = service.getAll();
		 return new ResponseEntity<List<InterviewInvitation>>(list,HttpStatus.OK);
	 }
	 
	 @GetMapping("/id")
	 public ResponseEntity<InterviewInvitation> getByIdInterviewInvitation(Integer id) {
		  InterviewInvitation obj =  service.getById(id);
		  return new ResponseEntity<InterviewInvitation>(obj,HttpStatus.OK);
	 }
	
}

