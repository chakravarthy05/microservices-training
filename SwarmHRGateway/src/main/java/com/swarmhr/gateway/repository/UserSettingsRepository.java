package com.swarmhr.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swarmhr.gateway.entity.UserSettings;

@Repository
public interface UserSettingsRepository extends JpaRepository<UserSettings, Integer> {

	public UserSettings findByUsernameAndPropertyType(String username, String propertyType);

}
