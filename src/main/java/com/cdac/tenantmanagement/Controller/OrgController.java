package com.cdac.tenantmanagement.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.tenantmanagement.Dto.RealmsDtos;
import com.cdac.tenantmanagement.Dto.TenantDetailApproveDto;
import com.cdac.tenantmanagement.Dto.TenantMasterDto;
import com.cdac.tenantmanagement.Dto.TenantRegistrationDto;
import com.cdac.tenantmanagement.KeycloakService.KeycloakAdminClientService;
import com.cdac.tenantmanagement.Model.TenantMaster;
import com.cdac.tenantmanagement.Services.OrgImpService;

@CrossOrigin("*")
@RestController
@RequestMapping("/organisation")
public class OrgController {

    @Autowired
    private OrgImpService orgImpService;

    @Autowired
    KeycloakAdminClientService keycloakAdminClientService;

    @PostMapping(value = "/registerOrg")
    public ResponseEntity<?> registerOrganisation(@ModelAttribute TenantRegistrationDto tenantRegistrationDto) {
        try {
            String resp = orgImpService.registerOrganisation(tenantRegistrationDto);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR");
    }

    @PostMapping("/orgemailverify")
    public String emailVarify(@RequestParam String token) {
        return orgImpService.verifyEMail(token);
    }

    @PostMapping(value = "/updateOrg")
    public ResponseEntity<?> updateOrganisation(@ModelAttribute TenantRegistrationDto tenantRegistrationDto) {
        try {
            String resp = orgImpService.updateRegisteredOrganisation(tenantRegistrationDto);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR");
    }

    @PostMapping(value = "/deleteOrg/{id}")
    public ResponseEntity<?> deleteOrganisation(@PathVariable("id") String tenantId) {
        try {
            String resp = orgImpService.deleteRegisteredOrganisation(tenantId);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR");
    }

    @GetMapping("/getalllist")
    public ResponseEntity<?> getAllList() {
        List<?> tenantList = orgImpService.findListByStatus("");
        if (tenantList.size() == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("EMPTY");
        }
        return ResponseEntity.status(HttpStatus.OK).body(tenantList);
    }

    @GetMapping("/getpendinglist")
    public ResponseEntity<?> getPendingList() {
        List<?> tenantList = orgImpService.findListByStatus("pending");
        if (tenantList.size() == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("EMPTY");
        }
        return ResponseEntity.status(HttpStatus.OK).body(tenantList);
    }

    // @GetMapping("/getapprovelist")
    // public ResponseEntity<?> getAllApproveList() {
    // List<TenantDetails> tenantList = orgImpService.findListByStatus("approve");
    // if (tenantList.size() == 0) {
    // return ResponseEntity.status(HttpStatus.OK).body("EMPTY");
    // }
    // return ResponseEntity.status(HttpStatus.OK).body(tenantList);
    // }

    @GetMapping("/getapprovelist")
    public ResponseEntity<?> getAllApproveList() {
        List<?> tenantList = orgImpService.findListByStatus("approve");
        if (tenantList.size() == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("EMPTY");
        }
        return ResponseEntity.status(HttpStatus.OK).body(tenantList);
    }

    @GetMapping("/getapprovelistforHomePage")
    public ResponseEntity<?> getApproveListForHomePage() {
        List<?> tenantList = orgImpService.homePageOrgList();
        if (tenantList.size() == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("EMPTY");
        }
        return ResponseEntity.status(HttpStatus.OK).body(tenantList);
    }

    // @GetMapping("/getallapproveorg")
    // public ResponseEntity<?> getAllApproveOrgListAtHomePage() {
    // List<TenantDetails> tenantList = orgImpService.findListByStatus("approve");
    // if (tenantList.size() == 0) {
    // return ResponseEntity.status(HttpStatus.OK).body("EMPTY");
    // }
    // return ResponseEntity.status(HttpStatus.OK).body(tenantList);
    // }

    @GetMapping("/getrejectlist")
    public ResponseEntity<?> getAllRejectList() {
        List<?> tenantList = orgImpService.findListByStatus("reject");
        if (tenantList.size() == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("EMPTY");
        }
        return ResponseEntity.status(HttpStatus.OK).body(tenantList);
    }

    @GetMapping("/getLogoFile/{id}")
    public ResponseEntity<?> getLogoFile(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(orgImpService.getLogo(id));
    }

    @GetMapping("/getSupportingFile/{id}")
    public ResponseEntity<?> getSupportFile(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(orgImpService.getSupportingDoc(id));
    }

    @PostMapping(value = "/approve/{id}")
    public ResponseEntity<?> onSuperAdminApproval(@RequestBody TenantMasterDto tenantMasterDto,
            @PathVariable("id") int id) {
        tenantMasterDto.setStatus("approve");
        return ResponseEntity.status(HttpStatus.OK).body(orgImpService.changeStatusByAdmin(id, tenantMasterDto));
    }

    @PostMapping("/reject/{id}")
    public ResponseEntity<?> onSuperAdminReject(@PathVariable("id") int id) {
        TenantMasterDto tenantMasterDto = new TenantMasterDto();
        tenantMasterDto.setStatus("reject");
        return ResponseEntity.status(HttpStatus.OK).body(orgImpService.changeStatusByAdmin(id, tenantMasterDto));
    }

    @PostMapping("/deletelogo/{id}")
    public ResponseEntity<?> deleteLogo(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(orgImpService.deleteLogo(id));
    }

    @PostMapping("/deleteSupportDoc/{id}")
    public ResponseEntity<?> deleteSupportDoc(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(orgImpService.deleteSupportDoc(id));
    }

    @PostMapping("/togglerED")
    public ResponseEntity<?> enableDisableToggler(@RequestBody TenantMasterDto tenantMasterDto) {
        RealmsDtos realmsDtos = new RealmsDtos();
        realmsDtos.setRealmsId(tenantMasterDto.getRealmId());
        return ResponseEntity.status(HttpStatus.OK).body(keycloakAdminClientService.disableEnableRealm(realmsDtos));
    }

}
