package com.slokam.rec.service.impl;

import com.slokam.rec.entity.Application;
import com.slokam.rec.repo.ApplicationRepo;
import com.slokam.rec.service.ApplicationService;
import org.springframework.stereotype.Service;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ApplicationServiceImpl implements ApplicationService {

      
   

	@Autowired
	private ApplicationRepo repo;
	
	@Override
	public List<Application> getAll() {
		return repo.findAll();
	}

	@Override
	public Application getById(Integer id) {
		
		Optional<Application> opt = repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else
		{
			return null;
		}
	}

	@Override
	public Application save(Application todo) {
		return repo.save(todo);
	}

	@Override
	public void remove(Application todo) {
		repo.delete(todo);
	}
}
