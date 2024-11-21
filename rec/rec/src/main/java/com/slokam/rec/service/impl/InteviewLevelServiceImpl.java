package com.slokam.rec.service.impl;

import com.slokam.rec.entity.InteviewLevel;
import com.slokam.rec.repo.InteviewLevelRepo;
import com.slokam.rec.service.InteviewLevelService;
import org.springframework.stereotype.Service;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class InteviewLevelServiceImpl implements InteviewLevelService {

      
   

	@Autowired
	private InteviewLevelRepo repo;
	
	@Override
	public List<InteviewLevel> getAll() {
		return repo.findAll();
	}

	@Override
	public InteviewLevel getById(Integer id) {
		
		Optional<InteviewLevel> opt = repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else
		{
			return null;
		}
	}

	@Override
	public InteviewLevel save(InteviewLevel todo) {
		return repo.save(todo);
	}

	@Override
	public void remove(InteviewLevel todo) {
		repo.delete(todo);
	}
}
