package com.cdac.tenantmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.tenantmanagement.Model.OrganisationType;

@Repository
public interface OrgTypeRepo extends JpaRepository<OrganisationType, Integer> {

}
