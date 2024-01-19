import React, { useEffect, useState } from 'react'
import '../Css/SuperAdminComponent.css'
import '../Css/Modal.css'
import Sidebar from '../CommonAdminComponents/Sidebar'
import Navbar from '../CommonAdminComponents/Navbar'
import Footer from '../CommonAdminComponents/Footer'
import DataTable from 'react-data-table-component'
import services from '../../Services/services'
import Swal from 'sweetalert2'
import { Modal } from 'react-bootstrap'



export default function ListOfClients() {

    useEffect(() => {
        getAllClientList();
    }, [])

    const [clientList, setClientList] = useState();

    const getAllClientList = () => {
        services.getAllClientList().then((resp) => {
            if (resp.status === 200) {
                setClientList(resp.data);
            }
        }).catch((error) => {

        })
    }

    const onClickApprove = (orgId) => {
        Swal.fire({
            title: "Loading",
            text: "Please wait for a while",
            showConfirmButton: false,
            didOpen: () => {
                services.approveClient(orgId).then((resp) => {
                    if (resp.status === 200) {
                        Swal.fire({
                            title: "Success",
                            text: "Client Approved Successfully",
                            timer: 3000,
                            icon: "success",
                            showConfirmButton: false,
                        }).then(() => {
                            let obj = clientList.find(client => client.regNo === orgId);
                            obj.approvalStatus = "approve";
                            let arr = clientList.filter(obj);
                            arr.push(obj);
                            setClientList(arr);
                        })
                    }
                }).catch((error) => {
                    Swal.fire({
                        title: "Error",
                        text: "Something went Wrong, Try Later",
                        timer: 3000,
                        icon: "error"
                    })
                })
            }
        })
    }

    const onClickReject = (orgId) => {
        services.rejectClient(orgId).then((resp) => {
            if (resp.status === 200) {
                Swal.fire({
                    title: "Success",
                    text: "Client Rejected Successfully",
                    timer: 3000,
                    icon: "success",
                    showConfirmButton: false,
                }).then(() => {
                    let obj = clientList.find(client => client.regNo === orgId);
                    obj.approvalStatus = "reject";
                    let arr = clientList.filter(obj);
                    arr.push(obj);
                    setClientList(arr);
                })
            }
        }).catch((error) => {
            Swal.fire({
                title: "Error",
                text: "Something went Wrong, Try Later",
                timer: 3000,
                icon: "error"
            })
        })
    }

    const customTableStyled = {
        rows: {
            style: {
                minHeight: '72px',
                margin: "1px 0px",
            },
        },
        headCells: {
            style: {
                fontSize: "15px",
                fontWeight: 600,
                color: "#333",

            },
        },
        cells: {
            style: {
                fontSize: "15px"
            },
        },

    }

    // const onClickInfoButton = (regId) => {

    //     console.log(clientList.filter(o => o.regNo === regId))
    //     let Obj = clientList.find(o => o.regNo === regId)
    //     Obj.approvalStatus = "reject"

    //     const array = clientList.filter(o => o.regNo === regId)
    //     array.push(Obj);
    //     setClientList(array)


    //     // clientList.push(Obj)
    //     // console.log(Obj)
    //     console.log(clientList)
    // }

    const conditionalRowStyles = [
        {
            when: row => row.approvalStatus === "pending",
            style: {
                backgroundColor: 'rgba(247, 203, 115, 0.3)',
                color: 'black',
                '&:hover': {
                    cursor: "pointer",
                },
            },
        },
        {
            when: row => row.approvalStatus === "warning",
            style: {
                backgroundColor: 'rgba(242, 146, 57, 0.3)',
                color: 'black',
                '&:hover': {
                    cursor: "pointer",
                },
            },
        },
        {
            when: row => row.approvalStatus === "reject",
            style: {
                backgroundColor: 'rgba(217, 81, 44, 0.3)',
                color: 'black',
                '&:hover': {
                    cursor: "pointer",
                },
            },
        },
        {
            when: row => row.approvalStatus === "approve",
            style: {
                backgroundColor: 'rgba(107, 200, 163, 0.3)',
                color: 'black',
                '&:hover': {
                    cursor: "pointer",
                },
            },
        }
    ]


    const column = [
        {
            name: "S.No",
            selector: (row, index) => index = index + 1,
            wrap: true,
        },
        {
            name: "Organisation Name",
            selector: (row) => row?.orgName,
            wrap: true,

        },
        {
            name: "Email-ID",
            selector: (row) => row?.orgEmailId,
            wrap: true,

        },
        {
            name: "Nodal Officer Name",
            selector: (row) => row?.orgNodalOfficerName,
            wrap: true,

        },
        {
            name: "Nodal Officer Email-Id",
            selector: (row) => row?.orgNodalOfficerEmail,
            wrap: true,

        },
        {
            name: "Action",
            cell: (row) =>
                <>
                    <div className='row'>
                        <div className='col'>
                            <button className='btn btn-info' style={{ margin: "1px", minWidth: "42px" }} onClick={() => { }} ><i className='fa fa-solid fa-info' style={{ color: 'white' }}></i></button>
                            <button className='btn btn-success' style={{ margin: "1px" }} onClick={() => { onClickApprove(row.regNo) }} ><i className='fa fa-solid fa-thumbs-up'></i></button>
                            <button className='btn btn-danger' style={{ margin: "1px" }} onClick={() => { onClickReject(row.regNo) }}><i className='fa fa-solid fa-thumbs-down'></i></button>
                        </div>
                    </div>
                </>,
            wrap: true,
        },

    ]

    return (
        <>
            <div className='super-admin'>
                <div className='wrapper'>
                    <Sidebar />
                    <div className='main-area'>
                        <Navbar />
                        <div className='main'>
                            <main className='content px-3 py-2'>
                                <div className='container-fluid' >
                                    <div className='mb-4 mt-4'>
                                        <h4> List Of Clients </h4>
                                    </div>
                                    <div className='row'>
                                        <div className='col d-flex'>
                                            <div className='card flex-fill card-body-area'>
                                                <h4 className='card-title'>Clients List</h4>
                                                <div className='card-body d-flex flex-fill'>
                                                    <div className='container-body'>
                                                        <DataTable
                                                            columns={column}
                                                            data={clientList}
                                                            customStyles={customTableStyled}
                                                            conditionalRowStyles={conditionalRowStyles}
                                                            pagination
                                                        />
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </main>
                        </div>
                        <Footer />
                    </div>
                </div>
            </div>
            <Modal size='xl' show={true} onHide={false} centered>
                <div className='modal-area'>
                    <Modal.Header>
                        <Modal.Title >
                            <div className='modal-title-center'>
                                <div className='modal-title'>
                                    <h3>Client Details</h3>
                                </div>
                                <div className='line'></div>
                            </div>
                        </Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <div className='modal-body-clientList'>
                            <div className='row'>
                                <div className='col-md-9'>
                                    <div className='row'>
                                        <div className='col'>
                                            <h6>Organisation Name</h6>
                                            <p>Center for Developmentof Advance Computing</p>
                                        </div>
                                        <div className='col'>
                                            <h6>Email-ID</h6>
                                            <p>meghsikshak2022@gmail.com</p>
                                        </div>
                                        <div className='col'>
                                            <h6>Contact No.</h6>
                                            <p>+91 9999999999</p>
                                        </div>
                                    </div>
                                    <div className='row'>
                                        <div className='col'>
                                            <h6>Address</h6>
                                            <p>Plot No. 6 & 7, Hardware Park, Sy No. 1/1, Srisailam Highway, Keshavagiri Post, Via, Pahadi Shareef, Hyderabad, Telangana 501510</p>
                                        </div>
                                        
                                    </div>
                                    <div className='row'>
                                        <div className='col'>
                                            <h6>Nodal Officer</h6>
                                            <p>Center for Developmentof Advance Computing</p>
                                        </div>
                                        <div className='col'>
                                            <h6>Nodal Officer E-Mail ID</h6>
                                            <p>meghsikshak2022@gmail.com</p>
                                        </div>
                                        <div className='col'>
                                            <h6>Contact No.</h6>
                                            <p>+91 9999999999</p>
                                        </div>
                                    </div>
                                </div>
                                <div className='col-md-3'>
                                    <img src={process.env.PUBLIC_URL+'/assets/images/Clients/cdac.png'} className='img-fluid'/>
                                </div>
                            </div>
                            <button>Close</button>
                        </div>
                    </Modal.Body>
                    <Modal.Footer>

                    </Modal.Footer>
                </div>
            </Modal>


        </>
    )
}
