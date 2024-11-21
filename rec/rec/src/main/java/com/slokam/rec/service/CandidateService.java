package com.slokam.rec.service;

import com.slokam.rec.entity.Candidate;
import com.slokam.rec.repo.CandidateRepo;

import java.util.*;

public interface CandidateService {
	
	public List<Candidate> getAll();
	public Candidate getById(Integer id);
	public Candidate save(Candidate Candidate);
	public void remove(Candidate Candidate);

}
