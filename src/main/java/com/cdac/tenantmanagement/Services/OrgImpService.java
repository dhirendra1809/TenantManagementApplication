package com.cdac.tenantmanagement.Services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cdac.tenantmanagement.Dto.RealmsDtos;
import com.cdac.tenantmanagement.Dto.TenantDetailApproveDto;
import com.cdac.tenantmanagement.Dto.TenantMasterDto;
import com.cdac.tenantmanagement.Dto.TenantRegistrationDto;
import com.cdac.tenantmanagement.KeycloakService.KeycloakAdminClientService;
import com.cdac.tenantmanagement.Model.TenantDetails;
import com.cdac.tenantmanagement.Model.TenantMaster;
import com.cdac.tenantmanagement.Repository.TenantDetailRepository;
import com.cdac.tenantmanagement.Repository.TenantMasterRepo;
import com.cdac.tenantmanagement.Utils.ActivationService;
import com.cdac.tenantmanagement.Utils.EmailSender;
import com.cdac.tenantmanagement.Utils.MD5Hashing;

@Service
@PropertySource("classpath:email.properties")
@PropertySource("classpath:application.properties")
public class OrgImpService {

    @Autowired
    private EmailSender eMail;

    @Autowired
    KeycloakAdminClientService keycloakAdminClientService;

    private String dateFormateString = "yyyy-MM-dd hh:mm:ss";

    @Value("${mail.subject1}")
    private String subject1;
    @Value("${mail.body.registartion1}")
    private String bodytext1;

    @Value("${mail.subject2}")
    private String subject2;
    @Value("${mail.body.registartion2}")
    private String bodytext2;

    @Autowired
    TenantDetailRepository tenantDetailRepository;

    @Autowired
    TenantMasterRepo tenantMasterRepo;

    @Value("${file.Path}")
    private String filePath;

    public String registerOrganisation(TenantRegistrationDto tenantRegistrationDto) throws Exception {

        System.out.println(tenantRegistrationDto.getOrgLogoFile().getBytes().length);
        Optional<TenantDetails> check = tenantDetailRepository.findByOrgEmailId(tenantRegistrationDto.getOrgEmailId());
        if (tenantRegistrationDto.getOrgLogoFile().getBytes().length == 0
                || tenantRegistrationDto.getOrgSupportingDocFile().getBytes().length == 0) {
            return "file_not_present";
        }
        if (check.isPresent()) {
            System.out.println("Inside Present");
            return "Already Exit";
        }
        TenantDetails tenantDetails = new TenantDetails();
        tenantDetails.setOrgName(tenantRegistrationDto.getOrgName());
        tenantDetails.setOrgEmailId(tenantRegistrationDto.getOrgEmailId());
        tenantDetails.setOrgContactNumber(tenantRegistrationDto.getOrgContactNo());
        tenantDetails.setOrgNodalOfficerName(tenantRegistrationDto.getNodalOfficerName());
        tenantDetails.setOrgNodalOfficerMobileNo(tenantRegistrationDto.getNodalOfficerContactNo());
        tenantDetails.setOrgNodalOfficerEmail(tenantRegistrationDto.getNodalOfficerEmailId());
        tenantDetails.setOrgAddress(tenantRegistrationDto.getOrgAddress());
        tenantDetails.setOrgWebsiteUrl(tenantRegistrationDto.getOrgWebsiteUrl());
        tenantDetails.setOrgCategory(tenantRegistrationDto.getOrgCategory());
        tenantDetails.setOrgType(tenantRegistrationDto.getOrgType());
        tenantDetails.setApprovalStatus("pending");
        ActivationService activationService = new ActivationService();
        String acttoken = activationService.generateActivationCode(tenantRegistrationDto.getOrgEmailId());
        tenantDetails.setActivationToken(acttoken);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        tenantDetails.setRegDate(timestamp);
        tenantDetails.setApprovalDate(timestamp);
        tenantDetails.setLastModifiedDate(timestamp);

        TenantDetails tenantDetails2 = tenantDetailRepository.save(tenantDetails);

        String path = filePath + File.separator + tenantDetails2.getRegNo();
        MultipartFile file = tenantRegistrationDto.getOrgLogoFile();
        String orgFileLogoName = "orgLogo_";
        Path orgPath = fileUpload(file, path, orgFileLogoName);

        MultipartFile orgSupportFile = tenantRegistrationDto.getOrgSupportingDocFile();
        String orgSupportFileName = "orgSupportFile_";
        Path orgSupportPath = fileUpload(orgSupportFile, path, orgSupportFileName);

        if (orgSupportFile == null && orgPath == null) {
            System.out.println("inside If");
            tenantDetailRepository.deleteById(tenantDetails2.getRegNo());
            return "FAIL";
        } else {
            tenantDetails2.setOrgLogoPath(orgPath.toString());
            tenantDetails2.setSupportingDocPath(orgSupportPath.toString());
            tenantDetailRepository.save(tenantDetails2);
        }

        String formattedtext = MessageFormat.format(bodytext1, tenantRegistrationDto.getOrgName(),
                acttoken);
        try {
            eMail.sendEmail(tenantRegistrationDto.getOrgEmailId(), subject1, formattedtext);
            // eMail.sendEmail(tenantRegistrationDto.getNodalOfficerEmailId(), subject1, formattedtext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    public String deleteOrganisation(String tenantId) {
        tenantDetailRepository.deleteById(Integer.parseInt(tenantId));
        return "success";
    }

    public String verifyEMail(String token) {
        Optional<List<TenantDetails>> tdOptional = tenantDetailRepository.findByActivationToken(token);
        if (tdOptional.isPresent()) {
            TenantDetails td = tdOptional.get().get(0);
            td.setActivationToken("E-Mail Verified".toLowerCase().replace(" ", "_"));
            tenantDetailRepository.save(td);
            String formattedtext = MessageFormat.format(bodytext2, tdOptional.get().get(0).getOrgName());
            try {
                eMail.sendEmail(tdOptional.get().get(0).getOrgName(), subject2, formattedtext);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "success";
        }
        return "fail";
    }

    public String updateRegisteredOrganisation(TenantRegistrationDto tenantRegistrationDto) {

        TenantDetails tenantDetails = tenantDetailRepository.findById(tenantRegistrationDto.getRegNo()).get();

        tenantDetails.setOrgName(tenantRegistrationDto.getOrgName());
        // tenantDetails.setOrgEmailId(tenantRegistrationDto.getOrgEmailId());
        tenantDetails.setOrgContactNumber(tenantRegistrationDto.getOrgContactNo());
        tenantDetails.setOrgNodalOfficerName(tenantRegistrationDto.getNodalOfficerName());
        tenantDetails.setOrgNodalOfficerMobileNo(tenantRegistrationDto.getNodalOfficerContactNo());
        tenantDetails.setOrgNodalOfficerEmail(tenantRegistrationDto.getNodalOfficerEmailId());
        tenantDetails.setOrgAddress(tenantRegistrationDto.getOrgAddress());
        tenantDetails.setOrgWebsiteUrl(tenantRegistrationDto.getOrgWebsiteUrl());
        tenantDetails.setOrgCategory(tenantRegistrationDto.getOrgCategory());
        tenantDetails.setOrgType(tenantRegistrationDto.getOrgType());
        // tenantDetails.setApprovalStatus("pending");
        String path = filePath + File.separator + tenantRegistrationDto.getRegNo();
        System.out.println("tenantRegistrationDto.getOrgLogoFile()  = " + tenantRegistrationDto.getOrgLogoFile());
        if (tenantRegistrationDto.getOrgLogoFile() != null) {
            String checkFile = deleteLogo(tenantRegistrationDto.getRegNo());
            if (checkFile.equals("SUCCESS") || checkFile.equals("FILE_NOT_EXIT_EMPTY_DB")) {
                MultipartFile file = tenantRegistrationDto.getOrgLogoFile();
                String orgFileLogoName = "orgLogo_";
                Path orgPath = fileUpload(file, path, orgFileLogoName);
                tenantDetails.setOrgLogoPath(orgPath.toString());
            }
        }
        if (tenantRegistrationDto.getOrgSupportingDocFile() != null) {
            String checkFile = deleteSupportDoc(tenantRegistrationDto.getRegNo());
            System.out.println("doc Check File  = " + checkFile);
            if (checkFile.equals("SUCCESS") || checkFile.equals("SUPPORT_FILE_NOT_EXIT_EMPTY_DB")) {
                System.out.println("inside DOC File ");
                MultipartFile orgSupportFile = tenantRegistrationDto.getOrgSupportingDocFile();
                String orgSupportFileName = "orgSupportFile_";
                Path orgSupportPath = fileUpload(orgSupportFile, path, orgSupportFileName);
                tenantDetails.setSupportingDocPath(orgSupportPath.toString());
            }
        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        tenantDetails.setLastModifiedDate(timestamp);
        tenantDetailRepository.save(tenantDetails);
        return "success";
    }

    public String deleteRegisteredOrganisation(String tenantId) {
        System.out.println("tenantId " + tenantId);
        Optional<TenantDetails> td = tenantDetailRepository.findById(Integer.parseInt(tenantId));
        Optional<TenantMaster> tm = tenantMasterRepo.findByRegNo(td.get().getRegNo());
        tenantDetailRepository.delete(td.get());
        tenantMasterRepo.delete(tm.get());
        RealmsDtos realmsDtos = new RealmsDtos();
        realmsDtos.setRealmsName(tm.get().getRealmId());
        keycloakAdminClientService.removeRealm(realmsDtos);
        return "success";
    }

    // public List<TenantDetails> findListByStatus(String status) {
    // if (status.equals("pending")) {
    // Optional<List<TenantDetails>> tdOptional =
    // tenantDetailRepository.findByApprovalStatusAndActivationToken(
    // "pending",
    // "E-Mail Verified");
    // return tdOptional.get();
    // } else if (status.equals("approve")) {
    // Optional<List<TenantDetails>> tdOptional =
    // tenantDetailRepository.findByApprovalStatusAndActivationToken(
    // "approve",
    // "E-Mail Verified");

    // return tdOptional.get();
    // } else if (status.equals("reject")) {
    // Optional<List<TenantDetails>> tdOptional =
    // tenantDetailRepository.findByApprovalStatusAndActivationToken(
    // "reject",
    // "E-Mail Verified");
    // return tdOptional.get();
    // } else {
    // Optional<List<TenantDetails>> tdOptional =
    // tenantDetailRepository.findByActivationToken("E-Mail Verified");
    // return tdOptional.get();
    // }
    // }

    public TenantRegistrationDto setTenantRegistrationDto(TenantDetails tenantDetails) {
        TenantRegistrationDto tenantDetailApproveDto = new TenantRegistrationDto();
        tenantDetailApproveDto.setRegNo(tenantDetails.getRegNo() != 0 ? tenantDetails.getRegNo() : 0);
        tenantDetailApproveDto.setOrgName(tenantDetails.getOrgName() != null ? tenantDetails.getOrgName() : null);
        tenantDetailApproveDto.setOrgType(tenantDetails.getOrgType() != null ? tenantDetails.getOrgType() : null);
        tenantDetailApproveDto
                .setOrgCategory(tenantDetails.getOrgCategory() != null ? tenantDetails.getOrgCategory() : null);
        tenantDetailApproveDto
                .setOrgEmailId(tenantDetails.getOrgEmailId() != null ? tenantDetails.getOrgEmailId() : null);
        tenantDetailApproveDto.setNodalOfficerName(
                tenantDetails.getOrgNodalOfficerName() != null ? tenantDetails.getOrgNodalOfficerName() : null);
        tenantDetailApproveDto.setNodalOfficerEmailId(
                tenantDetails.getOrgNodalOfficerEmail() != null ? tenantDetails.getOrgNodalOfficerEmail() : null);
        tenantDetailApproveDto.setNodalOfficerContactNo(
                tenantDetails.getOrgNodalOfficerMobileNo() != null ? tenantDetails.getOrgNodalOfficerMobileNo() : null);
        tenantDetailApproveDto.setOrgAddress(
                tenantDetails.getOrgAddress() != null ? tenantDetails.getOrgAddress() : null);
        tenantDetailApproveDto.setOrgWebsiteUrl(
                tenantDetails.getOrgWebsiteUrl() != null ? tenantDetails.getOrgWebsiteUrl() : null);

        return tenantDetailApproveDto;
    }

    public List<?> findListByStatus(String status) {
        if (status.equals("pending")) {
            Optional<List<TenantDetails>> tdOptional = tenantDetailRepository.findByApprovalStatusAndActivationToken(
                    "pending",
                    "E-Mail Verified".toLowerCase().replace(" ", "_"));
            if (tdOptional.get().size() != 0) {
                List<TenantRegistrationDto> tenantRegistrationDtos = Arrays
                        .asList(setTenantRegistrationDto(tdOptional.get().get(0)));
                return tenantRegistrationDtos;
            }
            return tdOptional.get();
        } else if (status.equals("approve")) {
            Optional<List<TenantDetails>> tdOptional = tenantDetailRepository.findByApprovalStatusAndActivationToken(
                    "approve",
                    "E-Mail Verified".toLowerCase().replace(" ", "_"));
            if (tdOptional.get().size() != 0) {
                List<TenantRegistrationDto> tenantRegistrationDtos = Arrays
                        .asList(setTenantRegistrationDto(tdOptional.get().get(0)));
                return tenantRegistrationDtos;
            }
            return tdOptional.get();
        } else if (status.equals("reject")) {
            Optional<List<TenantDetails>> tdOptional = tenantDetailRepository.findByApprovalStatusAndActivationToken(
                    "reject",
                    "E-Mail Verified".toLowerCase().replace(" ", "_"));
            if (tdOptional.get().size() != 0) {
                List<TenantRegistrationDto> tenantRegistrationDtos = Arrays
                        .asList(setTenantRegistrationDto(tdOptional.get().get(0)));
                return tenantRegistrationDtos;
            }
            return tdOptional.get();
        } else {
            Optional<List<TenantDetails>> tdOptional = tenantDetailRepository.findByActivationToken(
                    "E-Mail Verified".toLowerCase().replace(" ", "_").toLowerCase().replace(" ", "_"));
            return tdOptional.get();
        }
    }

    public List<?> homePageOrgList() {
        Optional<List<TenantDetails>> tdOptional = tenantDetailRepository.findByApprovalStatusAndActivationToken(
                "approve",
                "E-Mail Verified".toLowerCase().replace(" ", "_"));
        if (tdOptional.get().size() == 0) {
            return tdOptional.get();
        }
        List<TenantDetailApproveDto> tdal = new ArrayList<>();
        for (TenantDetails tenantDetails : tdOptional.get()) {
            Optional<TenantMaster> otm = tenantMasterRepo.findByRegNo(tenantDetails.getRegNo());
            TenantDetailApproveDto tenantDetailApproveDto = new TenantDetailApproveDto();
            tenantDetailApproveDto.setRegNo(tenantDetails.getRegNo());
            tenantDetailApproveDto.setOrgRealm(otm.get().getRealmId());
            tenantDetailApproveDto.setOrgClient(otm.get().getClientId());
            tenantDetailApproveDto.setLogo(getLogo(tenantDetails.getRegNo()));
            tdal.add(tenantDetailApproveDto);
        }
        return tdal;
    }

    public byte[] getLogo(int id) {
        Optional<TenantDetails> td = tenantDetailRepository.findById(id);
        if (td.isPresent()) {
            String logoPath = td.get().getOrgLogoPath();
            byte[] logoFile = getFile(logoPath);
            if (logoFile != null) {
                return logoFile;
            }
        }
        return null;
    }

    public byte[] getSupportingDoc(int id) {
        Optional<TenantDetails> td = tenantDetailRepository.findById(id);
        if (td.isPresent()) {
            String supportingFile = td.get().getSupportingDocPath();
            byte[] logoFile = getFile(supportingFile);
            if (logoFile != null) {
                return logoFile;
            }
        }
        return null;
    }

    public String deleteLogo(int id) {
        TenantDetails td = tenantDetailRepository.findById(id).get();
        String logoPath = td.getOrgLogoPath();
        boolean deleteCondition = deleteFile(logoPath);
        if (deleteCondition) {
            td.setOrgLogoPath("");
            tenantDetailRepository.save(td);
            return "SUCCESS";
        } else {
            td.setOrgLogoPath("");
            tenantDetailRepository.save(td);
            return "FILE_NOT_EXIT_EMPTY_DB";
        }
    }

    public String deleteSupportDoc(int id) {
        TenantDetails td = tenantDetailRepository.findById(id).get();
        String supportingDocPath = td.getSupportingDocPath();
        boolean deleteCondition = deleteFile(supportingDocPath);
        if (deleteCondition) {
            td.setSupportingDocPath("");
            tenantDetailRepository.save(td);
            return "SUCCESS";
        } else {
            td.setSupportingDocPath("");
            tenantDetailRepository.save(td);
            return "SUPPORT_FILE_NOT_EXIT_EMPTY_DB";
        }
    }

    public String changeStatusByAdmin(int id, TenantMasterDto tenantMasterDto) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormateString);
            Optional<TenantDetails> otd = tenantDetailRepository.findByRegNo(id);
            if (otd.isPresent()) {
                TenantDetails td2 = otd.get();
                if (tenantMasterDto.getStatus().equals("approve")) {
                    String approveStatus = approveByAdmin(td2);
                    if (approveStatus.equals("success")) {
                        td2.setApprovalStatus("approve");
                        tenantDetailRepository.save(td2);
                        TenantMaster tm = new TenantMaster();
                        tm.setRealmId(MD5Hashing.generateMD5(String.valueOf(otd.get().getRegNo())));
                        tm.setClientId("client_" + otd.get().getRegNo());
                        tm.setLastModifiedBy(tenantMasterDto.getLastModifiedBy());
                        tm.setLastModifiedDate(
                                new Timestamp(System.currentTimeMillis()));
                        tm.setLoginUrl(otd.get().getOrgWebsiteUrl());
                        // tm.setPasswordChangeUrl("");
                        tm.setStatus("approve");
                        tm.setValidFrom(new Timestamp(dateFormat.parse(tenantMasterDto.getValidFrom()).getTime()));
                        tm.setValidUpto(new Timestamp(dateFormat.parse(tenantMasterDto.getValidUpto()).getTime()));
                        tm.setRegNo(id);
                        tenantMasterRepo.save(tm);
                        return "success";
                    }
                    return "fail";
                } else if (tenantMasterDto.getStatus().equals("reject")) {
                    td2.setApprovalStatus("reject");
                    tenantDetailRepository.save(td2);
                    return "success";
                }
            }
            return "fail";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error on Approval";
            // TODO: handle exception
        }
    }

    // public String setTenantMaster(TenantDetails tenantDetails, String url,
    // Timestamp validFrom, Timestamp validUpto) {
    // TenantMaster tm = new TenantMaster();
    // tm.setRealmId(tenantDetails.getOrgName().toLowerCase().replace(" ", "_"));
    // tm.setClientId("reactclient_" +
    // tenantDetails.getOrgName().toLowerCase().replace(" ", "_"));
    // tm.setRegNo(tenantDetails.getRegNo());
    // tm.setValidFrom(validFrom);
    // tm.setValidUpto(validUpto);
    // tm.setLoginUrl(url);
    // tm.setPasswordChangeUrl(url);
    // tm.setStatus("active");
    // Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    // tm.setLastModifiedDate(timestamp);
    // tm.setLastModifiedBy(tenantDetails.getLastModifiedBy());
    // tenantMasterRepo.save(tm);
    // return "success";
    // }

    // public String approveByAdmin(TenantDetails tenantDetails) {

    // String clientCreateStatus =
    // keycloakAdminClientService.createNewClient(tenantDetails);
    // String adminCreateStatus =
    // keycloakAdminClientService.addNewAdmin(tenantDetails);
    // // String clientLoginUrl =
    // //
    // keycloakAdminClientService.getClientLoginUrl(tenantDetails.getOrgName().toLowerCase());

    // if (clientCreateStatus.equals("CLIENT_CREATED") &&
    // adminCreateStatus.equals("NEW_ADMIN_CREATED")) {
    // TenantMaster tm = new TenantMaster();
    // tm.setRegNo(tm.getRegNo());
    // tm.setKeycloakId(tenantDetails.getOrgName().toLowerCase());
    // // tm.setLoginUrl(clientLoginUrl);
    // Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    // tm.setLastModifiedBy(tenantDetails.getOrgNodalOfficerName());
    // tm.setLastModifiedDate(timestamp);
    // tm.setStatus("active");
    // tenantMasterRepo.save(tm);

    // return "SUCCESS";
    // }
    // return "FAIL";
    // }

    public String approveByAdmin(TenantDetails tenantDetails) {

        // Setting the all the fields of the realmDtos
        RealmsDtos realmsDtos = new RealmsDtos();

        realmsDtos.setRealmsId(MD5Hashing.generateMD5(String.valueOf(tenantDetails.getRegNo())));
        realmsDtos.setRealmsName(tenantDetails.getOrgName());
        realmsDtos.setEmail(tenantDetails.getOrgEmailId());
        realmsDtos.setClientId(
                "client_" + tenantDetails.getRegNo());
        List<String> clientRole = Arrays.asList("learner", "admin", "instructor");
        realmsDtos.setClientRole(clientRole);
        realmsDtos.setFirstName(tenantDetails.getOrgNodalOfficerName());

        // First creating the Realm
        String realmCreationStation = keycloakAdminClientService.createNewRealm(realmsDtos);
        System.out.println(realmCreationStation);

        // Second Creating the Client
        String clientCreationStatus = keycloakAdminClientService.createClient(realmsDtos);
        System.out.println(clientCreationStatus);

        // Assigning the Role in client
        String clientRoleCreationStatus = keycloakAdminClientService.createClientRole(realmsDtos);
        System.out.println(clientRoleCreationStatus);

        // Creating the admin User and assigning the role - learner , admin
        if (realmCreationStation.equals("Success") && clientCreationStatus.equals("Success")
                && clientRoleCreationStatus.equals("Success")) {
            String userCreation = keycloakAdminClientService.addNewAdmin(realmsDtos);
            return userCreation.equals("NEW_ADMIN_CREATED") ? "success" : "fail";
        } else {
            // if any of the above fail revert back all
            keycloakAdminClientService.removeRealm(realmsDtos);
            return "fail";
        }

    }

    public boolean deleteFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
            return true;
        }
        return false;
    }

    public Path fileUpload(MultipartFile file, String path, String name) {
        try {
            Date date = new Date();
            File desFile = new File(path);
            boolean flag = desFile.mkdir();
            if (flag) {
                System.out.println("Directory Created SuccessFully");
            } else {
                System.out.println("Directory Creation Fail");
            }
            byte[] orgFile = file.getBytes();
            Path fileSavePath = Paths
                    .get(path + File.separator + name + date.getTime() + "."
                            + FilenameUtils.getExtension(file.getOriginalFilename()));

            Path filePath = Files.write(fileSavePath, orgFile);
            System.out.println("This is File Path - " + filePath);
            return filePath;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] getFile(String location) {
        File file = new File(location);
        if (!file.exists()) {
            return null;
        }
        try {
            byte[] fileFetch = Files.readAllBytes(new File(location).toPath());
            return fileFetch;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
