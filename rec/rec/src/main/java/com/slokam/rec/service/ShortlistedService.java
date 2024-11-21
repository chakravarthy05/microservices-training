package com.slokam.rec.service;

import com.slokam.rec.entity.Shortlisted;
import com.slokam.rec.repo.ShortlistedRepo;

import java.util.*;

public interface ShortlistedService {
	
	public List<Shortlisted> getAll();
	public Shortlisted getById(Integer id);
	public Shortlisted save(Shortlisted Shortlisted);
	public void remove(Shortlisted Shortlisted);

}
