package com.slokam.rec.service.impl;

import com.slokam.rec.entity.Skill;
import com.slokam.rec.repo.SkillRepo;
import com.slokam.rec.service.SkillService;
import org.springframework.stereotype.Service;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SkillServiceImpl implements SkillService {

      
   

	@Autowired
	private SkillRepo repo;
	
	@Override
	public List<Skill> getAll() {
		return repo.findAll();
	}

	@Override
	public Skill getById(Integer id) {
		
		Optional<Skill> opt = repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else
		{
			return null;
		}
	}

	@Override
	public Skill save(Skill todo) {
		return repo.save(todo);
	}

	@Override
	public void remove(Skill todo) {
		repo.delete(todo);
	}
}
