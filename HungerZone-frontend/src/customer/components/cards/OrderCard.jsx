import { Button, Card } from "@mui/material";
import React from "react";

const OrderCard = ({ orderItem }) => {
  return (
    <Card className="flex justify-between items-center p-5">
      <div className="flex items-center space-x-5">
        <img className="h-16 w-16" src={orderItem.image} alt="" />
        <div className="flex flex-col gap-3">
          <p className="text-gray-300">Price: {orderItem.price}</p>
          <p className="text-gray-300">Qty: {orderItem.qty}</p>
        </div>
      </div>
      <div className="flex w-[50%] justify-between">
        <div className="flex flex-col w-[50%] justify-center items-center gap-5">
          <span className="font-semibold">{orderItem.name}</span>
          <span className="font-semibold">
            Total Amount: {orderItem.totalAmt}
          </span>
        </div>
        <Button className="cursor-not-allowed" variant="outlined">
          {orderItem.status}
        </Button>
      </div>
    </Card>
  );
};

export default OrderCard;
