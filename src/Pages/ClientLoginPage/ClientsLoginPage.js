import React from 'react'
import Header from '../../HomePageComponent/Header/Header'
import './clientlogin.css'
import Footer from '../../HomePageComponent/Footer/Footer'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import '@fortawesome/fontawesome-free/css/all.css';
import Hero from '../Components/Hero';

export default function ClientsLoginPage() {

  const clientList = [
    {
      name: "Punjab Police Academy",
      image: "ppa.png"
    },
    {
      name: "National Forensic Sciences University",
      image: "nfsu.png"
    },
    {
      name: "C-DAC Accelerated Knowledge Enhancement Series",
      image: "cakes3.0.png"
    },
    {
      name: "Maharashtra Police Academy",
      image: "mpa.png"
    },
    {
      name: "Centre For Materials For Electronics Technology",
      image: "cmet.png"
    },
  ]







  return (
    <div>
      <Header page={"clientslogin"} />
      <div>
        <Hero title={"Client Login"} description={"Some representative placeholder content for the first slide"} />
        <div className='container py-5'>
          <div className='row row-cols-1 row-cols-md-3 row-cols-xxl-4 g-4 py-5 align-items-stretch'>
            {
              clientList.map(client => {
                return (
                  <>
                    <div className='col'>
                      <div className='card h-100' style={{ width: "18rem" }}>
                        <img src={process.env.PUBLIC_URL + `/assets/Images/Clients/${client.image}`} className='card-img-top card-image' alt='..' />
                        <div className='card-body'>
                          <h5 style={{ textAlign: "center" }} className='card-title'>{client.name}</h5>
                        </div>
                      </div>
                    </div>
                  </>
                )
              })
            }
          </div>
        </div>
        <Footer />
      </div>

    </div>
  )
}

