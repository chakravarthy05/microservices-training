package com.swarmhr.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.swarmhr.gateway.entity.Reports;

@Repository
public interface ReportsRepository extends JpaRepository<Reports, Integer>, JpaSpecificationExecutor<Reports> {
	
	

}

