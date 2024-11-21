package com.slokam.rec.service.impl;

import com.slokam.rec.entity.Shortlisted;
import com.slokam.rec.repo.ShortlistedRepo;
import com.slokam.rec.service.ShortlistedService;
import org.springframework.stereotype.Service;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ShortlistedServiceImpl implements ShortlistedService {

      
   

	@Autowired
	private ShortlistedRepo repo;
	
	@Override
	public List<Shortlisted> getAll() {
		return repo.findAll();
	}

	@Override
	public Shortlisted getById(Integer id) {
		
		Optional<Shortlisted> opt = repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else
		{
			return null;
		}
	}

	@Override
	public Shortlisted save(Shortlisted todo) {
		return repo.save(todo);
	}

	@Override
	public void remove(Shortlisted todo) {
		repo.delete(todo);
	}
}
