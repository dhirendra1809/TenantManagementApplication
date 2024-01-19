import axios from "axios";
import { TenantService } from "./ServicesPort";

const TenantManagementService = TenantService;



class services {

    //  Organisation Registration Related Service call

    getAllApprovedClientListForHomePage(data) {
        return axios.get(TenantManagementService + `/organisation/getapprovelistforHomePage`)
    }

    registerOrganisation(data) {
        return axios.post(TenantManagementService + `/organisation/registerOrg`, data)
    }

    getAllClientList() {
        return axios.get(TenantManagementService + `/organisation/getalllist`)
    }

    approveClient(orgId) {
        return axios.post(TenantManagementService + `/organisation/approve/${orgId}`)
    }

    rejectClient(orgId) {
        return axios.post(TenantManagementService + `/organisation/reject/${orgId}`)
    }

    // Organisation Category Related Service call

    getAllCategory() {
        return axios.get(TenantManagementService + `/orgcategory/getAllCategory`);
    }

    // Organisation Type Related Service call

    getAllOrgType() {
        return axios.get(TenantManagementService + `/type/getAllOrgType`);
    }

}

export default new services();


