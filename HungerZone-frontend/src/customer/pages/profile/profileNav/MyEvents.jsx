import React from "react";
import EventCard from "../../../components/cards/EventCard";

const events = [{}];

const MyEvents = () => {
  return (
    <div className="lg:w-[80%] min-h-[80vh]">
      <h1 className="py-10 text-2xl text-center font-semibold">My Events</h1>
      <div className="w-[80%] flex flex-wrap justify-center gap-8 m-auto">
        {[1, 1, 1, 1].map((item, idx) => {
          return <EventCard />;
        })}
      </div>
    </div>
  );
};

export default MyEvents;
