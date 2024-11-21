package com.slokam.rec.service;

import com.slokam.rec.entity.InteviewLevel;
import com.slokam.rec.repo.InteviewLevelRepo;

import java.util.*;

public interface InteviewLevelService {
	
	public List<InteviewLevel> getAll();
	public InteviewLevel getById(Integer id);
	public InteviewLevel save(InteviewLevel InteviewLevel);
	public void remove(InteviewLevel InteviewLevel);

}
