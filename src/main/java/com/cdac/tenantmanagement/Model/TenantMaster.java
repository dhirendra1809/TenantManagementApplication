package com.cdac.tenantmanagement.Model;

import java.sql.*;
import java.util.List;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "tenant_master")
public class TenantMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tenant_id")
    private int tenantId;

    @Column(name = "realm_id")
    private String realmId;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "reg_no")
    private int regNo;

    @Column(name = "valid_from")
    private Timestamp validFrom;

    @Column(name = "valid_upto")
    private Timestamp validUpto;

    @Column(name = "login_url")
    private String loginUrl;

    @Column(name = "password_change_url")
    private String passwordChangeUrl;

    @Column(name = "status")
    private String status;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Column(name = "last_modified_date")
    private Timestamp lastModifiedDate;

    @OneToMany(mappedBy = "tenant_master")
    private List<TenantServiceMaster> tenant_service_master;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "regno", referencedColumnName = "reg_no")
    private TenantDetails tenant_details;   

}
