import React from 'react'
import './client.css'
import { Overlay, OverlayTrigger, Tooltip } from 'react-bootstrap'

export default function ClientComponent() {
    return (
        <>
            <div className='logos'>
                <div className='heading' >
                    <h1>Our Client</h1>
                    <div className='line'></div>
                </div>
                <div className='logos-slide'>
                    <img src={process.env.PUBLIC_URL + "/assets/Images/Clients/ppa.png"} alt='ppa' />
                    <img src={process.env.PUBLIC_URL + "/assets/Images/Clients/bpspa.png"} alt='bpspa' />
                    <img src={process.env.PUBLIC_URL + "/assets/Images/Clients/cakes3.0.png"} alt='cakes' />
                    <img src={process.env.PUBLIC_URL + "/assets/Images/Clients/cdac.png"} alt='cdac' />
                    <img src={process.env.PUBLIC_URL + "/assets/Images/Clients/chariot.png"} alt='chariot' />
                    <img src={process.env.PUBLIC_URL + "/assets/Images/Clients/cmet.png"} alt='cmet' />
                    <img src={process.env.PUBLIC_URL + "/assets/Images/Clients/kpa.png"} alt='kap' />
                    <img src={process.env.PUBLIC_URL + "/assets/Images/Clients/mpa.png"} alt='mpa' />
                    <img src={process.env.PUBLIC_URL + "/assets/Images/Clients/nfsu.png"} alt='nfsu' />
                    <img src={process.env.PUBLIC_URL + "/assets/Images/Clients/ndu.png"} alt='ndu' />
                </div>
                <div className='logos-slide'>
                    <img src={process.env.PUBLIC_URL + "/assets/Images/Clients/ppa.png"} alt='ppa' />
                    <img src={process.env.PUBLIC_URL + "/assets/Images/Clients/bpspa.png"} alt='bpspa' />
                    <img src={process.env.PUBLIC_URL + "/assets/Images/Clients/cakes3.0.png"} alt='cakes' />
                    <img src={process.env.PUBLIC_URL + "/assets/Images/Clients/cdac.png"} alt='cdac' />
                    <img src={process.env.PUBLIC_URL + "/assets/Images/Clients/chariot.png"} alt='chariot' />
                    <img src={process.env.PUBLIC_URL + "/assets/Images/Clients/cmet.png"} alt='cmet' />
                    <img src={process.env.PUBLIC_URL + "/assets/Images/Clients/kpa.png"} alt='kap' />
                    <img src={process.env.PUBLIC_URL + "/assets/Images/Clients/mpa.png"} alt='mpa' />
                    <img src={process.env.PUBLIC_URL + "/assets/Images/Clients/nfsu.png"} alt='nfsu' />
                    <img src={process.env.PUBLIC_URL + "/assets/Images/Clients/ndu.png"} alt='ndu' />
                </div>
            </div>
        </>
    )
}
