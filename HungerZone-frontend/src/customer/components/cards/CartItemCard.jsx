import React from "react";
import { Button, Chip, Divider, IconButton } from "@mui/material";
import { RemoveCircleOutline, AddCircleOutline } from "@mui/icons-material";

const CartItemCard = ({ item }) => {
  const totalPrice = item.quantity * item.price;

  const handleUpdateCart = (value) => {
    if (value === -1 && item.quantity === 1) {
      handleRemoveCartItem();
    }

    const cartData = { cartItemId: "item.id", quantity: item.quantity + value };

    //dispatch cartData;
  };

  const handleRemoveCartItem = () => {
    //dispatch removeCartItem;
  };
  return (
    <div className="px-5 w-full">
      <div className="lg:flex items-center lg:space-x-5">
        <div>
          <img
            className="w-[5rem] h-[5rem] object-cover rounded-md"
            src={
              "https://cdn.pixabay.com/photo/2022/06/14/18/57/chicken-biryani-7262650_640.jpg"
            }
            alt=""
          />
        </div>

        <div className="flex items-center justify-between lg:w-[80%]">
          <div className="space-y-1 lg:space-y-3 w-[75%] flex flex-col items-start">
            <p className="">{item.name}</p>
            <div className="flex items-center justify-center">
              <IconButton color="primary" onClick={() => handleUpdateCart(-1)}>
                <RemoveCircleOutline />
              </IconButton>
              <div className="text-black w-[3rem] h-[2rem] flex items-center justify-center outline-none rounded-md bg-slate-50">
                {item.quantity}
              </div>
              <IconButton color="primary" onClick={() => handleUpdateCart(1)}>
                <AddCircleOutline />
              </IconButton>
            </div>
          </div>
          <div className="space-y-1 lg:space-y-3 flex flex-col items-center justify-center w-[25%]">
            <p>{`${item.quantity} X ${item.price}`}</p>
            <p>₹{totalPrice}</p>
          </div>
        </div>
      </div>
      {/* <div className="pt-3 space-x-2">
        {item.ingredients.map((item) => (
          <Chip label={item} />
        ))}
      </div> */}
    </div>
  );
};

export default CartItemCard;
