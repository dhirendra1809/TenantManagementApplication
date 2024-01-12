package com.cdac.tenantmanagement.Model;

import java.sql.Timestamp;
import javax.persistence.*;

import lombok.Data;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
@Data
@Entity
@Table(name = "tenant_details")
public class TenantDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reg_no")
    private int regNo;

    @Column(name = "act_token")
    private String activationToken;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "org_emailid")
    private String orgEmailId;

    @Column(name = "org_contact_number")
    private String orgContactNumber;

    @Column(name = "org_nodal_officer_name")
    private String orgNodalOfficerName;

    @Column(name = "org_nodal_officer_mobile")
    private String orgNodalOfficerMobileNo;

    @Column(name = "org_nodal_officer_email")
    private String orgNodalOfficerEmail;

    @Column(name = "org_address")
    private String orgAddress;

    @Column(name = "org_website_url")
    private String orgWebsiteUrl;

    @Column(name = "org_logo_path")
    private String orgLogoPath;

    @Column(name = "org_category")
    private String orgCategory;

    @Column(name = "org_type")
    private String orgType;

    @Column(name = "approval_status")
    private String approvalStatus;

    @Column(name = "reg_date")
    private Timestamp regDate;

    @Column(name = "approval_date")
    private Timestamp approvalDate;

    @Column(name = "supporting_doc_path")
    private String supportingDocPath;

    @Column(name = "last_modified_date")
    private Timestamp lastModifiedDate;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @OneToOne(mappedBy = "tenant_details")
    private TenantMaster tenant_master;

}
