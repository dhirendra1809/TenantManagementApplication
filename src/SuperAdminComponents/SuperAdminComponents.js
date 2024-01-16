import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import '@fortawesome/fontawesome-free/css/all.css';
import './Css/SuperAdminComponent.css'
import Sidebar from './CommonAdminComponents/Sidebar';
import Main from './CommonAdminComponents/Main';
import Navbar from './CommonAdminComponents/Navbar';
import Footer from './CommonAdminComponents/Footer';

export default function SuperAdminComponents() {
    return (
        <div className='super-admin'>
            <div className='wrapper'>
                <Sidebar />
                <div className='main-area'>
                    <Navbar />
                    <Main />
                    <Footer />
                </div>
            </div>
        </div>
    )
}
