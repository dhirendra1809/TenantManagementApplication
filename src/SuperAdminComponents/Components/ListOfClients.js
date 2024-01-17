import React, { useEffect, useState } from 'react'
import '../Css/SuperAdminComponent.css'
import Sidebar from '../CommonAdminComponents/Sidebar'
import Navbar from '../CommonAdminComponents/Navbar'
import Footer from '../CommonAdminComponents/Footer'
import DataTable from 'react-data-table-component'
import services from '../../Services/services'

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

    const column = [
        {
            name: "S.No",
            selector: (row, index) => index = index + 1,
        },
        {
            name: "Organisation Name",
            selector: (row) => row?.orgName

        },
        {
            name: "Email-ID",
            selector: (row) => row?.orgEmailId,
        },
        {
            name: "Nodal Officer Name",
            selector: (row) => row?.orgNodalOfficerName,
        },
        {
            name: "Nodal Officer Name",
            selector: (row) =>
                <>
                    <button className='btn btn-primary'>Click here</button>
                </>
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
                                                <div className='container'>
                                                    <DataTable
                                                        columns={column}
                                                        data={clientList}
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
