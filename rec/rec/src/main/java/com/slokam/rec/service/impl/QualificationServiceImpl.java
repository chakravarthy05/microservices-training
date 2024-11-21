package com.slokam.rec.service.impl;

import com.slokam.rec.entity.Qualification;
import com.slokam.rec.repo.QualificationRepo;
import com.slokam.rec.service.QualificationService;
import org.springframework.stereotype.Service;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class QualificationServiceImpl implements QualificationService {

      
   

	@Autowired
	private QualificationRepo repo;
	
	@Override
	public List<Qualification> getAll() {
		return repo.findAll();
	}

	@Override
	public Qualification getById(Integer id) {
		
		Optional<Qualification> opt = repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else
		{
			return null;
		}
	}

	@Override
	public Qualification save(Qualification todo) {
		return repo.save(todo);
	}

	@Override
	public void remove(Qualification todo) {
		repo.delete(todo);
	}
}
