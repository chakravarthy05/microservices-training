package com.slokam.rec.service;

import com.slokam.rec.entity.Openning;
import com.slokam.rec.repo.OpenningRepo;

import java.util.*;

public interface OpenningService {
	
	public List<Openning> getAll();
	public Openning getById(Integer id);
	public Openning save(Openning Openning);
	public void remove(Openning Openning);

}
