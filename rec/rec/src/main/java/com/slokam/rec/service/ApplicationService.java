package com.slokam.rec.service;

import com.slokam.rec.entity.Application;
import com.slokam.rec.repo.ApplicationRepo;

import java.util.*;

public interface ApplicationService {
	
	public List<Application> getAll();
	public Application getById(Integer id);
	public Application save(Application Application);
	public void remove(Application Application);

}
