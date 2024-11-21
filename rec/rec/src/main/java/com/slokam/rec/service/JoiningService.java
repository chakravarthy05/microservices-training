package com.slokam.rec.service;

import com.slokam.rec.entity.Joining;
import com.slokam.rec.repo.JoiningRepo;

import java.util.*;

public interface JoiningService {
	
	public List<Joining> getAll();
	public Joining getById(Integer id);
	public Joining save(Joining Joining);
	public void remove(Joining Joining);

}
