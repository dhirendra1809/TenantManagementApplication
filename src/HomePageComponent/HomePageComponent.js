import React from 'react'
import Header from './Header/Header'
import HeroSlider from './HeroSlider/HeroSlider'
import AboutUs from './AboutUs/AboutUs'
import ClientComponent from './ClientsList/ClientComponent'
import Footer from './Footer/Footer'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import '@fortawesome/fontawesome-free/css/all.css';

export default function HomePageComponent() {
    return (
        <div>
            <Header page={"home"} />
            <HeroSlider />
            <AboutUs />
            <ClientComponent />
            <Footer />
        </div>
    )
}
