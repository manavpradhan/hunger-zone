import { Delete, LocationCity, LocationOnSharp } from "@mui/icons-material";
import {
  Card,
  CardActions,
  CardContent,
  CardMedia,
  IconButton,
  Typography,
} from "@mui/material";
import React from "react";

const EventCard = ({ item, isCustomer }) => {
  return (
    <Card>
      <CardMedia
        sx={{ height: 145, width: 300, objectFit: "cover" }}
        image="https://images.pexels.com/photos/291528/pexels-photo-291528.jpeg?auto=compress&cs=tinysrgb&w=800"
      />
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          {"restaurant name"}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          {"Event name"}
        </Typography>
        <div className="py-2 space-y-2">
          <div className="flex items-center gap-2 mb-4 mt-2">
            <LocationOnSharp className="text-red-500" />
            <p>{"Event location"}</p>
          </div>
          <p className="text-sm text-blue-500">{"item.startedAt"}</p>
          <p className="text-sm text-red-500">{"item.endsAt"}</p>
        </div>
      </CardContent>
      {!true && (
        <CardActions>
          <IconButton aria-label="add to favorites">
            <Delete />
          </IconButton>
        </CardActions>
      )}
    </Card>
  );
};

export default EventCard;
