package com.slokam.rec.service.impl;

import com.slokam.rec.entity.Offer;
import com.slokam.rec.repo.OfferRepo;
import com.slokam.rec.service.OfferService;
import org.springframework.stereotype.Service;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class OfferServiceImpl implements OfferService {

      
   

	@Autowired
	private OfferRepo repo;
	
	@Override
	public List<Offer> getAll() {
		return repo.findAll();
	}

	@Override
	public Offer getById(Integer id) {
		
		Optional<Offer> opt = repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else
		{
			return null;
		}
	}

	@Override
	public Offer save(Offer todo) {
		return repo.save(todo);
	}

	@Override
	public void remove(Offer todo) {
		repo.delete(todo);
	}
}
