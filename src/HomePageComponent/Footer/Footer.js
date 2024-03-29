
import React from 'react'
import './footer.css'

export default function Footer() {
    return (
        <>
            <div className='footer-section' style={{backgroundColor:"aliceblue"}}>
                <div className='footer-area'>
                    <footer className='text-dark pt-4 pb-4'>
                        <div className='container text-center text-md-left'>
                            <div className='row text-center text-md-left'>
                                <div className='col-md-4 col-lg-4 col-xl-4 mx-auto mt-3'>
                                    <h5 className='text-uppercase mb-4 font-weight-bold'>Contact Us</h5>
                                    <div className='footer-heading-line'></div>
                                    <div className='contact-us-content'>
                                        <div className='row '>
                                            <div className='col-sm-3'><i class="fa-solid fa-location-dot"></i></div>
                                            <div className='col-sm-9'>
                                                <p>Plot No. 6 & 7, Hardware Park, Sy No. 1/1, Srisailam Highway, Keshavagiri Post, Via, Pahadi Shareef, Hyderabad, Telangana 501510</p>
                                            </div>
                                        </div>
                                        <div className='row'>
                                            <div className='col-sm-3'><i class="fa-solid fa-phone"></i></div>
                                            <div className='col-sm-9'>
                                                <p>+91 999999999</p>
                                            </div>
                                        </div>
                                        <div className='row'>
                                            <div className='col-sm-3'><i class="fa-solid fa-envelope"></i></div>
                                            <div className='col-sm-9'>
                                                <p>meghsikshak2022@gmail.com</p>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                                <div className='col-md-4 col-lg-4 col-xl-4 mx-auto mt-3'>
                                    <h5 className='text-uppercase mb-4 font-weight-bold'>UseFul Links</h5>
                                    <div className='footer-heading-line'></div>
                                    <div className='useful-link-content'>
                                        <li><a href='#'>CDAC (Center for Development of Advance Compunting)</a></li>
                                        <li><a href='#'>Meghsikshak</a></li>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </footer>
                </div>
                <div className='footer-line'></div>
                <div className='copyright-area' >
                    <h5>Copyright © 2024 | Powered By MeghSikshak , Centre for Development of Advanced Computing(C-DAC)</h5>
                    <a href="#"><i class="fa-brands fa-square-facebook"></i></a>
                    <a href="#"><i class="fa-brands fa-square-x-twitter"></i></a>
                    <a href="#"><i class="fa-brands fa-youtube"></i></a>
                    <a href="#"><i class="fa-brands fa-linkedin"></i></a>
                    <a href="#"><i class="fa-brands fa-square-instagram"></i></a>
                </div>
            </div>
        </>
    )
}

