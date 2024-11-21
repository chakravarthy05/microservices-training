package com.slokam.rec.service.impl;

import com.slokam.rec.entity.SalaryDetails;
import com.slokam.rec.repo.SalaryDetailsRepo;
import com.slokam.rec.service.SalaryDetailsService;
import org.springframework.stereotype.Service;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SalaryDetailsServiceImpl implements SalaryDetailsService {

      
   

	@Autowired
	private SalaryDetailsRepo repo;
	
	@Override
	public List<SalaryDetails> getAll() {
		return repo.findAll();
	}

	@Override
	public SalaryDetails getById(Integer id) {
		
		Optional<SalaryDetails> opt = repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else
		{
			return null;
		}
	}

	@Override
	public SalaryDetails save(SalaryDetails todo) {
		return repo.save(todo);
	}

	@Override
	public void remove(SalaryDetails todo) {
		repo.delete(todo);
	}
}
