package com.slokam.rec.service;

import com.slokam.rec.entity.Position;
import com.slokam.rec.repo.PositionRepo;

import java.util.*;

public interface PositionService {
	
	public List<Position> getAll();
	public Position getById(Integer id);
	public Position save(Position Position);
	public void remove(Position Position);

}
