package com.cdac.tenantmanagement.Dto;

import java.util.List;

import lombok.Data;

@Data
public class RealmsDtos {

    private String realmsId;

    private String realmsName;

    private String clientId;

    private String userName;

    private String email;

    private String firstName;

    private String lastName;

    private List<String> realmRole;

    private List<String> clientRole;

}
