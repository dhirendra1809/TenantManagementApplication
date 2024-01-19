import React from 'react'

import '../Css/Footer.css'
import { Link } from 'react-router-dom'

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
                                    <Link  className='text-muted'>
                                        <strong>Contact</strong>
                                    </Link>
                                    <Link className='text-muted'>
                                        <strong>About Us</strong>
                                    </Link>
                                    <Link className='text-muted'>
                                        <strong>Terms</strong>
                                    </Link>
                                </li>
                            </ul>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    )
}
