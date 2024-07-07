import React from "react";
import StorefrontIcon from "@mui/icons-material/Storefront";
import FavoriteIcon from "@mui/icons-material/Favorite";
import HomeIcon from "@mui/icons-material/Home";
import NotificationsActiveIcon from "@mui/icons-material/NotificationsActive";
import LocalActivityIcon from "@mui/icons-material/LocalActivity";
import LogoutIcon from "@mui/icons-material/Logout";
import { Divider, Drawer, useMediaQuery } from "@mui/material";
import { useNavigate } from "react-router-dom";

const navItems = [
  { title: "Orders", icon: <StorefrontIcon /> },
  { title: "Favorites", icon: <FavoriteIcon /> },
  { title: "Addresses", icon: <HomeIcon /> },
  { title: "Notifications", icon: <NotificationsActiveIcon /> },
  { title: "Events", icon: <LocalActivityIcon /> },
  { title: "Logout", icon: <LogoutIcon /> },
];

const ProfileNav = ({ open, handleClose }) => {
  const navigate = useNavigate();
  const isSmallScreen = useMediaQuery("(max-width: 900px)");

  const handleNavigate = (uri) => {
    navigate(`/my-profile/${uri.toLowerCase()}`);
  };

  return (
    <div>
      <Drawer
        sx={{ zIndex: 1 }}
        anchor="left"
        open={isSmallScreen ? open : true}
        onClose={handleClose}
        variant={isSmallScreen ? "temporary" : "permanent"}
      >
        <div className="w-[50vw] lg:w-[20vw] h-[100%] flex flex-col gap-8 text-xl pt-32">
          {navItems.map((item, idx) => {
            return (
              <>
                <div
                  key={idx}
                  className="flex gap-6 items-center px-5 cursor-pointer"
                  onClick={() => handleNavigate(item.title)}
                >
                  {item.icon}
                  <span className="ml-2">{item.title}</span>
                </div>
                {idx !== navItems.length - 1 && <Divider />}
              </>
            );
          })}
        </div>
      </Drawer>
    </div>
  );
};

export default ProfileNav;

{
  /* <div className="w-[100%] h-[100%] flex flex-col gap-20 pl-10 pt-20">
  {navItems.map((item, idx) => {
    return (
      <div key={idx} className="flex gap-6 items-center w-[100%]">
        <span>{item.icon}</span>
        <span className="ml-2">{item.title}</span>
      </div>
    );
  })}
</div>; */
}
