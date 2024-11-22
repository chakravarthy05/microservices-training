package com.slokam.healthcare.dpi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.slokam.healthcare.dpi.entity.Patient;

@Repository
public interface IPatientRepo extends JpaRepository<Patient, Long> {

	
}
