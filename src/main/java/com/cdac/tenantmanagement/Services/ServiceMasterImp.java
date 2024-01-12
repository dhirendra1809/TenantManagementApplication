package com.cdac.tenantmanagement.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.tenantmanagement.Dto.ServicesDto;
import com.cdac.tenantmanagement.Model.ServiceMaster;
import com.cdac.tenantmanagement.Repository.ServiceMasterRepo;

@Service
public class ServiceMasterImp {

    @Autowired
    ServiceMasterRepo serviceMasterRepo;

    public List<ServiceMaster> getServiceList() {
        List<ServiceMaster> servicesList = serviceMasterRepo.findAll();
        return servicesList;
    }

    public Optional<ServiceMaster> getServiceById(int id) {
        Optional<ServiceMaster> serviceOptional = serviceMasterRepo.findById(id);
        return serviceOptional;
    }

    public String addService (ServicesDto servicesDto) {
        ServiceMaster serviceMaster = new ServiceMaster();
        serviceMaster.setServiceName(servicesDto.getServiceName());
        ServiceMaster serviceMaster2 = serviceMasterRepo.save(serviceMaster);
        if (serviceMaster2.getServiceName().equals(servicesDto.getServiceName())) {
            return "SUCCESS";
        } else {
            return "FAILED";
        }
    }

    public String deleteServiceById(int id) {
        Optional<ServiceMaster> serviceMaster = serviceMasterRepo.findById(id);
        if (serviceMaster.isPresent()) {
            serviceMasterRepo.deleteById(id);
            return "SUCCESS";
        } else {
            return "NOT_PRESENT";
        }
    }

    public String updateServiceById(ServicesDto servicesDto) {
        Optional<ServiceMaster> serviceMasterOptional = serviceMasterRepo.findById(servicesDto.getServiceId());
        if (serviceMasterOptional.isPresent()) {
            ServiceMaster serviceMaster2 = serviceMasterOptional.get();
            serviceMaster2.setServiceName(servicesDto.getServiceName());
            serviceMasterRepo.save(serviceMaster2);
            return "SUCCESS";
        } else {
            return "NOT_PRESENT";
        }
    }

}
