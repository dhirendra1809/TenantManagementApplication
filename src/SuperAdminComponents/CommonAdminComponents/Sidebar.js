import React, { useState } from 'react'
import '../Css/Sidebar.css'

export default function Sidebar({ isCollapsed }) {

    // const [collapseState , setCollapseState] = useState()

    // const toggleCollapseState = (condition) =>{
    //     setCollapseState(condition)
    // }


    return (
        <>
            <aside id='sidebar' class="js-sidebar">
                <div className='h-100'>
                    <div className='sidebar-logo'>
                        <a href='#'> LOGO </a>
                    </div>
                    <ul className='sidebar-nav'>
                        <li className='sidebar-header'>
                            Admin Element
                        </li>
                        <li className="sidebar-item">
                            <a href="#" className="sidebar-link">
                                <i className="fa fa-solid fa-list pe-2"></i>
                                Dashboard
                            </a>
                        </li>
                        <li className="sidebar-item">
                            <a href="#" className="sidebar-link collapsed" data-bs-target="#pages" data-bs-toggle="collapse"
                                aria-expanded="false"><i className="fa fa-solid fa-file-lines pe-2"></i>
                                Pages
                            </a>
                            <ul id="pages" className="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                                <li className="sidebar-item">
                                    <a href="#" className="sidebar-link">Page 1</a>
                                </li>
                                <li className="sidebar-item">
                                    <a href="#" className="sidebar-link">Page 2</a>
                                </li>
                            </ul>
                        </li>
                        <li className="sidebar-item">
                            <a href="#" className="sidebar-link collapsed" data-bs-target="#posts" data-bs-toggle="collapse"
                                aria-expanded="false"><i className="fa fa-solid fa-sliders pe-2"></i>
                                Posts
                            </a>
                            <ul id="posts" className="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                                <li className="sidebar-item">
                                    <a href="#" className="sidebar-link">Posts 1</a>
                                </li>
                                <li className="sidebar-item">
                                    <a href="#" className="sidebar-link">Posts 2</a>
                                </li>
                                <li className="sidebar-item">
                                    <a href="#" className="sidebar-link">Posts 3</a>
                                </li>
                            </ul>
                        </li>
                        <li className="sidebar-item">
                            <a href="#" className="sidebar-link collapsed" data-bs-target="#auth" data-bs-toggle="collapse"
                                aria-expanded="false"><i className="fa fa-regular fa-user pe-2"></i>
                                Auth
                            </a>
                            <ul id="auth" className="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                                <li className="sidebar-item">
                                    <a href="#" className="sidebar-link">Login </a>
                                </li>
                                <li className="sidebar-item">
                                    <a href="#" className="sidebar-link">Register </a>
                                </li>
                                <li className="sidebar-item">
                                    <a href="#" className="sidebar-link">Forgot Password </a>
                                </li>
                            </ul>
                        </li>
                        <li className='sidebar-header'>
                            Multi Level Menu
                        </li>
                        <li className='sidebar-item'>
                            <a href="#" className="sidebar-link collapsed" data-bs-target="#multi" data-bs-toggle="collapse"
                                aria-expanded="false"><i className="fa fa-solid fa-share-nodes pe-2"></i>
                                Multi Drop down
                            </a>
                            <ul id="multi" className="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                                <li className="sidebar-item">
                                    <a href="#" className="sidebar-link collapsed" data-bs-target="#level-1" data-bs-toggle="collapse"
                                        aria-expanded="false">Level 1 </a>
                                    <ul id="level-1" className="sidebar-dropdown list-unstyled collapse">
                                        <li className="sidebar-item">
                                            <a href="#" className="sidebar-link">Level 1.1</a>
                                        </li>
                                        <li className="sidebar-item">
                                            <a href="#" className="sidebar-link">Level 1.2</a>
                                        </li>
                                        <li className="sidebar-item">
                                            <a href="#" className="sidebar-link">Level 1.3</a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </aside>
        </>
    )
}
