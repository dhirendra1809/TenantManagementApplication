import React from 'react'

import './aboutUs.css'

export default function AboutUs() {
    return (
        <>
            {/* <div className='center-vertical'>
                <div className='about-us-section'>
                    <div className='container'>
                        <div className='row'>
                            <div className='col-12 col-lg-6 mb-4 mb-lg-0'>
                                <div className='img-head'>
                                    
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div> */}

            <div className='about-us-container'>
                <div className='heading' >
                    <h1>About Us</h1>
                    <div className='line'></div>
                </div>
                <div className='container'>
                    <section className='about'>
                        <div className='about-image'>
                            <img src={process.env.PUBLIC_URL + "assets/Images/meghsikshak.png"} />
                        </div>
                        <div className='about-content'>
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin neque tellus, maximus eget tellus non, venenatis accumsan odio. Nullam imperdiet semper tincidunt. Nullam tellus lacus, ornare pulvinar leo ut, eleifend blandit arcu. Quisque eleifend, ipsum eget dignissim placerat, lacus risus placerat ante, vitae dignissim tortor sapien non est. Nam pellentesque.</p>
                            <a href='#'>Read More</a>
                        </div>
                    </section>
                </div>
            </div>
        </>
    )
}
