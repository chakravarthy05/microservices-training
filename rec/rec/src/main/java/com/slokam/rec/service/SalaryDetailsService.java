package com.slokam.rec.service;

import com.slokam.rec.entity.SalaryDetails;
import com.slokam.rec.repo.SalaryDetailsRepo;

import java.util.*;

public interface SalaryDetailsService {
	
	public List<SalaryDetails> getAll();
	public SalaryDetails getById(Integer id);
	public SalaryDetails save(SalaryDetails SalaryDetails);
	public void remove(SalaryDetails SalaryDetails);

}
