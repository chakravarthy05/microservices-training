package com.swarmhr.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swarmhr.gateway.entity.SwarmHRJwtToken;

public interface SwarmHRJwtTokenRepository extends JpaRepository<SwarmHRJwtToken, Long>{
	public SwarmHRJwtToken findByToken(String token);
}
