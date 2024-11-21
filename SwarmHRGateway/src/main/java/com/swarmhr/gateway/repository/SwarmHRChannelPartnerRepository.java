package com.swarmhr.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swarmhr.gateway.entity.SwarmHRChannelPartner;

public interface SwarmHRChannelPartnerRepository extends JpaRepository<SwarmHRChannelPartner, String>{

	public SwarmHRChannelPartner findByUsername(String username);

}
