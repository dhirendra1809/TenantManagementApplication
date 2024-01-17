import axios from "axios";
import { TenantService } from "./ServicesPort";

const TenantManagementService = TenantService;



class services {

    //  Organisation Registration Related Service call

    registerOrganisation(data) {
        return axios.post(TenantManagementService + `/organisation/registerOrg`, data)
    }

    getAllClientList() {
        return axios.get(TenantManagementService + `/organisation/getalllist`)
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


