package com.swarmhr.gateway.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.swarmhr.gateway.entity.OffboardConfiguration;

@Repository
public interface OffboardConfigurationRepository extends JpaRepository<OffboardConfiguration, Integer>{

	List<OffboardConfiguration> findByOrganizationID(String organizationID);
	
}
