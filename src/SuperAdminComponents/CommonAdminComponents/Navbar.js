import React, { useState } from 'react'
import '../Css/Navbar.css'

export default function Navbar() {

    // const [sidebarCollapse, setSidebarCollapse] = useState(false);

    // const toggleSidebar = () => {
    //     setSidebarCollapse(!sidebarCollapse);
    //     console.log(sidebarCollapse)
    // }

    const toggleSidebar = () => {
        document.querySelector("#sidebar").classList.toggle("collapsed");
    }


    return (
        <div id='navbar'>
            <nav className='navbar navbar-expand px-3'>
                <button className='btn' id="sidebar-toggle" type='button' onClick={toggleSidebar} >
                    <span className='navbar-toggler-icon'></span>
                </button>
                <div className="navbar-collapse navbar">
                    <ul className="navbar-nav">
                        <li className="nav-item dropdown">
                            <a href="#" data-bs-toggle="dropdown" className="nav-icon pe-md-0">
                                <img src={process.env.PUBLIC_URL + "/assets/Images/defaultPP.png"} className="avatar img-fluid rounded" alt="" />
                            </a>
                            <div className="dropdown-menu dropdown-menu-end">
                                <a href="#" className="dropdown-item">Profile</a>
                                <a href="#" className="dropdown-item">Setting</a>
                                <a href="#" className="dropdown-item">Logout</a>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    )
}
