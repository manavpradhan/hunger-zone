import React, { useState } from "react";
import AddressCard from "../../../components/cards/AddressCard";
import { Box, Button, Card, Grid, Modal, TextField } from "@mui/material";
import { AddLocation } from "@mui/icons-material";
import { Formik, Field, Form, ErrorMessage } from "formik";
import * as Yup from "yup";

const initialValues = {
  streetAddress: "123, sector 12",
  city: "Dwarka",
  postalCode: "110045",
  state: "New Delhi",
};

const myAddress = [];

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

const MyAddresses = () => {
  const [selectedAddress, setSelectedAddress] = useState("card-0");
  const [openModal, setOpenModal] = useState(false);

  const handleSubmitAddress = (values, { resetForm }) => {
    const data = {
      streetAddress: values.streetAddress,
      city: values.city,
      state: values.state,
      postalCode: values.pincode,
      type: values.type,
    };
    myAddress.push(data);

    setOpenModal(false);
  };

  return (
    <>
      <div className="lg:w-[80%] min-h-[80vh]">
        <h1 className="py-10 text-2xl text-center font-semibold">
          My Addresses
        </h1>
        <div className="w-[80%] flex flex-wrap justify-center gap-8 m-auto">
          {[1, 1, 1, 1, 1].map((item, idx) => {
            return (
              <AddressCard
                key={idx}
                idx={idx}
                item={initialValues}
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

export default MyAddresses;
