import React from "react";
import OrderCard from "../../../components/cards/OrderCard";

const orderItem = [
  {
    name: "Biriyani",
    price: 299,
    image:
      "https://cdn.pixabay.com/photo/2022/06/14/18/57/chicken-biryani-7262650_640.jpg",
    qty: 3,
    totalAmt: 897,
    status: "pending",
  },
  {
    name: "Biriyani",
    price: 299,
    image:
      "https://cdn.pixabay.com/photo/2022/06/14/18/57/chicken-biryani-7262650_640.jpg",
    qty: 3,
    totalAmt: 897,
    status: "pending",
  },
  {
    name: "Biriyani",
    price: 299,
    image:
      "https://cdn.pixabay.com/photo/2022/06/14/18/57/chicken-biryani-7262650_640.jpg",
    qty: 3,
    totalAmt: 897,
    status: "pending",
  },
];

const MyOrders = () => {
  return (
    <div className="lg:w-[80%] min-h-[80vh]">
      <h1 className="py-10 text-2xl text-center font-semibold">My Orders</h1>
      <div className="space-y-5 w-full lg:w-[40%] m-auto">
        {orderItem.map((item, idx) => {
          return <OrderCard key={idx} orderItem={item} />;
        })}
      </div>
    </div>
  );
};

export default MyOrders;
