import React from "react";
import RestrauntCard from "../../../components/cards/RestrauntCard";

const favorites = [
  {
    imageUrl:
      "https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_660/2b4f62d606d1b2bfba9ba9e5386fabb7",
    name: "Pizza Hut",
    rating: "4.7",
    city: "Mumbai",
    description: "Pizzas",
  },
  {
    imageUrl:
      "https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_660/82d66b90554a59d340159fe81d882069",
    name: "Dalmia's Fastfood",
    rating: "3.8",
    city: "Mumbai",
    description: "Snacks",
  },
  {
    imageUrl:
      "https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_660/wnqlcvowxdy0afkimvfs",
    name: "Amritsar dhaba",
    rating: "4.9",
    city: "Mumbai",
    description: "Biryani",
  },
  {
    imageUrl:
      "https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_660/ac5e720e85541abe2cc5c10a3745e669",
    name: "Ambarsariya dhaba",
    rating: "4.2",
    city: "Mumbai",
    description: "Burgers, Beverages",
  },
  {
    imageUrl:
      "https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_660/ifznwbhinqkdtg3xhsbo",
    name: "Brown Burger Co.",
    rating: "4.1",
    city: "Mumbai",
    description: "Burgers, Ice Cream, Pizzas",
  },
  {
    imageUrl:
      "https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_660/475085b5ea76a4da3163febd643d98b0",
    name: "Sam Momo's",
    rating: "4.6",
    city: "Mumbai",
    description: "Chinese, Fast Food, Beverages",
  },
  {
    imageUrl:
      "https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_660/1b59f8e96eb6acea83a5b855e2223eb5",
    name: "Pendu'S",
    rating: "4.3",
    city: "Mumbai",
    description: "Indian, Biryani",
  },
];
const MyFavorites = () => {
  return (
    <div className="lg:w-[80%] min-h-[80vh]">
      <h1 className="py-10 text-2xl text-center font-semibold">My Favorites</h1>
      <div className="w-[80%] flex flex-wrap justify-center gap-3 m-auto">
        {favorites.map((fav, idx) => {
          return <RestrauntCard key={idx} item={fav} />;
        })}
      </div>
    </div>
  );
};

export default MyFavorites;
