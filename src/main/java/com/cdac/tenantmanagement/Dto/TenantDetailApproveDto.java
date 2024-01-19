package com.cdac.tenantmanagement.Dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class TenantDetailApproveDto {

    private String orgName;

    private int regNo;

    private String orgRealm;

    private String orgClient;

    private byte[] logo;

}
