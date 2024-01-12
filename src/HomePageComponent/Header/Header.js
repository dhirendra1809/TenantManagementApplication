
import React from 'react'
import "./header.css"
import { Link } from 'react-router-dom'

export default function Header({ page }) {


    return (
        <>
            {/* <nav className="navbar bg-body-tertiary navbar-expand-lg fixed-top"> */}
            <nav className="navbar bg-body-tertiary navbar-expand-lg" style={{ padding: "10px 20px" }}>
                <div className="container-fluid">
                    <a className="navbar-brand me-auto" href="#">LOGO</a>
                    <div className="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
                        <div className="offcanvas-header">
                            <h5 className="offcanvas-title" id="offcanvasNavbarLabel">LOGO</h5>
                            <button type="button" style={{ border: "none", boxShadow: "none", outline: "none" }} className="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                        </div>
                        <div className="offcanvas-body">
                            <ul className="navbar-nav justify-content-center flex-grow-1 pe-3">
                                <li className="nav-item">
                                    <Link to={"/"} className={`nav-link mx-lg-2 ${page === "home" ? "active" : ""}`} aria-current="page" href="#">Home</Link>
                                </li>
                                <li className="nav-item">
                                    <Link to={"/clientslogin"} className={`nav-link mx-lg-2 ${page === "clientslogin" ? "active" : ""}`} href="#">Clients Login</Link>
                                </li>
                                <li className="nav-item">
                                    <Link to={"/aboutus"} className={`nav-link mx-lg-2 ${page === "aboutus" ? "active" : ""}`}>About Us</Link>
                                </li>
                                <li className="nav-item">
                                    <Link to={"/services"} className={`nav-link mx-lg-2 ${page === "services" ? "active" : ""}`}>Services</Link>
                                </li>
                                <li className="nav-item">
                                    <Link to={"/contact"} className={`nav-link mx-lg-2 ${page === "contact" ? "active" : ""}`}>Contact</Link>
                                </li>
                            </ul>
                        </div>
                    </div>
                    {/* <div> */}
                    <Link to={"/register"} className='register-button'>Register</Link>
                    <Link to={"/super-admin"} className='login-button'>Login</Link>
                    <button style={{ border: "none", boxShadow: "none", outline: "none" }} className="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    {/* </div> */}

                </div>
            </nav>

        </>
    )
}



