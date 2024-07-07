import { Box, Modal } from "@mui/material";
import React from "react";
import { useLocation, useNavigate } from "react-router-dom";
import Login from "../../components/login/Login";
import Register from "../../components/register/Register";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "background.paper",
  boxShadow: 24,
  outline: "none",
  p: 4,
  borderRadius: "10px",
};

const Auth = () => {
  const location = useLocation();
  const navigate = useNavigate();

  return (
    <>
      <Modal
        open={
          location.pathname === "/account/login" ||
          location.pathname === "/account/register"
        }
        onClose={() => navigate("/")}
      >
        {/* {location?.pathname === "/account/login" && <Box sx={style}>login</Box>}
        {location?.pathname === "/account/register" && (
          <Box sx={style}>register</Box>
        )} */}
        <Box sx={style}>
          {location?.pathname === "/account/login" && <Login />}

          {location?.pathname === "/account/register" && <Register />}
        </Box>
      </Modal>
    </>
  );
};

export default Auth;
