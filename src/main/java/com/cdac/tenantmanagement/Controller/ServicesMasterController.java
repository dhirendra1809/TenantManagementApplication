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

import com.cdac.tenantmanagement.Dto.ServicesDto;
import com.cdac.tenantmanagement.Model.ServiceMaster;
import com.cdac.tenantmanagement.Services.ServiceMasterImp;

@RestController
@CrossOrigin("*")
@RequestMapping("/services")
public class ServicesMasterController {

    @Autowired
    ServiceMasterImp serviceMasterImp;


    @GetMapping("/getAllServices")
    public ResponseEntity<?> getAllServices(){

        List<ServiceMaster> serviceMastersList = serviceMasterImp.getServiceList();
        if(serviceMastersList.size() == 0){
            return ResponseEntity.status(HttpStatus.OK).body("EMPTY");
        }
        return ResponseEntity.status(HttpStatus.OK).body(serviceMastersList);
    }

    @GetMapping("/getServiceById/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") int id){
        
        Optional<ServiceMaster> serviceMasterOptional = serviceMasterImp.getServiceById(id);
        if(!serviceMasterOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body("NOTFOUND");
        }
        return ResponseEntity.status(HttpStatus.OK).body(serviceMasterOptional.get());
    }

    @PostMapping("/addService")
    public ResponseEntity<?> addCategory(@RequestBody ServicesDto servicesDto){               
        return ResponseEntity.status(HttpStatus.OK).body(serviceMasterImp.addService(servicesDto)); 
    }

    @PostMapping("/deleteService/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") int id){               
        return ResponseEntity.status(HttpStatus.OK).body(serviceMasterImp.deleteServiceById(id)); 
    }

    @PostMapping("/updateService/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody ServicesDto orgCategoryDto){               
        return ResponseEntity.status(HttpStatus.OK).body(serviceMasterImp.updateServiceById(orgCategoryDto)); 
    }  
    
}
