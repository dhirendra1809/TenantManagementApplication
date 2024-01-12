import React from 'react'

import '../Css/Footer.css'

export default function Footer() {
    return (
        <div id="footer-id">
            <div className='footer'>
                <div className='container-fluid'>
                    <div className='row text-muted'>
                        <div className='col-6 text-start'>
                            <a href="#" className='text-muted'>
                                <strong>CDAC (Center for Development of Advanced Computing)</strong>
                            </a>
                        </div>
                        <div className='col-6 text-end'>
                            <ul className='list-inline'>
                                <li className='list-inline-item'>
                                    <a href="#" className='text-muted'>
                                        <strong>Contact</strong>
                                    </a>
                                    <a href="#" className='text-muted'>
                                        <strong>About Us</strong>
                                    </a>
                                    <a href="#" className='text-muted'>
                                        <strong>Terms</strong>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    )
}
