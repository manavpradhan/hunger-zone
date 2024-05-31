import React from "react";
import "./HomePage.css";
import Carousel from "../../components/carousel/Carousel";
import { restaurants } from "../../../data/topRestraunts.js";
import RestrauntCard from "../../components/cards/RestrauntCard.jsx";

const Homepage = () => {
  return (
    <div>
      <section className="-z-40 banner relative flex flex-col items-center justify-center">
        <div className="w-[50vw] text-center z-10 ">
          <p className="text-2xl lg:text-7xl font-bold py-5">Hunger Zone</p>
          <p className="text-gray-300 text-xl lg:text-4xl">
            You hungry? Well, we've got your cravings!
          </p>
        </div>
        <div className="opacity-layer top-0 left-0 right-0 absolute"></div>
        <div className="fade-out"></div>
      </section>
      <section className="p-10 lg:py-10 lg:px-20">
        <div>
          <p className="text-2xl font-semibold text-gray-400 py-3 pb-10">
            What's on your mind?
          </p>
        </div>
        <Carousel />
      </section>
      <section className="px-10 lg:px-20">
        <div>
          <h1 className="text-2xl font-semibold text-gray-400 py-3 pb-10">
            Top restraunts near you
          </h1>
          <div className="flex flex-wrap justify-center items-center">
            {restaurants.map((item, idx) => {
              return <RestrauntCard key={idx} item={item} index={idx} />;
            })}
          </div>
        </div>
      </section>
    </div>
  );
};

export default Homepage;
