package com.slokam.rec.service.impl;

import com.slokam.rec.entity.Joining;
import com.slokam.rec.repo.JoiningRepo;
import com.slokam.rec.service.JoiningService;
import org.springframework.stereotype.Service;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class JoiningServiceImpl implements JoiningService {

      
   

	@Autowired
	private JoiningRepo repo;
	
	@Override
	public List<Joining> getAll() {
		return repo.findAll();
	}

	@Override
	public Joining getById(Integer id) {
		
		Optional<Joining> opt = repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else
		{
			return null;
		}
	}

	@Override
	public Joining save(Joining todo) {
		return repo.save(todo);
	}

	@Override
	public void remove(Joining todo) {
		repo.delete(todo);
	}
}
