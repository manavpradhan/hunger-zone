import React, { useEffect, useState } from "react";
import "./Navbar.css";
import PersonIcon from "@mui/icons-material/Person";
import {
  Avatar,
  Badge,
  Button,
  IconButton,
  Menu,
  MenuItem,
} from "@mui/material";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import SearchIcon from "@mui/icons-material/Search";
import { useLocation, useNavigate } from "react-router-dom";
import Auth from "../../pages/auth/Auth";

const Navbar = () => {
  const navigate = useNavigate();
  const location = useLocation();

  const [anchorEl, setAnchorEl] = useState(null);
  const open = Boolean(anchorEl);

  const handleOpenMenu = (event) => {
    setAnchorEl(event.currentTarget);
  };
  const handleCloseMenu = () => {
    setAnchorEl(null);
  };

  const navigateToHome = () => {
    navigate("/");
  };
  return (
    <div className="sticky top-0 px-5 z-50 py-[.8rem] bg-[#e91e63]  lg:px-20 flex justify-between">
      <div
        onClick={navigateToHome}
        className="lg:mr-10 cursor-pointer flex items-center space-x-4"
      >
        <li className="logo font-semibold text-gray-300 text-2xl list-none">
          HungerZone
        </li>
      </div>
      <div className="flex items-center space-x-2 lg:space-x-10">
        <div className="">
          <IconButton
            className="nav-actions"
            onClick={() => navigate("/search")}
          >
            <SearchIcon sx={{ fontSize: "2rem" }} />
          </IconButton>
        </div>
        <div className="flex items-center space-x-2">
          {/* {auth.user?.fullName ? (
            <span
              id="demo-positioned-button"
              aria-controls={open ? "demo-positioned-menu" : undefined}
              aria-haspopup="true"
              aria-expanded={open ? "true" : undefined}
              onClick={
                auth.user?.role === "ROLE_ADMIN"
                  ? handleOpenMenu
                  : navigateToProfile
              }
              className=" font-semibold cursor-pointer"
            >
              <Avatar
                sx={{ bgcolor: "white", color: pink.A400 }}
                className="bg-white"
              >
                {auth.user.fullName[0].toUpperCase()}
              </Avatar>
            </span>
          ) : ( */}
          <IconButton
            className="nav-actions"
            onClick={() => navigate("account/login")}
          >
            <PersonIcon sx={{ fontSize: "2rem" }} />
          </IconButton>
          {/* )} */}
          <Menu
            id="basic-menu"
            anchorEl={anchorEl}
            open={open}
            onClose={handleCloseMenu}
            MenuListProps={{
              "aria-labelledby": "basic-button",
            }}
          >
            <MenuItem onClick={() => navigate("/my-profile")}>Profile</MenuItem>
            <MenuItem>Logout</MenuItem>
          </Menu>
        </div>

        {false && (
          <IconButton className="nav-actions" onClick={() => navigate("/cart")}>
            <Badge color="black" badgeContent={"0"}>
              <ShoppingCartIcon
                className="text-4xl"
                sx={{ fontSize: "2rem" }}
              />
            </Badge>
          </IconButton>
        )}
      </div>
      <Auth />
    </div>
  );
};

export default Navbar;
