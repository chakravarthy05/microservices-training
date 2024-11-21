package com.slokam.rec.service;

import com.slokam.rec.entity.Qualification;
import com.slokam.rec.repo.QualificationRepo;

import java.util.*;

public interface QualificationService {
	
	public List<Qualification> getAll();
	public Qualification getById(Integer id);
	public Qualification save(Qualification Qualification);
	public void remove(Qualification Qualification);

}
