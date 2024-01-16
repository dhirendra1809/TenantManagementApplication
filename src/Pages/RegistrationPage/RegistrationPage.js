import React, { useState } from 'react'

import './registrationPage.css'
import Header from '../../HomePageComponent/Header/Header'
import Hero from '../Components/Hero'
import Footer from '../../HomePageComponent/Footer/Footer'

export default function RegistrationPage() {

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

    for (const key of form.entries()) {
      console.log(key[0] + " = " + key[1])
    }




  }


  return (
    <>
      <Header />
      <Hero title={"Client Registration Form"} />
      <div id="registration-block">
        <div className='registration-body'>
          <div className='container'>
            <header>Registration</header>
            <div>
              <div className='form first'>
                <div className='details personal'>
                  <span className='title'>Organisation Details</span>
                  <div className='fields'>

                    <div className='input-field'>
                      <label>Name</label>
                      <input type='text' placeholder='Enter Organisation Name' name='orgName' value={registrationDetails.orgName} onChange={onChangeHandler} required />
                    </div>

                    <div className='input-field'>
                      <label>E-mail</label>
                      <input type='text' placeholder='Enter Organisation Email' name='orgEmailId' value={registrationDetails.orgEmailId} onChange={onChangeHandler} required />
                    </div>

                    <div className='input-field'>
                      <label>Contact No.</label>
                      <input type='text' placeholder='Enter Organisation Contact No.' name='orgContactNo' value={registrationDetails.orgContactNo} onChange={onChangeHandler} required />
                    </div>


                    <div className='input-field'>
                      <label>Website URL</label>
                      <input type='text' placeholder='Enter Organisation Website URL' name='orgWebsiteUrl' value={registrationDetails.orgWebsiteUrl} onChange={onChangeHandler} required />
                    </div>

                    <div className='input-field'>
                      <label>Type</label>
                      <input type='text' placeholder='Enter Organisation Contact No.' name='orgType' value={registrationDetails.orgType} onChange={onChangeHandler} required />
                    </div>
                    <div className='input-field'>
                      <label>Category</label>
                      <input type='text' placeholder='Enter Organisation Contact No.' name='orgCategory' value={registrationDetails.orgCategory} onChange={onChangeHandler} required />
                    </div>

                    <div className='input-field field-address'>
                      <label>Address</label>
                      <input type='text' placeholder='Enter Organisation Address' name='orgAddress' value={registrationDetails.orgAddress} onChange={onChangeHandler} required />
                    </div>

                  </div>
                  <div className='file-upload-area'>

                    <div className='input-field'>
                      <label>Upload Logo</label>
                      <input type='file' className='custom-upload-btn' onChange={onSelectOrgLogo} />
                      <div className='file'></div>
                    </div>

                    <div className='input-field'>
                      <label>Upload Supporting Documents</label>
                      <input type='file' onChange={onSelectOrgSupporingDoc} />
                    </div>

                  </div>

                </div>

                <div className='details ID'>
                  <span className='title'>Nodal Officer Details</span>
                  <div className='fields'>
                    <div className='input-field'>
                      <label>Name</label>
                      <input type='text' placeholder='Enter Nodal Officer Name' name='nodalOfficerName' value={registrationDetails.nodalOfficerName} onChange={onChangeHandler} required />
                    </div>
                    <div className='input-field'>
                      <label>E-mail</label>
                      <input type='text' placeholder='Enter Nodal Officer Email' name='nodalOfficerEmailId' value={registrationDetails.nodalOfficerEmailId} onChange={onChangeHandler} required />
                    </div>
                    <div className='input-field'>
                      <label>Contact No.</label>
                      <input type='text' placeholder='Enter Nodal Officer Contact No.' name='nodalOfficerContactNo' value={registrationDetails.nodalOfficerContactNo} onChange={onChangeHandler} required />
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
      <Footer />
    </>

  )
}