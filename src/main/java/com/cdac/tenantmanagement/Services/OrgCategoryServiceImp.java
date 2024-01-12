package com.cdac.tenantmanagement.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.tenantmanagement.Dto.OrgCategoryDto;
import com.cdac.tenantmanagement.Model.OrganisationCategory;
import com.cdac.tenantmanagement.Repository.OrgCategoryRepo;

@Service
public class OrgCategoryServiceImp {

    @Autowired
    OrgCategoryRepo orgCategoryRepo;

    public List<OrganisationCategory> getOrgList() {
        List<OrganisationCategory> organisationCategory = orgCategoryRepo.findAll();
        return organisationCategory;
    }

    public Optional<OrganisationCategory> getOrgById(int id) {
        Optional<OrganisationCategory> organisationCategory = orgCategoryRepo.findById(id);
        return organisationCategory;
    }

    public String addCategory(OrgCategoryDto orgCategoryDto) {
        OrganisationCategory organisationCategory = new OrganisationCategory();
        organisationCategory.setOrgCategory(orgCategoryDto.getOrgCategory());
        OrganisationCategory organisationCategory2 = orgCategoryRepo.save(organisationCategory);
        if (organisationCategory2.getOrgCategory().equals(orgCategoryDto.getOrgCategory())) {
            return "SUCCESS";
        } else {
            return "FAILED";
        }
    }

    public String deleteOrgCateById(int id) {
        Optional<OrganisationCategory> organisationCategory = orgCategoryRepo.findById(id);
        if (organisationCategory.isPresent()) {
            orgCategoryRepo.deleteById(id);
            return "SUCCESS";
        } else {
            return "NOT_PRESENT";
        }
    }

    public String updateOrgCateById(OrgCategoryDto orgCategoryDto) {
        Optional<OrganisationCategory> organisationCategory = orgCategoryRepo.findById(orgCategoryDto.getId());
        if (organisationCategory.isPresent()) {
            OrganisationCategory organisationCategory2 = organisationCategory.get();
            organisationCategory2.setOrgCategory(orgCategoryDto.getOrgCategory());
            orgCategoryRepo.save(organisationCategory2);
            return "SUCCESS";
        } else {
            return "NOT_PRESENT";
        }
    }

}
