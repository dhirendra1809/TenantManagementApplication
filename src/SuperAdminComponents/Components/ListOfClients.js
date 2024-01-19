import React, { useEffect, useState } from 'react'
import '../Css/SuperAdminComponent.css'
import Sidebar from '../CommonAdminComponents/Sidebar'
import Navbar from '../CommonAdminComponents/Navbar'
import Footer from '../CommonAdminComponents/Footer'
import DataTable from 'react-data-table-component'
import services from '../../Services/services'
import Swal from 'sweetalert2'



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
            timer: 3000,
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
            name: "Nodal Officer Name",
            selector: (row) =>
                <>
                    <button className='btn btn-info' style={{ backgroundColor: "#00b3d7" }}><i className='fa fa-solid fa-info' style={{ color: 'white' }}></i></button>
                    <button className='btn btn-success' onClick={() => { onClickApprove(row.regNo) }} ><i className='fa fa-solid fa-thumbs-up'></i></button>
                    <button className='btn btn-danger' onClick={() => { onClickReject(row.regNo) }}><i className='fa fa-solid fa-thumbs-down'></i></button>
                </>,


        },

    ]

    return (
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
    )
}
