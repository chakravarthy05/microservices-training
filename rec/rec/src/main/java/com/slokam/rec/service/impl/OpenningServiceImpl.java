package com.slokam.rec.service.impl;

import com.slokam.rec.entity.Openning;
import com.slokam.rec.repo.OpenningRepo;
import com.slokam.rec.service.OpenningService;
import org.springframework.stereotype.Service;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class OpenningServiceImpl implements OpenningService {

      
   

	@Autowired
	private OpenningRepo repo;
	
	@Override
	public List<Openning> getAll() {
		return repo.findAll();
	}

	@Override
	public Openning getById(Integer id) {
		
		Optional<Openning> opt = repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else
		{
			return null;
		}
	}

	@Override
	public Openning save(Openning todo) {
		return repo.save(todo);
	}

	@Override
	public void remove(Openning todo) {
		repo.delete(todo);
	}
}
