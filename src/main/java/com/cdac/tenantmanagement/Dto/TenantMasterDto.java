package com.cdac.tenantmanagement.Dto;

import java.sql.Timestamp;
import java.util.List;

import com.cdac.tenantmanagement.Model.TenantDetails;
import com.cdac.tenantmanagement.Model.TenantServiceMaster;

import lombok.Data;

@Data
public class TenantMasterDto {

    private int tenantId;

    private String realmId;

    private String clientId;

    private int regNo;

    private String validFrom;

    private String validUpto;

    private String loginUrl;

    private String passwordChangeUrl;

    private String status;

    private String lastModifiedBy;

    private String lastModifiedDate;

    // private List<TenantServiceMaster> tenant_service_master;

    // private TenantDetails tenant_details;

}
