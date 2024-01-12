package com.cdac.tenantmanagement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.tenantmanagement.Model.TenantMaster;

@Repository
public interface TenantMasterRepo extends JpaRepository<TenantMaster, Integer> {
    Optional<TenantMaster> findByRegNo(int regId);
}
