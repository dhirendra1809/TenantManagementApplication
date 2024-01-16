import React from 'react'

import './heroSlider.css';

export default function HeroSlider() {
    return (
        <>
            <div>
                <div id="carouselExampleCaptions" className="carousel slide">
                    <div className="carousel-indicators">
                        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" className="active" aria-current="true" aria-label="Slide 1"></button>
                    </div>
                    <div className="carousel-inner">
                        <div className="carousel-item active c-item position-relative">
                            <img src={process.env.PUBLIC_URL + "assets/Images/pic1.png"} className="d-block w-100 c-img" alt="..." />
                            <div className='overlay'>
                                <div className="carousel-caption text-center text-white position-absolute top-50 start-50 translate-middle">
                                    <h5 className='display-5 mt-4 fw-bolder text-capitalize'>First slide label</h5>
                                    <p className='mt-5 fs-5 text-capitalize'>Some representative placeholder content for the first slide.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <button className="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
                        <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span className="visually-hidden">Previous</span>
                    </button>
                    <button className="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
                        <span className="carousel-control-next-icon" aria-hidden="true"></span>
                        <span className="visually-hidden">Next</span>
                    </button>
                </div>
            </div>
        </>
    )
}
