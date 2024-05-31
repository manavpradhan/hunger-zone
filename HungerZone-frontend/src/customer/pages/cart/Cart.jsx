import React, { useState } from "react";
import CartItemCard from "../../components/cards/CartItemCard";
import {
  Box,
  Button,
  Card,
  Divider,
  Grid,
  Modal,
  TextField,
} from "@mui/material";
import AddressCard from "../../components/cards/AddressCard";
import { AddLocation } from "@mui/icons-material";
import { Formik, Field, Form, ErrorMessage } from "formik";
import * as Yup from "yup";

const item = {
  name: "Biriyani",
  price: 299,
  image:
    "https://cdn.pixabay.com/photo/2022/06/14/18/57/chicken-biryani-7262650_640.jpg",
  description: "description",
  quantity: 3,
};

const address = [];

const initialValues = {
  streetAddress: "",
  city: "",
  pincode: "",
  state: "",
};

const validationSchema = Yup.object().shape({
  streetAddress: Yup.string().required("Street Address is required"),
  city: Yup.string().required("City is required"),
  pincode: Yup.string()
    .required("Pincode is required")
    .matches(/^\d{6}$/, "Pincode must be 6 digits"),
  state: Yup.string().required("State is required"),
});

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "background.paper",
  boxShadow: 24,
  outline: "none",
  p: 4,
};

const Cart = () => {
  const [selectedAddress, setSelectedAddress] = useState("card-0");
  const [openModal, setOpenModal] = useState(false);

  const handleCreateOrder = () => {
    alert("order created");
  };

  const handleSubmitAddress = (values, { resetForm }) => {
    const data = {
      streetAddress: values.streetAddress,
      city: values.city,
      state: values.state,
      postalCode: values.pincode,
      type: values.type,
    };
    address.push(data);

    setOpenModal(false);
  };

  return (
    <>
      <main className="lg:flex justify-between relative p-0 m-0 h-[100%]">
        <section className="lg:w-[25%] space-y-6 lg:min-h-screen mt-10 overflow-auto h-[80%]">
          <h1 className="text-4xl text-center mb-10">Your Cart Items</h1>
          {/* {cart.cartItems.map((item, i) => (
              <CartItemCard item={item} />
            ))} */}
          <CartItemCard item={item} />
          <CartItemCard item={item} />
          <CartItemCard item={item} />
          <CartItemCard item={item} />
        </section>
        <Divider orientation="vertical" flexItem />
        <section className="lg:w-[50%] flex flex-col justify-between px-5 mt-10 h-[80vh]">
          <div className="relative">
            <h1 className=" text-4xl text-center mb-10">
              Choose Delivery Address
            </h1>
            <div className="flex gap-5 flex-wrap justify-center mt-16">
              {address?.map((item, idx) => {
                return (
                  <AddressCard
                    key={idx}
                    idx={idx}
                    item={item}
                    selectedAddress={selectedAddress}
                    setSelectedAdress={setSelectedAddress}
                  />
                );
              })}
              <Card className="flex space-x-5 w-64 p-5">
                <AddLocation />
                <div className="space-y-3 text-gray-500 flex flex-col gap-5 justify-between">
                  <h1 className="font-semibold text-lg text-white">
                    Add New Address
                  </h1>

                  <Button
                    variant="contained"
                    className="w-full"
                    color="warning"
                    onClick={() => setOpenModal(true)}
                  >
                    Add
                  </Button>
                </div>
              </Card>
            </div>
          </div>
          <div className="flex items-center justify-center">
            <Button
              variant="outlined"
              className="w-[20%] h-[4rem]"
              sx={{
                background: "#e91e63",
                ":hover": { background: "#ce1f59" },
                color: "white",
                wordSpacing: "5px",
                fontSize: "1.2rem",
              }}
              onClick={() => handleCreateOrder()}
            >
              Place Order
            </Button>
          </div>
        </section>
        <Divider orientation="vertical" flexItem />
        <section className="lg:w-[25%] space-y-6 lg:min-h-screen mt-10 overflow-auto">
          <h1 className="text-4xl text-center mb-10">Bill Details</h1>
          <div className="billDetails px-5 text-sm ">
            <div className="space-y-3">
              <div className="flex justify-between text-gray-400">
                <p>Item Total</p>
                {/* <p>₹{cartTotal(cart.cartItems)}</p> */}
                <p>₹{"Total AMount"}</p>
              </div>
              <div className="flex justify-between text-gray-400">
                <p>Deliver Fee</p>
                <p>₹21</p>
              </div>
              <div className="flex justify-between text-gray-400">
                <p>Plateform Fee</p>
                <p>₹5</p>
              </div>
              <div className="flex justify-between text-gray-400">
                <p>GST and Restaurant Charges</p>
                <p>₹33</p>
              </div>
              <Divider />
              <div className="flex justify-between text-gray-400">
                <p>Total Pay</p>
                {/* <p>₹{cartTotal(cart.cartItems)+33}</p> */}
                <p>₹{"total amount + 33"}</p>
              </div>
            </div>
          </div>
        </section>
      </main>
      <Modal open={openModal} onClose={() => setOpenModal(false)}>
        <Box sx={style}>
          <Formik
            initialValues={initialValues}
            validationSchema={validationSchema}
            onSubmit={handleSubmitAddress}
          >
            <Form>
              <Grid container spacing={2}>
                <Grid item xs={12}>
                  <Field
                    name="streetAddress"
                    as={TextField}
                    label="Street Address"
                    fullWidth
                    variant="outlined"
                    error={!ErrorMessage("streetAddress")}
                    helperText={
                      <ErrorMessage name="streetAddress">
                        {(msg) => <span className="text-red-600">{msg}</span>}
                      </ErrorMessage>
                    }
                  />
                </Grid>
                <Grid item xs={7}>
                  <Field
                    name="city"
                    as={TextField}
                    label="City"
                    fullWidth
                    variant="outlined"
                    error={!ErrorMessage("city")}
                    helperText={
                      <ErrorMessage name="city">
                        {(msg) => <span className="text-red-600">{msg}</span>}
                      </ErrorMessage>
                    }
                  />
                </Grid>
                <Grid item xs={5}>
                  <Field
                    name="pincode"
                    as={TextField}
                    label="Pincode"
                    fullWidth
                    variant="outlined"
                    error={!ErrorMessage("pincode")}
                    helperText={
                      <ErrorMessage name="pincode">
                        {(msg) => <span className="text-red-600">{msg}</span>}
                      </ErrorMessage>
                    }
                  />
                </Grid>
                <Grid item xs={8}>
                  <Field
                    name="state"
                    as={TextField}
                    label="State"
                    fullWidth
                    variant="outlined"
                    error={!ErrorMessage("state")}
                    helperText={
                      <ErrorMessage name="state">
                        {(msg) => <span className="text-red-600">{msg}</span>}
                      </ErrorMessage>
                    }
                  />
                </Grid>
                <Grid item xs={4}>
                  <Field
                    name="type"
                    as={TextField}
                    label="type"
                    fullWidth
                    variant="outlined"
                  />
                </Grid>
                <Grid item xs={12} className="flex justify-center">
                  <Button
                    type="submit"
                    variant="contained"
                    color="primary"
                    className="w-[50%]"
                  >
                    Deliver Here
                  </Button>
                </Grid>
              </Grid>
            </Form>
          </Formik>
        </Box>
      </Modal>
    </>
  );
};

export default Cart;
