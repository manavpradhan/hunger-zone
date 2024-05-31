import React from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import CarouselItem from "./CarouselItem";
import { topMeals } from "./../../../data/topMeals.js";

const Carousel = () => {
  const settings = {
    dots: false,
    infinite: true,
    speed: 400,
    slidesToShow: 5,
    slidesToScroll: 1,
    arrows: false,
    autoplay: true,
    autoplaySpeed: 3000,
  };
  return (
    <div className="slider-container">
      <Slider {...settings}>
        {topMeals.map((item, idx) => {
          return (
            <CarouselItem image={item.image} title={item.title} key={idx} />
          );
        })}
      </Slider>
    </div>
  );
};

export default Carousel;
