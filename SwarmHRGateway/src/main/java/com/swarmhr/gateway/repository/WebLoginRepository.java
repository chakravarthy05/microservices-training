package com.swarmhr.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swarmhr.gateway.entity.WebLogin;

public interface WebLoginRepository extends JpaRepository<WebLogin, Long> {

}
