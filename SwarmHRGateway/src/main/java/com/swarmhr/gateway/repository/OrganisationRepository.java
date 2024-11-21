package com.swarmhr.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.swarmhr.gateway.entity.Organisation;


public interface OrganisationRepository extends JpaRepository<Organisation, String>, JpaSpecificationExecutor<Organisation>{

	public Organisation findByOrganizationID(String organizationID);

}
