import React from 'react'

import './heroSlider.css';

export default function HeroSlider() {
    return (
        <>
            <div>
                <div id="carouselExampleCaptions" class="carousel slide">
                    <div class="carousel-indicators">
                        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                    </div>
                    <div class="carousel-inner">
                        <div class="carousel-item active c-item position-relative">
                            <img src={process.env.PUBLIC_URL + "assets/Images/pic1.png"} class="d-block w-100 c-img" alt="..." />
                            <div className='overlay'>
                                <div class="carousel-caption text-center text-white position-absolute top-50 start-50 translate-middle">
                                    <h5 className='display-5 mt-4 fw-bolder text-capitalize'>First slide label</h5>
                                    <p className='mt-5 fs-5 text-capitalize'>Some representative placeholder content for the first slide.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
            </div>
        </>
    )
}
