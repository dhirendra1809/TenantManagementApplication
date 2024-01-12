package com.cdac.tenantmanagement.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.tenantmanagement.Model.TenantDetails;

@Repository
public interface TenantDetailRepository extends JpaRepository<TenantDetails, Integer> {

    Optional<TenantDetails> findByRegNo(int id);

    Optional<TenantDetails> findByOrgEmailId(String emailid);

    List<TenantDetails> findByApprovalStatus(String status);

    Optional<List<TenantDetails>> findByActivationToken(String status);

    Optional<List<TenantDetails>> findByApprovalStatusAndActivationToken(String status, String act);

}
