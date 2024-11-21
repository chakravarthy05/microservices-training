package com.slokam.rec.service;

import com.slokam.rec.entity.Skill;
import com.slokam.rec.repo.SkillRepo;

import java.util.*;

public interface SkillService {
	
	public List<Skill> getAll();
	public Skill getById(Integer id);
	public Skill save(Skill Skill);
	public void remove(Skill Skill);

}
