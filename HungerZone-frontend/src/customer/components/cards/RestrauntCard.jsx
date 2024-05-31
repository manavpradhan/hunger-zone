import React from "react";
import {
  CardActions,
  CardContent,
  CardMedia,
  Card,
  Typography,
  IconButton,
} from "@mui/material";
import { Favorite, FavoriteBorder } from "@mui/icons-material";
import "./card.css";
import { useNavigate } from "react-router-dom";

const RestrauntCard = ({ item, index }) => {
  const navigate = useNavigate();

  return (
    <Card
      className="m-4 w-[18rem] restraunt-card"
      onClick={() =>
        navigate(`restraunt/${item.city}/${item.name}/${index + 1}`)
      }
    >
      <CardMedia
        className="w-full h-[10rem] rounded-t-lg object-cover"
        component={"img"}
        image={item.imageUrl}
        alt=""
      />
      <div className="flex justify-between relative">
        <CardContent className="p-4 textPart">
          <Typography className="font-semibold text-lg text-red-500">
            {item.name}
          </Typography>
          <Typography className="text-gray-300 py-2">
            {item.description.length > 40
              ? item.description.substring(0, 40) + "...."
              : item.description}
          </Typography>
        </CardContent>
        <CardActions className="top-0 right-0 absolute">
          <IconButton>
            {true ? <Favorite color="primary" /> : <FavoriteBorder />}
          </IconButton>
        </CardActions>
      </div>
    </Card>
  );
};

export default RestrauntCard;
