package com.slokam.rec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.http.HttpStatus;

import com.slokam.rec.entity.Position;
import com.slokam.rec.service.PositionService;

@RestController
@RequestMapping("Position") 
public class PositionController {
	 @Autowired
	 private PositionService service;
	 


	 @PostMapping("/save") 
	 public ResponseEntity<Position>	 savePosition(@RequestBody Position obj)
	 throws Exception{
		  service.save(obj);
		  return new ResponseEntity<Position>(obj,HttpStatus.CREATED);
	 }
	
	 @PostMapping("/delete") 
	 public ResponseEntity<Position>	 deletePosition(@RequestBody Position obj)
	 throws Exception{
		  service.remove(obj);
		  return new ResponseEntity<Position>(obj,HttpStatus.CREATED);
	 }
	 @GetMapping("/all")
	 public ResponseEntity<List<Position>> getAllPosition(){
		 List<Position> list = service.getAll();
		 return new ResponseEntity<List<Position>>(list,HttpStatus.OK);
	 }
	 
	 @GetMapping("/id")
	 public ResponseEntity<Position> getByIdPosition(Integer id) {
		  Position obj =  service.getById(id);
		  return new ResponseEntity<Position>(obj,HttpStatus.OK);
	 }
	
}

