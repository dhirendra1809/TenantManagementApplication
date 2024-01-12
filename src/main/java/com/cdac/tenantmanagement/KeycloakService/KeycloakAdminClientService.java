package com.cdac.tenantmanagement.KeycloakService;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.apache.catalina.Realm;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.cdac.tenantmanagement.Dto.RealmsDtos;
import com.cdac.tenantmanagement.Model.TenantDetails;
import com.cdac.tenantmanagement.Utils.EmailSender;
import com.cdac.tenantmanagement.Utils.RandomPasswordGenerator;

import org.keycloak.representations.idm.*;

@Service
@PropertySource("classpath:email.properties")
@PropertySource("classpath:application.properties")
public class KeycloakAdminClientService {

    @Autowired
    private EmailSender eMail;

    Keycloak keycloak;

    @Value("${mail.subject3}")
    private String subject3;
    @Value("${mail.username3}")
    private String username3;
    @Value("${mail.body.registartion3}")
    private String bodytext3;

    @Value("${keycloak.auth-server-url}")
    String serverUrl;

    @Value("${keycloak.realm}")
    String realm;

    @Value("${keycloak.resource}")
    String clientId;

    @Value("${keycloak.credentials.secret}")
    String clientSecret;

    @Autowired
    RandomPasswordGenerator randomPasswordGenerator;

    public void buildKeycloak() {
        // keycloak = KeycloakBuilder.builder()
        // .serverUrl(serverUrl)
        // .realm(realm)
        // .clientId(clientId)
        // .grantType(OAuth2Constants.PASSWORD)
        // .username("clientadmin")
        // .password("Cdac@123$")
        // .build();

        keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm("master")
                .clientId("admin-cli")
                .grantType(OAuth2Constants.PASSWORD)
                .username("superadmin")
                .password("Cd@c@123$")
                .build();
    }

    public String createNewClient(TenantDetails tenantDetails) {
        // buildKeycloak(); // Building the Keycloak Connection

        try {
            // Adding the organisation website url in the arrayList
            List<String> url = new ArrayList<>();
            url.add(tenantDetails.getOrgWebsiteUrl() + "/*");

            /*
             * Creating the ClientRepresentation which is of type ClientResource with new
             * Object
             */
            org.keycloak.representations.idm.ClientRepresentation addNewClient = new org.keycloak.representations.idm.ClientRepresentation();
            addNewClient.setName(tenantDetails.getOrgName()); // Adding the Client Name
            addNewClient.setClientId(tenantDetails.getOrgName().toLowerCase()); // setting the Client ID
            addNewClient.setRedirectUris(url); // Adding the Organisation Url in the new Client
            addNewClient.setBaseUrl(tenantDetails.getOrgWebsiteUrl());
            addNewClient.setRootUrl(tenantDetails.getOrgWebsiteUrl());
            addNewClient.setPublicClient(true);
            addNewClient.setDirectAccessGrantsEnabled(true);
            addNewClient.setImplicitFlowEnabled(true);

            Response rs = keycloak.realm(realm).clients().create(addNewClient); // Creating the New Client

            System.out.println(rs.getStatus());
            if (rs.getStatus() == Response.Status.CREATED.getStatusCode()) {

                /*
                 * variable of type List and mention all
                 * the Roles in the Variable
                 */
                List<String> roles = Arrays.asList("admin", "learner", "instructor");
                // iterating the list of role and adding in the Client Id or in the Client
                for (String role : roles) {
                    RoleRepresentation addRoles = new RoleRepresentation(); // Creating the new Object of
                                                                            // RoleRepresentation
                    addRoles.setName(role); // setting the Role in RoleRepresentation
                    addRoles.setClientRole(true);
                    addRoles.setContainerId(clientId);
                    /*
                     * Below Line First Accessing the realms then Finding the Client by ClientId
                     * it will return the list of Client of type ClientRepresentation
                     * After that Each Time getting the details to access the Role and
                     * Create it
                     */
                    keycloak.realm(realm).clients().findByClientId(tenantDetails.getOrgName().toLowerCase())
                            .forEach(client -> keycloak.realm(realm).clients().get(client.getId())
                                    .roles().create(addRoles));

                }
                keycloak.close();
                return "CLIENT_CREATED";
            } else {
                keycloak.close();
                return "ERROR_IN_CLIENT_CREATE";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Exception Occured";
            // TODO: handle exception
        }

    }

    public String createNewRealm(RealmsDtos realmsDtos) {
        buildKeycloak();

        try {

            boolean checkRealmExit = keycloak.realms().findAll().stream().anyMatch(
                    realm -> realm.getRealm().equals(realmsDtos.getRealmsId().toLowerCase().replace(" ", "_")));
            if (checkRealmExit) {
                return "Already Exits";
            }
            RealmRepresentation realmRepresentation = new RealmRepresentation();
            // realmRepresentation.setRealm(realmsDtos.getRealmsId().toLowerCase().replace("
            // ", "_"));
            realmRepresentation.setDisplayName(realmsDtos.getRealmsId().toLowerCase().replace(" ", "_"));
            realmRepresentation.setRealm(realmRepresentation.getDisplayName());
            realmRepresentation.setEnabled(true);
            keycloak.realms().create(realmRepresentation);
            String updateStatus = updateRealm(realmsDtos);
            System.out.println(updateStatus);
            return "Success";

        } catch (Exception e) {
            e.printStackTrace();
            return "Exception Occured";
            // TODO: handle exception
        }
    }

    public String updateRealm(RealmsDtos realmsDtos) {
        buildKeycloak();

        try {
            RealmRepresentation realmRepresentation = keycloak.realms().realm(realmsDtos.getRealmsId())
                    .toRepresentation();
            realmRepresentation.setDisplayName(realmsDtos.getRealmsName());
            keycloak.realms().realm(realmsDtos.getRealmsId()).update(realmRepresentation);

            // ClientRepresentation clientRepresentation =
            // keycloak.realms().realm(realmsDtos.getRealmsId()).clients()
            // .findByClientId(realmsDtos.getClientId()).get(0);
            // clientRepresentation.setRedirectUris(Arrays.asList());
            return "Success";

        } catch (Exception e) {
            e.printStackTrace();
            return "Exception Occured";
            // TODO: handle exception
        }
    }

    public String removeRealm(RealmsDtos realmsDtos) {
        buildKeycloak();

        try {

            try {
                keycloak.realms().realm(realmsDtos.getRealmsId().toLowerCase().replace(" ",
                        "_")).remove();
            } catch (NotFoundException notFoundException) {
                System.out.println(notFoundException.getResponse().getStatus());
            }
            return "Success";

        } catch (Exception e) {
            e.printStackTrace();
            return "Exception Occured";
            // TODO: handle exception
        }
    }

    public String creatingRealmLevelAdmin(RealmsDtos realmsDtos) {
        buildKeycloak();

        try {

            String tempPassword = randomPasswordGenerator.generateRandomPassword(8);
            System.out.println(tempPassword);
            UserRepresentation userRepresentation = userRepresentation(realmsDtos);
            CredentialRepresentation credentialRepresentation = credentialRepresentation(tempPassword);
            userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));
            Response response = keycloak.realm("master").users().create(userRepresentation);
            System.out.println(response.getStatus());
            if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
                ClientRepresentation clientRepresentation = keycloak.realm("master").clients()
                        .findByClientId(realmsDtos.getClientId().toLowerCase().replace(" ", "_") + "-realm").get(0);
                UserRepresentation userRepresentation2 = keycloak.realm("master").users()
                        .search(realmsDtos.getEmail()).get(0);
                System.out.println(realmsDtos.getRealmsId().toLowerCase().replace(" ", "_") + "-realm");
                List<RoleRepresentation> realmsRole = keycloak.realm("master").clients()
                        .get(clientRepresentation.getId()).roles().list();
                keycloak.realm("master").users().get(userRepresentation2.getId()).roles()
                        .clientLevel(clientRepresentation.getId()).add(realmsRole);
                return "Success";
            }
            return "Fail";
        } catch (Exception e) {
            e.printStackTrace();
            return "Exception Occured";
            // TODO: handle exception
        }
    }

    public String createRealmRole(RealmsDtos realmsDtos) {
        buildKeycloak();

        try {
            for (String realmRoles : realmsDtos.getRealmRole()) {
                RoleRepresentation roleRepresentation = new RoleRepresentation();
                roleRepresentation.setName(realmRoles.toLowerCase());
                roleRepresentation.setClientRole(false);
                roleRepresentation.setComposite(false);
                roleRepresentation.setDescription("Realms Level Role Assigning");
                keycloak.realm(realmsDtos.getRealmsId().toLowerCase().replace(" ", "_")).roles()
                        .create(roleRepresentation);

            }
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Exception Occured";
        }
    }

    public Object listRealmRole(String realm) {
        buildKeycloak();
        try {

            List<RoleRepresentation> customList = new ArrayList<>();

            keycloak.realm(realm.toLowerCase().replace(" ", "_")).roles()
                    .list().forEach((role) -> {
                        if (!role.getDescription().equals("${role_uma_authorization}")
                                && !role.getDescription().equals("${role_default-roles}")
                                && !role.getDescription().equals("${role_offline-access}")) {
                            customList.add(role);
                        }
                    });
            return customList;
        } catch (Exception e) {
            e.printStackTrace();
            return "Exception Occured";
        }
    }

    public String removeRealmRole(RealmsDtos realmsDtos) {
        buildKeycloak();

        try {

            for (String realmRoles : realmsDtos.getRealmRole()) {
                keycloak.realm(realmsDtos.getRealmsId().toLowerCase().replace(" ", "_")).roles()
                        .deleteRole(realmRoles.toLowerCase());

            }
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Exception Occured";
        }
    }

    public String createClient(RealmsDtos realmsDtos) {
        buildKeycloak();

        ClientRepresentation clientRepresentation = new ClientRepresentation();
        clientRepresentation.setClientId(realmsDtos.getClientId().toLowerCase().replace(" ", "_"));
        clientRepresentation.setBearerOnly(true);
        clientRepresentation.setStandardFlowEnabled(true);
        clientRepresentation.setImplicitFlowEnabled(true);
        clientRepresentation.setPublicClient(true);
        clientRepresentation.setDirectAccessGrantsEnabled(true);
        clientRepresentation.setDirectGrantsOnly(true);
        Response createClientStatus = keycloak.realm(realmsDtos.getRealmsId().toLowerCase().replace(" ", "_")).clients()
                .create(clientRepresentation);
        if (createClientStatus.getStatus() == Response.Status.CREATED.getStatusCode()) {
            return "Success";
        } else {
            return "fail";
        }

    }

    public String deleteClient(RealmsDtos realmsDtos) {
        buildKeycloak();

        keycloak.realm(realmsDtos.getRealmsId().toLowerCase().replace(" ", "_")).clients()
                .get(keycloak.realm(realmsDtos.getRealmsId().toLowerCase().replace(" ", "_")).clients()
                        .findByClientId(realmsDtos.getClientId().toLowerCase()).get(0).getId())
                .remove();

        return "Success";
    }

    public String createClientRole(RealmsDtos realmsDtos) {
        buildKeycloak();

        try {

            for (String clientRoles : realmsDtos.getClientRole()) {
                RoleRepresentation roleRepresentation = new RoleRepresentation();
                roleRepresentation.setName(clientRoles.toLowerCase());
                roleRepresentation.setClientRole(false);
                roleRepresentation.setComposite(false);
                roleRepresentation.setDescription("Client Level Role Assigning");
                keycloak.realm(realmsDtos.getRealmsId().toLowerCase().replace(" ", "_")).clients()
                        .get(keycloak.realm(realmsDtos.getRealmsId().toLowerCase().replace(" ", "_")).clients()
                                .findByClientId(realmsDtos.getClientId().toLowerCase().replace(" ", "_")).get(0)
                                .getId())
                        .roles()
                        .create(roleRepresentation);
            }
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Exception Occured";
        }
    }

    public String removeClientRole(RealmsDtos realmsDtos) {
        buildKeycloak();

        try {
            for (String clientRoles : realmsDtos.getClientRole()) {
                RoleRepresentation roleRepresentation = new RoleRepresentation();
                roleRepresentation.setName(clientRoles.toLowerCase());
                roleRepresentation.setClientRole(false);
                roleRepresentation.setComposite(false);
                roleRepresentation.setDescription("Client Level Role Assigning");
                keycloak.realm(realmsDtos.getRealmsId().toLowerCase().replace(" ", "_")).clients()
                        .get(keycloak.realm(realmsDtos.getRealmsId().toLowerCase().replace(" ", "_")).clients()
                                .findByClientId(realmsDtos.getClientId().toLowerCase().replace(" ", "_")).get(0)
                                .getId())
                        .roles().deleteRole(clientRoles.toLowerCase());
            }
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Exception Occured";
        }
    }

    public String addNewAdmin(RealmsDtos realmsDtos) {

        buildKeycloak();

        try {
            String pass = randomPasswordGenerator.generateRandomPassword(8);

            CredentialRepresentation credentialRepresentation = credentialRepresentation(pass);

            UserRepresentation addNewAdmin = userRepresentation(realmsDtos);

            addNewAdmin.setCredentials(Collections.singletonList(credentialRepresentation));

            Response response = keycloak.realm(realmsDtos.getRealmsId().toLowerCase().replace(" ", "_")).users()
                    .create(addNewAdmin);

            if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
                String status = assignAdminRole(realmsDtos.getRealmsId(), realmsDtos.getEmail(),
                        realmsDtos.getClientId());
                if (status.equals("SUCCESS")) {
                    String formattedtext = MessageFormat.format(bodytext3, realmsDtos.getRealmsId(), pass);
                    try {
                        eMail.sendEmail(realmsDtos.getEmail(), subject3, formattedtext);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return "NEW_ADMIN_CREATED";
        } catch (Exception e) {
            e.printStackTrace();
            return "NEW_ADMIN_CREATED_FAIL";
        }
    }

    public UserRepresentation userRepresentation(RealmsDtos realmsDtos) {
        UserRepresentation addNewAdmin = new UserRepresentation();
        addNewAdmin.setUsername(realmsDtos.getEmail());
        addNewAdmin.setEmail(realmsDtos.getEmail());
        addNewAdmin.setFirstName(realmsDtos.getFirstName());
        addNewAdmin.setEnabled(true);
        return addNewAdmin;
    }

    public CredentialRepresentation credentialRepresentation(String pass) {
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(pass);
        credentialRepresentation.setTemporary(true);
        return credentialRepresentation;
    }

    public String disableEnableRealm(RealmsDtos realmsDtos) {
        buildKeycloak();

        RealmRepresentation realmRepresentation = keycloak.realms()
                .realm(realmsDtos.getRealmsId().toLowerCase().replace(" ", "_")).toRepresentation();
        System.out.println(realmRepresentation.isEnabled());
        boolean condition = realmRepresentation.isEnabled() ? false : true;
        realmRepresentation.setEnabled(condition);
        RealmResource realmResource = keycloak.realm(realmsDtos.getRealmsId().toLowerCase().replace(" ", "_"));
        realmResource.update(realmRepresentation);

        return "Success";
    }

    public String assignAdminRole(String realm, String username, String clientId) {
        buildKeycloak();
        try {
            RealmResource realmResource = keycloak.realm(realm.toLowerCase().replace(" ", "_"));

            ClientRepresentation clientRepresentation = realmResource.clients().findByClientId(clientId).get(0);

            UserRepresentation userRepresentation = realmResource.users().search(username).get(0);

            UserResource getUser = keycloak.realm(realm.toLowerCase().replace(" ", "_")).users()
                    .get(userRepresentation.getId());

            RoleRepresentation learner_clientRole = keycloak.realm(realm.toLowerCase().replace(" ", "_")).clients()
                    .get(clientRepresentation.getId())
                    .roles().get("learner").toRepresentation();
            RoleRepresentation admin_clientRole = keycloak.realm(realm.toLowerCase().replace(" ", "_")).clients()
                    .get(clientRepresentation.getId())
                    .roles()
                    .get("admin").toRepresentation();

            List<RoleRepresentation> adminRoles = Arrays.asList(learner_clientRole, admin_clientRole);

            getUser.roles().clientLevel(clientRepresentation.getId()).add(adminRoles);

            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            return "FAIL";
        }
    }

}
