package com.slokam.rec.service;

import com.slokam.rec.entity.Offer;
import com.slokam.rec.repo.OfferRepo;

import java.util.*;

public interface OfferService {
	
	public List<Offer> getAll();
	public Offer getById(Integer id);
	public Offer save(Offer Offer);
	public void remove(Offer Offer);

}
