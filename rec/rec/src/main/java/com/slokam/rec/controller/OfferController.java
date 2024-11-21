package com.slokam.rec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.http.HttpStatus;

import com.slokam.rec.entity.Offer;
import com.slokam.rec.service.OfferService;

@RestController
@RequestMapping("Offer") 
public class OfferController {
	 @Autowired
	 private OfferService service;
	 


	 @PostMapping("/save") 
	 public ResponseEntity<Offer>	 saveOffer(@RequestBody Offer obj)
	 throws Exception{
		  service.save(obj);
		  return new ResponseEntity<Offer>(obj,HttpStatus.CREATED);
	 }
	
	 @PostMapping("/delete") 
	 public ResponseEntity<Offer>	 deleteOffer(@RequestBody Offer obj)
	 throws Exception{
		  service.remove(obj);
		  return new ResponseEntity<Offer>(obj,HttpStatus.CREATED);
	 }
	 @GetMapping("/all")
	 public ResponseEntity<List<Offer>> getAllOffer(){
		 List<Offer> list = service.getAll();
		 return new ResponseEntity<List<Offer>>(list,HttpStatus.OK);
	 }
	 
	 @GetMapping("/id")
	 public ResponseEntity<Offer> getByIdOffer(Integer id) {
		  Offer obj =  service.getById(id);
		  return new ResponseEntity<Offer>(obj,HttpStatus.OK);
	 }
	
}

