package com.cdac.tenantmanagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.tenantmanagement.KeycloakService.KeycloakAdminClientService;
import com.cdac.tenantmanagement.Model.TenantDetails;

@CrossOrigin("*")
@RequestMapping("/auth")
@RestController
public class KeycloakController {

    @Autowired
    KeycloakAdminClientService keycloakAdminClientService;

    @RequestMapping("/check")
    public String keycloakConnectionStatus() {

        TenantDetails td = new TenantDetails();
        td.setOrgName("CDAC-HYD");
        td.setOrgWebsiteUrl("http://www.ndrf.com");
        td.setOrgEmailId("ndrfunit1@ndrf.com");
        td.setOrgNodalOfficerName("Vijay Verma");
        keycloakAdminClientService.createNewClient(td);
        // keycloakAdminClientService.addNewAdmin(td);

        return "Success";
    }
  
}
