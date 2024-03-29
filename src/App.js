import React from "react";
import SuperAdminComponents from "./SuperAdminComponents/SuperAdminComponents";
import { Route, Routes } from "react-router-dom";
import HomePageComponent from "./HomePageComponent/HomePageComponent";
import ClientsLoginPage from "./Pages/ClientLoginPage/ClientsLoginPage";
import RegistrationPage from "./Pages/RegistrationPage/RegistrationPage";
import Header from "./HomePageComponent/Header/Header";

function App() {
  return (
    <>
      <Routes>        
        <Route path={process.env.PUBLIC_URL + "/"} element={<HomePageComponent />} />
        <Route path={process.env.PUBLIC_URL + "/clientslogin"} element={<ClientsLoginPage />} />
        <Route path={process.env.PUBLIC_URL + "/super-admin"} element={<SuperAdminComponents />} />
        <Route path={process.env.PUBLIC_URL + "/register"} element={<RegistrationPage />} />
      </Routes>

    </>
  );
}

export default App;
