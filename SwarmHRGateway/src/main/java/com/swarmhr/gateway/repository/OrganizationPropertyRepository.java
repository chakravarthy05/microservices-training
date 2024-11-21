package com.swarmhr.gateway.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.swarmhr.gateway.entity.OrganizationProperties;

@Repository
public interface OrganizationPropertyRepository extends JpaRepository<OrganizationProperties, String>, JpaSpecificationExecutor<OrganizationProperties>{
		
	public OrganizationProperties findByOrganizationIDAndPropertyType(String organizationID, String propertyType);
	
}
