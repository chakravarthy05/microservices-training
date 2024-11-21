package com.swarmhr.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swarmhr.gateway.entity.SwarmHRUser;

public interface SwarmHRUserRepository extends JpaRepository<SwarmHRUser, Long>{
	
	public SwarmHRUser findByUserName(String userName);

}
