import React, { useState } from "react";
import ProfileNav from "./ProfileNav";
import { Route, Routes } from "react-router-dom";
import MyOrders from "./profileNav/MyOrders";
import MyFavorites from "./profileNav/MyFavorites";
import MyAddresses from "./profileNav/MyAddresses";
import MyEvents from "./profileNav/MyEvents";
import { AccountCircle } from "@mui/icons-material";
import { Button } from "@mui/material";

const Profile = () => {
  const [openSideBar, setOpenSideBar] = useState(false);

  const MyProfile = () => {
    return (
      <div className="lg:w-[80%] min-h-[80vh] flex justify-center text-center items-center">
        <div className="flex flex-col justify-center items-center">
          <AccountCircle sx={{ fontSize: "9rem" }} />
          <h1 className="py-5 text-2xl font-semibold">Manav Pradhan</h1>
          <p>Email: manavpradhan17@gmail.com</p>
          <Button sx={{ margin: "2rem 0rem" }} variant="contained">
            Logout
          </Button>
        </div>
      </div>
    );
  };

  return (
    <div className="lg:flex justify-between">
      <div className="lg:w-[20%] h-[80vh] sticky">
        <ProfileNav open={openSideBar} />
      </div>
      <Routes>
        <Route path="/" element={<MyProfile />} />
        <Route path="/orders" element={<MyOrders />} />
        <Route path="/favorites" element={<MyFavorites />} />
        <Route path="/addresses" element={<MyAddresses />} />
        <Route path="/events" element={<MyEvents />} />
      </Routes>
    </div>
  );
};

export default Profile;
