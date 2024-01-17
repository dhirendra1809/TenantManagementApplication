import React, { useEffect, useState } from 'react'
import '../Css/SuperAdminComponent.css'
import '../../Pages/RegistrationPage/registrationPage.css'
import Sidebar from '../CommonAdminComponents/Sidebar'
import Navbar from '../CommonAdminComponents/Navbar'
import Footer from '../CommonAdminComponents/Footer'
import services from '../../Services/services'
import { emptyFieldMessage, notSelectMessage, orgAddressValidation, orgAddressValidationMessage, orgContactNoValidation, orgContactNoValidationMessage, orgEmailValidation, orgEmailValidationMessage, orgNameValidation, orgNameValidationMessage, orgWebsiteUrlValidation, orgWebsiteUrlValidationMessage } from '../../Validation/Validation'
import Swal from 'sweetalert2'

export default function RegistrationByAdmin() {

    useEffect(() => {
        getOrgCatagory()
        getOrgType()
    }, [])

    const [getOrganisationCategory, setOrganisationCategory] = useState([])
    const [getOrganisationType, setOrganisationType] = useState([])

    const getOrgCatagory = () => {
        services.getAllCategory().then((resp) => {
            console.log(resp.data)
            if (resp.status === 200) {
                setOrganisationCategory(resp.data);
            }
        }).catch((error) => {

        })
    }

    const getOrgType = () => {
        services.getAllOrgType().then((resp) => {
            if (resp.status === 200) {
                setOrganisationType(resp.data);
            }
        }).catch((error) => {

        })
    }

    const [registrationDetails, setRegistrationDetails] = useState({
        regNo: "",
        orgName: "",
        orgEmailId: "",
        orgContactNo: "",
        orgAddress: "",
        orgWebsiteUrl: "",
        orgType: "",
        orgCategory: "",
        orgLogoFile: null,
        orgSupportingDocFile: null,
        nodalOfficerName: "",
        nodalOfficerEmailId: "",
        nodalOfficerContactNo: "",
    });

    const resetRegistrationDetails = () => {
        setRegistrationDetails((prev) => {
            return {
                ...prev,
                regNo: "",
                orgName: "",
                orgEmailId: "",
                orgContactNo: "",
                orgAddress: "",
                orgWebsiteUrl: "",
                orgType: "",
                orgCategory: "",
                orgLogoFile: null,
                orgSupportingDocFile: null,
                nodalOfficerName: "",
                nodalOfficerEmailId: "",
                nodalOfficerContactNo: "",
            }
        })
    }

    const [registrationDetailsError, setRegistrationDetailsError] = useState({
        regNoError: "",
        orgNameError: "",
        orgEmailIdError: "",
        orgContactNoError: "",
        orgAddressError: "",
        orgWebsiteUrlError: "",
        orgTypeError: "",
        orgCategoryError: "",
        orgLogoFileError: null,
        orgSupportingDocFileError: null,
        nodalOfficerNameError: "",
        nodalOfficerEmailIdError: "",
        nodalOfficerContactNoError: "",
    });

    const resetRegistrationDetailsError = () => {
        setRegistrationDetailsError((prev) => {
            return {
                ...prev,
                regNoError: "",
                orgNameError: "",
                orgEmailIdError: "",
                orgContactNoError: "",
                orgAddressError: "",
                orgWebsiteUrlError: "",
                orgTypeError: "",
                orgCategoryError: "",
                orgLogoFileError: null,
                orgSupportingDocFileError: null,
                nodalOfficerNameError: "",
                nodalOfficerEmailIdError: "",
                nodalOfficerContactNoError: "",
            }
        })
    }



    const validationChecking = () => {

        let orgNameError = ""
        let orgEmailIdError = ""
        let orgContactNoError = ""
        let orgAddressError = ""
        let orgWebsiteUrlError = ""
        let orgTypeError = ""
        let orgCategoryError = ""
        let orgLogoFileError = ""
        let orgSupportingDocFileError = ""
        let nodalOfficerNameError = ""
        let nodalOfficerEmailIdError = ""
        let nodalOfficerContactNoError = ""

        if (registrationDetails.orgName === "") {
            orgNameError = emptyFieldMessage;
        }
        else if (!registrationDetails.orgName.match(orgNameValidation)) {
            orgNameError = orgNameValidationMessage
        }

        if (registrationDetails.orgEmailId === "") {
            orgEmailIdError = emptyFieldMessage;
        }
        else if (!registrationDetails.orgName.match(orgEmailValidation)) {
            orgEmailIdError = orgEmailValidationMessage
        }

        if (registrationDetails.orgContactNo === "") {
            orgContactNoError = emptyFieldMessage;
        }
        else if (!registrationDetails.orgContactNo.match(orgContactNoValidation)) {
            orgContactNoError = orgContactNoValidationMessage
        }

        if (registrationDetails.orgWebsiteUrl === "") {
            orgWebsiteUrlError = emptyFieldMessage;
        }
        else if (!registrationDetails.orgWebsiteUrl.match(orgWebsiteUrlValidation)) {
            orgWebsiteUrlError = orgWebsiteUrlValidationMessage
        }

        if (registrationDetails.orgType === "") {
            orgTypeError = emptyFieldMessage;
        }
        else if (registrationDetails.orgType === "selectType") {
            orgTypeError = notSelectMessage;
        }
        else if (!registrationDetails.orgType.match(orgNameValidation)) {
            orgTypeError = orgNameValidationMessage
        }

        if (registrationDetails.orgCategory === "") {
            orgCategoryError = emptyFieldMessage;
        }
        else if (registrationDetails.orgCategory === "selectType") {
            orgCategoryError = notSelectMessage;
        }
        else if (!registrationDetails.orgCategory.match(orgNameValidation)) {
            orgCategoryError = orgNameValidationMessage
        }

        if (registrationDetails.orgAddress === "") {
            orgAddressError = emptyFieldMessage;
        }
        else if (!registrationDetails.orgAddress.match(orgAddressValidation)) {
            orgAddressError = orgAddressValidationMessage
        }

        if (registrationDetails.orgLogoFile === null) {
            orgLogoFileError = emptyFieldMessage;
        }

        if (registrationDetails.orgSupportingDocFile === null) {
            orgSupportingDocFileError = emptyFieldMessage;
        }

        if (registrationDetails.nodalOfficerName === "") {
            nodalOfficerNameError = emptyFieldMessage;
        }
        else if (!registrationDetails.nodalOfficerName.match(orgNameValidation)) {
            nodalOfficerNameError = orgNameValidationMessage
        }

        if (registrationDetails.nodalOfficerEmailId === "") {
            nodalOfficerEmailIdError = emptyFieldMessage;
        }
        else if (!registrationDetails.nodalOfficerEmailId.match(orgEmailValidation)) {
            nodalOfficerEmailIdError = orgEmailValidationMessage;
        }

        if (registrationDetails.nodalOfficerContactNo === "") {
            nodalOfficerContactNoError = emptyFieldMessage;
        }
        else if (!registrationDetails.nodalOfficerContactNo.match(orgContactNoValidation)) {
            nodalOfficerContactNoError = orgContactNoValidationMessage;
        }

        if (orgNameError || orgEmailIdError || orgContactNoError || orgAddressError || orgLogoFileError || orgSupportingDocFileError || orgWebsiteUrlError || orgTypeError || orgCategoryError || nodalOfficerNameError || nodalOfficerEmailIdError || nodalOfficerContactNoError) {
            setRegistrationDetailsError((prev) => {
                return {
                    ...prev,
                    orgNameError: orgNameError,
                    orgEmailIdError: orgEmailIdError,
                    orgContactNoError: orgContactNoError,
                    orgWebsiteUrlError: orgWebsiteUrlError,
                    orgTypeError: orgTypeError,
                    orgCategoryError: orgCategoryError,
                    orgAddressError: orgAddressError,
                    orgLogoFileError: orgLogoFileError,
                    orgSupportingDocFileError: orgSupportingDocFileError,
                    nodalOfficerNameError: nodalOfficerNameError,
                    nodalOfficerEmailIdError: nodalOfficerEmailIdError,
                    nodalOfficerContactNo: nodalOfficerContactNoError
                }
            })
            return false;
        }
        return true;
    }

    const onChangeHandler = (e) => {
        setRegistrationDetails((prev) => {
            return {
                ...prev,
                [e.target.name]: e.target.value
            }
        })
    }

    const onSelectOrgLogo = (e) => {
        let file = e.target.files;
        for (let i = 0; i < file.length; i++) {
            if (file[i].type != "image/jpeg" && file[i].type != "image/jpg" && file[i].type != "image/png") {
                return;
            }
            else {
                setRegistrationDetails((prev) => {
                    return {
                        ...prev,
                        orgLogoFile: file
                    }
                })
            }
        }
    }

    const onSelectOrgSupporingDoc = (e) => {
        let file = e.target.files;
        for (let i = 0; i < file.length; i++) {
            if (file[i].type !== "application/pdf") {
                return;
            }
            else {
                setRegistrationDetails((prev) => {
                    return {
                        ...prev,
                        orgSupportingDocFile: file
                    }
                })
            }
        }

    }

    const onSubmitRegistrationForm = () => {
        const form = new FormData();
        for (const key in registrationDetails) {
            if (Object.hasOwnProperty.call(registrationDetails, key)) {
                form.append(key, registrationDetails[key]);
            }
        }
        const checkValidation = validationChecking()

        if (checkValidation) {
            form.delete("regNo");
            console.log(registrationDetails)
            Swal.fire({
                title: "Please Wait",
                text: "Details are storing in the Database",
                didOpen: () => {
                    Swal.showLoading();
                    services.registerOrganisation(form).then((resp) => {
                        if (resp.status === 200) {
                            Swal.fire({
                                title: "Success",
                                text: "Registration Done Successfully",
                                timer: 2000,
                                icon: "success",
                                showCloseButton: false
                            }).then(() => {
                                resetRegistrationDetails();
                                resetRegistrationDetailsError();
                            })
                        }
                    }).catch(error => {
                        Swal.fire({
                            title: "Error",
                            text: "Something went wrong, Try Later",
                            timer: 3000,
                            icon: "error",
                            showCloseButton: false
                        }).then(() => {
                            resetRegistrationDetails();
                            resetRegistrationDetailsError();
                        })
                    })
                }
            })
        }
    }




    return (
        <div className='super-admin'>
            <div className='wrapper'>
                <Sidebar />
                <div className='main-area'>
                    <Navbar />
                    <div className='main'>
                        <main className='content px-3 py-2'>
                            <div className='container-fluid' >
                                <div className='mb-4 mt-4'>
                                    <h4> Client Registration </h4>
                                </div>
                                <div className='row'>
                                    <div className='col d-flex'>
                                        <div className='card flex-fill card-body-area'>
                                            <h4 className='card-title'>Registration</h4>
                                            <div className='card-body d-flex flex-fill'>
                                                <div className='container'>
                                                    <div className='form'>
                                                        <div className='details personal'>
                                                            <span className='title'>Organisation Details</span>
                                                            <div className='fields'>
                                                                <div className='input-field'>
                                                                    <label>Name</label>
                                                                    <input type='text' placeholder='Enter Organisation Name' name='orgName' value={registrationDetails.orgName} onChange={onChangeHandler} required />
                                                                    {
                                                                        registrationDetailsError.orgNameError ?
                                                                            <>
                                                                                <span>{registrationDetailsError.orgNameError}</span>
                                                                            </>
                                                                            :
                                                                            <>
                                                                            </>
                                                                    }
                                                                </div>

                                                                <div className='input-field'>
                                                                    <label>E-mail</label>
                                                                    <input type='text' placeholder='Enter Organisation Email' name='orgEmailId' value={registrationDetails.orgEmailId} onChange={onChangeHandler} required />
                                                                    {
                                                                        registrationDetailsError.orgEmailIdError ?
                                                                            <>
                                                                                <span>{registrationDetailsError.orgEmailIdError}</span>
                                                                            </>
                                                                            :
                                                                            <>
                                                                            </>
                                                                    }
                                                                </div>

                                                                <div className='input-field'>
                                                                    <label>Contact No.</label>
                                                                    <input type='text' placeholder='Enter Organisation Contact No.' name='orgContactNo' value={registrationDetails.orgContactNo} onChange={onChangeHandler} required />
                                                                    {
                                                                        registrationDetailsError.orgContactNoError ?
                                                                            <>
                                                                                <span>{registrationDetailsError.orgContactNoError}</span>
                                                                            </>
                                                                            :
                                                                            <>
                                                                            </>
                                                                    }
                                                                </div>


                                                                <div className='input-field'>
                                                                    <label>Website URL</label>
                                                                    <input type='text' placeholder='Enter Organisation Website URL' name='orgWebsiteUrl' value={registrationDetails.orgWebsiteUrl} onChange={onChangeHandler} required />
                                                                    {
                                                                        registrationDetailsError.orgWebsiteUrlError ?
                                                                            <>
                                                                                <span>{registrationDetailsError.orgWebsiteUrlError}</span>
                                                                            </>
                                                                            :
                                                                            <>
                                                                            </>
                                                                    }
                                                                </div>

                                                                <div className='input-field'>
                                                                    <label>Type</label>
                                                                    {/* <input type='text' placeholder='Enter Organisation Contact No.' name='orgType' value={registrationDetails.orgType} onChange={onChangeHandler} required /> */}
                                                                    <select name='orgType' value={registrationDetails.orgType} onChange={onChangeHandler} >
                                                                        <option defaultValue="selectType" selected>Select Type</option>
                                                                        {
                                                                            getOrganisationType?.map((type) => {
                                                                                return (
                                                                                    <option value={type.orgType}>{type.orgType}</option>
                                                                                )
                                                                            })
                                                                        }
                                                                    </select>
                                                                    {
                                                                        registrationDetailsError.orgTypeError ?
                                                                            <>
                                                                                <span>{registrationDetailsError.orgTypeError}</span>
                                                                            </>
                                                                            :
                                                                            <>
                                                                            </>
                                                                    }
                                                                </div>
                                                                <div className='input-field'>
                                                                    <label>Category</label>
                                                                    <select name='orgCategory' value={registrationDetails.orgCategory} onChange={onChangeHandler} required >
                                                                        <option value={"selectCategory"} selected >Select Category</option>
                                                                        {
                                                                            getOrganisationCategory?.map((cat) => {
                                                                                return (
                                                                                    <option value={cat.orgCategory}>{cat.orgCategory}</option>
                                                                                )
                                                                            })
                                                                        }
                                                                    </select>
                                                                    {
                                                                        registrationDetailsError.orgCategoryError ?
                                                                            <>
                                                                                <span>{registrationDetailsError.orgCategoryError}</span>
                                                                            </>
                                                                            :
                                                                            <>
                                                                            </>
                                                                    }
                                                                </div>

                                                                <div className='input-field field-address'>
                                                                    <label>Address</label>
                                                                    <input type='text' placeholder='Enter Organisation Address' name='orgAddress' value={registrationDetails.orgAddress} onChange={onChangeHandler} required />
                                                                    {
                                                                        registrationDetailsError.orgAddressError ?
                                                                            <>
                                                                                <span>{registrationDetailsError.orgAddressError}</span>
                                                                            </>
                                                                            :
                                                                            <>
                                                                            </>
                                                                    }
                                                                </div>

                                                            </div>
                                                            <div className='file-upload-area'>

                                                                <div className='input-field'>
                                                                    <label>Upload Logo</label>
                                                                    <input type='file' className='custom-upload-btn' onChange={onSelectOrgLogo} />
                                                                    {/* <div className='file'></div> */}
                                                                    {
                                                                        registrationDetailsError.orgLogoFileError ?
                                                                            <>
                                                                                <span>{registrationDetailsError.orgLogoFileError}</span>
                                                                            </>
                                                                            :
                                                                            <>
                                                                            </>
                                                                    }
                                                                </div>

                                                                <div className='input-field'>
                                                                    <label>Upload Supporting Documents</label>
                                                                    <input type='file' onChange={onSelectOrgSupporingDoc} />
                                                                    {
                                                                        registrationDetailsError.orgSupportingDocFileError ?
                                                                            <>
                                                                                <span>{registrationDetailsError.orgSupportingDocFileError}</span>
                                                                            </>
                                                                            :
                                                                            <>
                                                                            </>
                                                                    }
                                                                </div>

                                                            </div>

                                                        </div>

                                                        <div className='details ID'>
                                                            <span className='title'>Nodal Officer Details</span>
                                                            <div className='fields'>
                                                                <div className='input-field'>
                                                                    <label>Name</label>
                                                                    <input type='text' placeholder='Enter Nodal Officer Name' name='nodalOfficerName' value={registrationDetails.nodalOfficerName} onChange={onChangeHandler} required />
                                                                    {
                                                                        registrationDetailsError.nodalOfficerNameError ?
                                                                            <>
                                                                                <span>{registrationDetailsError.nodalOfficerNameError}</span>
                                                                            </>
                                                                            :
                                                                            <>
                                                                            </>
                                                                    }
                                                                </div>
                                                                <div className='input-field'>
                                                                    <label>E-mail</label>
                                                                    <input type='text' placeholder='Enter Nodal Officer Email' name='nodalOfficerEmailId' value={registrationDetails.nodalOfficerEmailId} onChange={onChangeHandler} required />
                                                                    {
                                                                        registrationDetailsError.nodalOfficerEmailIdError ?
                                                                            <>
                                                                                <span>{registrationDetailsError.nodalOfficerEmailIdError}</span>
                                                                            </>
                                                                            :
                                                                            <>
                                                                            </>
                                                                    }
                                                                </div>
                                                                <div className='input-field'>
                                                                    <label>Contact No.</label>
                                                                    <input type='text' placeholder='Enter Nodal Officer Contact No.' name='nodalOfficerContactNo' value={registrationDetails.nodalOfficerContactNo} onChange={onChangeHandler} required />
                                                                    {
                                                                        registrationDetailsError.orgContactNoError ?
                                                                            <>
                                                                                <span>{registrationDetailsError.orgContactNoError}</span>
                                                                            </>
                                                                            :
                                                                            <>
                                                                            </>
                                                                    }
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <button className='submit-btn' onClick={() => { onSubmitRegistrationForm() }}>
                                                            <span className='btnText'>Submit</span>
                                                        </button>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </main>
                    </div>
                    <Footer />
                </div>
            </div>
        </div>
    )
}


