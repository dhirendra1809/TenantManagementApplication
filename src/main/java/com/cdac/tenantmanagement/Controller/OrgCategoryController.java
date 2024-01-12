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

import com.cdac.tenantmanagement.Dto.OrgCategoryDto;
import com.cdac.tenantmanagement.Model.OrganisationCategory;
import com.cdac.tenantmanagement.Services.OrgCategoryServiceImp;

@CrossOrigin("*")
@RestController
@RequestMapping("/orgcategory")
public class OrgCategoryController {

    @Autowired
    OrgCategoryServiceImp orgCategoryServiceImp;


    @GetMapping("/getAllCategory")
    public ResponseEntity<?> getallCategory(){

        List<OrganisationCategory> organisationCategory = orgCategoryServiceImp.getOrgList();
        if(organisationCategory.size() == 0){
            return ResponseEntity.status(HttpStatus.OK).body("EMPTY");
        }
        return ResponseEntity.status(HttpStatus.OK).body(organisationCategory);
    }

    @GetMapping("/getCateById/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") int id){
        
        Optional<OrganisationCategory> organisationCategory = orgCategoryServiceImp.getOrgById(id);
        if(!organisationCategory.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body("NOTFOUND");
        }
        return ResponseEntity.status(HttpStatus.OK).body(organisationCategory);
    }

    @PostMapping("/addCategory")
    public ResponseEntity<?> addCategory(@RequestBody OrgCategoryDto orgCategoryDto){               
        return ResponseEntity.status(HttpStatus.OK).body(orgCategoryServiceImp.addCategory(orgCategoryDto)); 
    }

    @PostMapping("/deleteCategory/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") int id){               
        return ResponseEntity.status(HttpStatus.OK).body(orgCategoryServiceImp.deleteOrgCateById(id)); 
    }

    @PostMapping("/updateCategory/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody OrgCategoryDto orgCategoryDto){               
        return ResponseEntity.status(HttpStatus.OK).body(orgCategoryServiceImp.updateOrgCateById(orgCategoryDto)); 
    }    
}
