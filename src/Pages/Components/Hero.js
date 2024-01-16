import React from 'react'

export default function Hero({ title, description }) {
    return (
        <div id="carouselExampleCaptions" className="carousel slide">
            <div className="carousel-inner">
                <div className="carousel-item active login-image position-relative">
                    <img src={process.env.PUBLIC_URL + "assets/Images/loginImage.jpg"} className="d-block w-100 c-img" alt="..." />
                    <div className='client-overlay'>
                        <div className="text-center text-white position-absolute top-50 start-50 translate-middle">
                            <h5 className='display-5 mt-3 fw-bolder text-capitalize'>{title}</h5>
                            <p className='mt-4 fs-5 text-capitalize'>{description}</p>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
