package com.cdac.tenantmanagement.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.tenantmanagement.Dto.OrgTypeDto;
import com.cdac.tenantmanagement.Model.OrganisationType;
import com.cdac.tenantmanagement.Repository.OrgTypeRepo;

@Service
public class OrgTypeServiceImp {

    @Autowired
    OrgTypeRepo orgTypeRepo;

    public List<OrganisationType> getOrgTypeList() {
        List<OrganisationType> organisationTypes = orgTypeRepo.findAll();
        return organisationTypes;
    }

    public Optional<OrganisationType> getOrgTypeById(int id) {
        Optional<OrganisationType> organisationType = orgTypeRepo.findById(id);
        return organisationType;
    }

    public String addType(OrgTypeDto orgTypeDto) {
        OrganisationType organisationType = new OrganisationType();
        organisationType.setOrgType(orgTypeDto.getOrgType());
        OrganisationType organisationType2 = orgTypeRepo.save(organisationType);
        if (organisationType2.getOrgType().equals(orgTypeDto.getOrgType())) {
            return "SUCCESS";
        } else {
            return "FAILED";
        }
    }

    public String deleteOrgTypeById(int id) {
        Optional<OrganisationType> organisationType = orgTypeRepo.findById(id);
        if (organisationType.isPresent()) {
            orgTypeRepo.deleteById(id);
            return "SUCCESS";
        } else {
            return "NOT_PRESENT";
        }
    }

    public String updateOrgTypeById(OrgTypeDto orgTypeDto) {
        Optional<OrganisationType> organisationType = orgTypeRepo.findById(orgTypeDto.getId());
        if (organisationType.isPresent()) {
            OrganisationType organisationType2 = organisationType.get();
            organisationType2.setOrgType(orgTypeDto.getOrgType());
            orgTypeRepo.save(organisationType2);
            return "SUCCESS";
        } else {
            return "NOT_PRESENT";
        }
    }


    
    
}
