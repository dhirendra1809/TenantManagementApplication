package com.cdac.tenantmanagement.Dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
@Data
public class TenantRegistrationDto {

    private int regNo;

    private String orgName;

    private String orgType;

    private String orgCategory;

    private String orgEmailId;

    private String orgContactNo;

    private String nodalOfficerName;

    private String nodalOfficerContactNo;

    private String nodalOfficerEmailId;

    private String orgAddress;

    private String orgWebsiteUrl;

    private MultipartFile orgLogoFile;

    private MultipartFile orgSupportingDocFile;

    
}
