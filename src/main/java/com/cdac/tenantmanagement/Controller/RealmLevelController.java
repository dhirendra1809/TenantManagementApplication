package com.cdac.tenantmanagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.tenantmanagement.Dto.RealmsDtos;
import com.cdac.tenantmanagement.KeycloakService.KeycloakAdminClientService;
import com.cdac.tenantmanagement.Model.TenantDetails;

import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin("*")
@RestController
@RequestMapping("/realms")
public class RealmLevelController {

    @Autowired
    KeycloakAdminClientService keycloakAdminClientService;

    @PostMapping("/createNewRealms")
    public ResponseEntity<?> addRealms(@RequestBody RealmsDtos realmsDtos) {
        return ResponseEntity.status(HttpStatus.OK).body(keycloakAdminClientService.createNewRealm(realmsDtos));
    }

    @PostMapping("/updateRealms")
    public ResponseEntity<?> updateRealms(@RequestBody RealmsDtos realmsDtos) {
        return ResponseEntity.status(HttpStatus.OK).body(keycloakAdminClientService.updateRealm(realmsDtos));
    }

    @PostMapping("/removeRealms")
    public ResponseEntity<?> removeRealms(@RequestBody RealmsDtos realmsDtos) {
        return ResponseEntity.status(HttpStatus.OK).body(keycloakAdminClientService.removeRealm(realmsDtos));
    }

    @PostMapping("/createNewRealmsAdmin")
    public ResponseEntity<?> addRealmsAdmin(@RequestBody RealmsDtos realmsDtos) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(keycloakAdminClientService.creatingRealmLevelAdmin(realmsDtos));
    }

    @PostMapping("/createRealmRole")
    public ResponseEntity<?> addNewRealmRole(@RequestBody RealmsDtos realmsDtos) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(keycloakAdminClientService.createRealmRole(realmsDtos));
    }

    @GetMapping("/listRealmRole/{realm}")
    public ResponseEntity<?> listRealmRole(@PathVariable("realm") String realm) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(keycloakAdminClientService.listRealmRole(realm));
    }

    @PostMapping("/removeRealmRole")
    public ResponseEntity<?> deleteRealmRole(@RequestBody RealmsDtos realmsDtos) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(keycloakAdminClientService.removeRealmRole(realmsDtos));
    }

    @PostMapping("/createClient")
    public ResponseEntity<?> addNewClient(@RequestBody RealmsDtos realmsDtos) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(keycloakAdminClientService.createClient(realmsDtos));
    }

    @PostMapping("/removeClient")
    public ResponseEntity<?> removeClient(@RequestBody RealmsDtos realmsDtos) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(keycloakAdminClientService.deleteClient(realmsDtos));
    }

    @PostMapping("/createClientRole")
    public ResponseEntity<?> addClientRole(@RequestBody RealmsDtos realmsDtos) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(keycloakAdminClientService.createClientRole(realmsDtos));
    }

    @PostMapping("/removeClientRole")
    public ResponseEntity<?> deleteClientRole(@RequestBody RealmsDtos realmsDtos) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(keycloakAdminClientService.removeClientRole(realmsDtos));
    }

    @PostMapping("/disableEnableRealm")
    public ResponseEntity<?> disableEnableRealm(@RequestBody RealmsDtos realmsDtos) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(keycloakAdminClientService.disableEnableRealm(realmsDtos));
    }

}
