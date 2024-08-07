import React from "react";
import { Routes, Route } from "react-router-dom";
import Homepage from "../customer/pages/home/Homepage";
import Restraunt from "../customer/pages/restraunt/Restraunt";
import Cart from "../customer/pages/cart/Cart";
import Profile from "../customer/pages/profile/Profile";

const CustomerRoutes = () => {
  return (
    <>
      <Routes>
        <Route path="/*" element={<Homepage />} />
        <Route path="/restraunt/:city/:name/:id" element={<Restraunt />} />
        <Route path="/cart" element={<Cart />} />
        <Route path="/my-profile/*" element={<Profile />} />
      </Routes>
    </>
  );
};

export default CustomerRoutes;
