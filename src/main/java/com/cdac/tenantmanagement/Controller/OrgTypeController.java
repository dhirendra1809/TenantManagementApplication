package com.cdac.tenantmanagement.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.tenantmanagement.Dto.OrgTypeDto;
import com.cdac.tenantmanagement.Model.OrganisationType;
import com.cdac.tenantmanagement.Services.OrgTypeServiceImp;

@CrossOrigin("*")
@RequestMapping("/type")
@RestController
public class OrgTypeController {

    @Autowired
    OrgTypeServiceImp orgTypeServiceImp;

    @GetMapping("/getAllOrgType")
    public ResponseEntity<?> getallTypes(){
        List<OrganisationType> organisationTypes = orgTypeServiceImp.getOrgTypeList();
        if(organisationTypes.size() == 0){
            return ResponseEntity.status(HttpStatus.OK).body("EMPTY");
        }
        return ResponseEntity.status(HttpStatus.OK).body(organisationTypes);
    }

    @GetMapping("/getTypeById/{id}")
    public ResponseEntity<?> getTypeById(@PathVariable("id") int id){
        Optional<OrganisationType> organisationType = orgTypeServiceImp.getOrgTypeById(id);
        if(!organisationType.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body("NOTFOUND");
        }
        return ResponseEntity.status(HttpStatus.OK).body(organisationType);
    }

    @PostMapping("/addType")
    public ResponseEntity<?> addType(@RequestBody OrgTypeDto orgTypeDto){               
        return ResponseEntity.status(HttpStatus.OK).body(orgTypeServiceImp.addType(orgTypeDto)); 
    }

    @PostMapping("/deleteType/{id}")
    public ResponseEntity<?> deleteType(@PathVariable("id") int id){               
        return ResponseEntity.status(HttpStatus.OK).body(orgTypeServiceImp.deleteOrgTypeById(id)); 
    }

    @PostMapping("/updateType/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody OrgTypeDto orgTypeDto){               
        return ResponseEntity.status(HttpStatus.OK).body(orgTypeServiceImp.updateOrgTypeById(orgTypeDto)); 
    }
    
}
