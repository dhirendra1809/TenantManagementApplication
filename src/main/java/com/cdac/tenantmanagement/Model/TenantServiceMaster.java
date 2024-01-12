package com.cdac.tenantmanagement.Model;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "tenant_service_master")
public class TenantServiceMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sno")
    private int sno;

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    @Column(name = "tenant_id", insertable = false, updatable = false)
    private int tenantId;

    @Column(name = "service_id", insertable = false, updatable = false)
    private int serviceId;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "tenant_id", referencedColumnName = "tenant_id")
    private TenantMaster tenant_master;

    @OneToOne
    @JoinColumn(name = "service_id", referencedColumnName = "service_id")
    private ServiceMaster service_master;

}