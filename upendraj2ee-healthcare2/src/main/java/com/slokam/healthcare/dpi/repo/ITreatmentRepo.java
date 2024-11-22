package com.slokam.healthcare.dpi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.slokam.healthcare.dpi.entity.Treatment;

@Repository
public interface ITreatmentRepo extends JpaRepository<Treatment, Long>{
	

}
