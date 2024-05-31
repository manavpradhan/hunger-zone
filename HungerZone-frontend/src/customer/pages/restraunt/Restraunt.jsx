import {
  Divider,
  FormControl,
  FormControlLabel,
  Radio,
  RadioGroup,
  Typography,
} from "@mui/material";
import React, { useEffect, useState } from "react";
import MenuCard from "../../components/cards/MenuCard";

const categories = [
  "Starters",
  "Indian Main Course",
  "Rice and Biryani",
  "Breads",
  "Accompaniments",
  "Dessert",
];

const foodTypes = [
  { label: "All", value: "all" },
  { label: "Vegetarian Only", value: "vegetarian" },
  { label: "Non-Vegetarian Only", value: "non_vegetarian" },
  { label: "Seasonal", value: "seasonal" },
];

const Restraunt = () => {
  const [category, setCategory] = useState("");
  const [foodType, setFoodType] = useState("");

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  return (
    <div className="px-5 lg:px-20">
      <section className="restraunt-banner">
        <img
          className="w-full h-[40vh] object-cover opacity-80"
          alt=""
          src="https://images.pexels.com/photos/958545/pexels-photo-958545.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
        />
        <div>
          <h1 className="font-semibold text-4xl py-1">{"restraunt name"}</h1>
          <p className="text-gray-500">{"restraunt description"}</p>
          <p className="text-yellow-500 font-semibold mt-4">
            {"open now (10:30am - 12:00am)"}
          </p>
        </div>
      </section>
      <Divider sx={{ borderBottomColor: "gray", marginTop: "15px" }} />
      <section className="restraunt-menu lg:flex pt-[2rem] relative">
        <div className="space-y-10 lg:w-[20%] ">
          <div className="px-4 lg:sticky top-[112px] absolute">
            <Typography sx={{ paddingBottom: "1rem" }} variant="h5">
              Category
            </Typography>
            <FormControl component={"fieldset"}>
              <RadioGroup
                name="category"
                value={category}
                onChange={(e) => setCategory(e.target.value)}
              >
                {categories.map((item, idx) => (
                  <FormControlLabel
                    key={idx}
                    value={item}
                    control={<Radio />}
                    label={item}
                    sx={{ color: "gray" }}
                  />
                ))}
              </RadioGroup>
            </FormControl>
          </div>
          <Divider
            className="lg:sticky top-[450px] absolute"
            sx={{ borderBottomColor: "gray", width: "60%" }}
          />
          <div className="px-4 lg:sticky top-[500px] absolute">
            <Typography sx={{ paddingBottom: "1rem" }} variant="h5">
              Food Type
            </Typography>
            <FormControl component={"fieldset"}>
              <RadioGroup
                name="foodType"
                value={foodType}
                onChange={(e) => setFoodType(e.target.value)}
              >
                {foodTypes.map((item, idx) => (
                  <FormControlLabel
                    key={idx}
                    value={item.value}
                    control={<Radio />}
                    label={item.label}
                    sx={{ color: "gray" }}
                  />
                ))}
              </RadioGroup>
            </FormControl>
          </div>
        </div>
        <div className="flex flex-1 flex-col gap-5 ">
          <p className="lg:text-3xl w-[100%] h-[5rem] pt-5 text-center lg:sticky top-0 absolute bg-black z-10">
            Our curated Menu
          </p>
          <MenuCard />
          <MenuCard />
          <MenuCard />
          <MenuCard />
          <MenuCard />
          <MenuCard />
          <MenuCard />
        </div>
      </section>
    </div>
  );
};

export default Restraunt;
