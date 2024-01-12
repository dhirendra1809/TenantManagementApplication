import React from 'react'

import './registrationPage.css'
import Header from '../../HomePageComponent/Header/Header'
import Hero from '../Components/Hero'
import Footer from '../../HomePageComponent/Footer/Footer'

export default function RegistrationPage() {
  return (
    <>
      <Header />
      <Hero title={"Client Registration Form"} />
      <div id="registration-block">
        <div className='registration-body'>
          <div className='container'>
            <header>Registration</header>
            <form action='#'>
              <div className='form first'>
                <div className='details personal'>
                  <span className='title'>Organisation Details</span>
                  <div className='fields'>

                    <div className='input-field'>
                      <label>Name</label>
                      <input type='text' placeholder='Enter Organisation Name' required />
                    </div>

                    <div className='input-field'>
                      <label>E-mail</label>
                      <input type='text' placeholder='Enter Organisation Email' required />
                    </div>

                    <div className='input-field'>
                      <label>Contact No.</label>
                      <input type='text' placeholder='Enter Organisation Contact No.' required />
                    </div>

                    <div className='input-field'>
                      <label>Address</label>
                      <input type='text' placeholder='Enter Organisation Address' required />
                    </div>

                    <div className='input-field'>
                      <label>Website URL</label>
                      <input type='text' placeholder='Enter Organisation Website URL' required />
                    </div>

                    <div className='input-field'>
                      <label>Type</label>
                      <input type='text' placeholder='Enter Organisation Contact No.' required />
                    </div>

                  </div>
                  <div className='file-upload-area'>

                    <div className='input-field'>
                      <label>Upload Logo</label>
                      <input type='file' className='custom-upload-btn' placeholder='Enter Organisation Website URL' required />
                      <div className='file'></div>
                    </div>

                    <div className='input-field'>
                      <label>Upload Supporting Documents</label>
                      <input type='file' placeholder='Enter Organisation Contact No.' required />
                    </div>

                  </div>

                </div>

                <div className='details ID'>
                  <span className='title'>Nodal Officer Details</span>
                  <div className='fields'>
                    <div className='input-field'>
                      <label>Name</label>
                      <input type='text' placeholder='Enter Nodal Officer Name' required />
                    </div>
                    <div className='input-field'>
                      <label>E-mail</label>
                      <input type='text' placeholder='Enter Nodal Officer Email' required />
                    </div>
                    <div className='input-field'>
                      <label>Contact No.</label>
                      <input type='text' placeholder='Enter Nodal Officer Contact No.' required />
                    </div>
                  </div>
                </div>
                <button className='submit-btn'>
                  <span className='btnText'>Submit</span>
                </button>

              </div>
            </form>
          </div>
        </div>
      </div>
      <Footer />
    </>

  )
}
