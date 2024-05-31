import { Home } from "@mui/icons-material";
import { Button, Card } from "@mui/material";
import React from "react";

const AddressCard = ({ idx, item, selectedAddress, setSelectedAdress }) => {
  const thisCard = "card-" + idx;

  const handleSelect = () => {
    setSelectedAdress(thisCard);
  };
  return (
    <Card className="flex space-x-5 w-64 p-5">
      <Home />

      <div className="space-y-3 text-gray-500">
        <h1 className="font-semibold text-lg text-white">
          {item.type || "Home"}
        </h1>
        <p>
          {item.streetAddress}, {item.city} {item.postalCode}, {item.state}
          {/* {`${address?.streetAddress} ${address?.city} ${address?.state} ${address?.zipCode}`} */}
        </p>

        {true &&
          (selectedAddress === thisCard ? (
            <Button variant="outlined" className="w-full" color="success">
              Deliver Here
            </Button>
          ) : (
            <Button
              variant="outlined"
              className="w-full"
              onClick={() => handleSelect()}
              color="primary"
            >
              select
            </Button>
          ))}
      </div>
    </Card>
  );
};

export default AddressCard;
