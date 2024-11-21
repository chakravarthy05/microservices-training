package com.slokam.rec.service;

import com.slokam.rec.entity.Location;
import com.slokam.rec.repo.LocationRepo;

import java.util.*;

public interface LocationService {
	
	public List<Location> getAll();
	public Location getById(Integer id);
	public Location save(Location Location);
	public void remove(Location Location);

}
