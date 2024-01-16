import React, { useState } from 'react'
import '../Css/Main.css'
import Navbar from './Navbar';

export default function Main() {

  return (
    <>
      <div className='main'>
        <main className='content px-3 py-2'>
          <div className='container-fluid'>
            <div className='mb-4 mt-4'>
              <h4> Admin Dashboard </h4>
            </div>
            <div className='row'>
              <div className='col-12 col-md-6 d-flex'>
                <div className='card flex-fill border-2 illustration'>
                  <div className='card-body d-flex flex-fill'>
                    <div className='row g-0 w-100'>
                      <div className='col-6'>
                        <div className='p-3 m-1'>
                          <h4>Welcome back, Admin</h4>
                          <p className='mb-0'>Admin Dashboard, Dhirendra</p>
                        </div>
                      </div>
                      <div className='col-6 align-self-end text-center'>
                        <img src={process.env.PUBLIC_URL + "/assets/Images/defaultPP.png"} className='img-fluid illustration-img' />
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div className='col-12 col-md-6 d-flex'>
                <div className='card flex-fill border-2 illustration'>
                  <div className='card-body d-flex flex-fill'>
                    <div className='row g-0 w-100'>
                      <div className='col-6'>
                        <div className='p-3 m-1'>
                          <h4>Welcome back, Admin</h4>
                          <p className='mb-0'>Admin Dashboard, Dhirendra</p>
                        </div>
                      </div>
                      <div className='col-6 align-self-end text-center'>
                        <img src={process.env.PUBLIC_URL + "/assets/Images/defaultPP.png"} className='img-fluid illustration-img' />
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div className="col-12 col-md-6 d-flex">
                <div className="card flex-fill border-2">
                  <div className="card-body">
                    <div className="d-flex align-items-start">
                      <div className="flex-grow-1">
                        <h4 className="mb-2">
                          $ 78.00
                        </h4>
                        <p className="mb-2">
                          Total Earnings
                        </p>
                        <div className="mb-0">
                          <span className="badge text-success me-2">
                            +9.0%
                          </span>
                          <span className="text-muted">
                            Since Last Month
                          </span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </main>
      </div>
    </>
  )
}
