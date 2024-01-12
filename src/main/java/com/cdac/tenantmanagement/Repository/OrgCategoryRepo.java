package com.cdac.tenantmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.tenantmanagement.Model.OrganisationCategory;

@Repository
public interface OrgCategoryRepo extends JpaRepository<OrganisationCategory , Integer> {
    
}
