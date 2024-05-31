import { Button, IconButton } from "@mui/material";
import React, { useEffect, useState } from "react";
import { RemoveCircleOutline, AddCircleOutline } from "@mui/icons-material";

const item = {
  name: "Biriyani",
  price: 299,
  image:
    "https://cdn.pixabay.com/photo/2022/06/14/18/57/chicken-biryani-7262650_640.jpg",
  description: "description",
};

const MenuCard = () => {
  const [quantity, setQuantity] = useState(1);

  const decreaseQuant = () => {
    if (quantity > 1) {
      setQuantity(quantity - 1);
    }
  };

  const increaseQuant = () => {
    setQuantity(quantity + 1);
  };

  const addToCart = (item, qty) => {
    const newItem = {
      ...item,
      quantity: qty,
    };

    var cartItems = JSON.parse(localStorage.getItem("cartItems") || "[]");
    cartItems.push(newItem);
    localStorage.setItem("cartItems", JSON.stringify(cartItems));
  };

  return (
    <div className="lg:flex items-center justify-between box-border bg-zinc-800 px-5 py-5 rounded-lg">
      <div className="lg:flex items-center lg:space-x-5">
        <img
          className="w-[7rem] h-[7rem] object-cover rounded-md"
          alt=""
          src={item.image}
        />
        <div className="flex flex-col justify-between space-y-2 lg:space-y-5 lg:max-w-2xl">
          <p className="font-semibold text-xl">{item.name}</p>
          <p>₹​{item.price}</p>
          <p className="text-gray-400">{item.description}</p>
        </div>
      </div>
      <div className="flex items-center gap-3">
        <div className="flex items-center justify-center gap-2">
          {/* <button
            onClick={decreaseQuant}
            className="w-[2rem] h-[2rem] rounded-full bg-gray-500 flex items-center justify-center"
          >
            -
          </button> */}
          <IconButton color="primary" onClick={decreaseQuant}>
            <RemoveCircleOutline />
          </IconButton>
          <div className="text-black w-[3rem] h-[2rem] flex items-center justify-center outline-none rounded-md bg-slate-50">
            {quantity}
          </div>
          <IconButton color="primary" onClick={increaseQuant}>
            <AddCircleOutline />
          </IconButton>
          {/* <button
            onClick={increaseQuant}
            className="w-[2rem] h-[2rem] rounded-full bg-gray-500 flex items-center justify-center"
          >
            +
          </button> */}
        </div>
        <Button onClick={() => addToCart(item, quantity)}>Add to cart</Button>
      </div>
    </div>
  );
};

export default MenuCard;
