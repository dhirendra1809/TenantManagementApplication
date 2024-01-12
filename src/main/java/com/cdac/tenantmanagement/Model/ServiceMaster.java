package com.cdac.tenantmanagement.Model;

import java.util.List;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "service_master")
public class ServiceMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private int serviceId;

    @Column(name = "service_name")
    private String serviceName;

    @OneToOne(mappedBy = "service_master")
    private TenantServiceMaster tenant_service_master;
}
