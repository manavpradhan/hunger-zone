import React, { useState } from "react";
import { Formik, Field, Form, ErrorMessage } from "formik";
import * as Yup from "yup";
import {
  Button,
  FormControlLabel,
  Grid,
  Radio,
  RadioGroup,
  TextField,
} from "@mui/material";
import { useNavigate } from "react-router-dom";
import { RemoveRedEye, Visibility, VisibilityOff } from "@mui/icons-material";

const initialValues = {
  firstName: "",
  lastName: "",
  emailId: "",
  password: "",
  confirmPassword: "",
  role: "ROLE_CUSTOMER",
};

const registerValidationSchema = Yup.object().shape({
  firstName: Yup.string().required("First name is required"),
  lastName: Yup.string(),
  emailId: Yup.string().required("Email Address is required"),
  password: Yup.string()
    .required("Password is required")
    .min(6, "Password must have min 6 characters"),
  confirmPassword: Yup.string().oneOf(
    [Yup.ref("password")],
    "Passwords must match"
  ),
});

const Register = () => {
  const navigate = useNavigate();
  const [showPassword, setShowPassword] = useState(true);

  const handleRegister = (value) => {
    console.log(value);
  };

  return (
    <div>
      <Formik
        initialValues={initialValues}
        validationSchema={registerValidationSchema}
        onSubmit={handleRegister}
      >
        <Form>
          <h1 className="text-center text-3xl mb-10">REGISTER</h1>
          <Grid container spacing={2}>
            <Grid item xs={6}>
              <Field
                name="firstName"
                as={TextField}
                label="First Name"
                fullWidth
                variant="outlined"
                error={!ErrorMessage("firstName")}
                helperText={
                  <ErrorMessage name="firstName">
                    {(msg) => <span className="text-red-600">{msg}</span>}
                  </ErrorMessage>
                }
              />
            </Grid>
            <Grid item xs={6}>
              <Field
                name="lastName"
                as={TextField}
                label="Last Name"
                fullWidth
                variant="outlined"
                error={!ErrorMessage("lastName")}
                helperText={
                  <ErrorMessage name="lastName">
                    {(msg) => <span className="text-red-600">{msg}</span>}
                  </ErrorMessage>
                }
              />
            </Grid>
            <Grid item xs={12}>
              <Field
                name="emailId"
                as={TextField}
                label="Email Address"
                fullWidth
                variant="outlined"
                error={!ErrorMessage("emailId")}
                helperText={
                  <ErrorMessage name="emailId">
                    {(msg) => <span className="text-red-600">{msg}</span>}
                  </ErrorMessage>
                }
              />
            </Grid>
            <Grid item xs={12} className="flex justify-center items-center">
              <Field
                name="password"
                as={TextField}
                label="Password"
                fullWidth
                variant="outlined"
                type={showPassword ? "text" : "password"}
                error={!ErrorMessage("password")}
                helperText={
                  <ErrorMessage name="password">
                    {(msg) => <span className="text-red-600">{msg}</span>}
                  </ErrorMessage>
                }
              />
              {showPassword ? (
                <Visibility
                  onClick={() => setShowPassword(!showPassword)}
                  className="absolute right-14 cursor-pointer"
                />
              ) : (
                <VisibilityOff
                  onClick={() => setShowPassword(!showPassword)}
                  className="absolute right-14 cursor-pointer"
                />
              )}
            </Grid>
            <Grid item xs={12} className="flex justify-center items-center">
              <Field
                name="confirmPassword"
                as={TextField}
                label="Confirm Password"
                fullWidth
                variant="outlined"
                type={showPassword ? "text" : "password"}
                error={!ErrorMessage("confirmPassword")}
                helperText={
                  <ErrorMessage name="confirmPassword">
                    {(msg) => <span className="text-red-600">{msg}</span>}
                  </ErrorMessage>
                }
              />
              {showPassword ? (
                <Visibility
                  onClick={() => setShowPassword(!showPassword)}
                  className="absolute right-14 cursor-pointer"
                />
              ) : (
                <VisibilityOff
                  onClick={() => setShowPassword(!showPassword)}
                  className="absolute right-14 cursor-pointer"
                />
              )}
            </Grid>
            <Grid item xs={12} className="flex justify-center">
              <Field name="role" as={RadioGroup} row>
                <FormControlLabel
                  value="ROLE_RESTAURANT_OWNER"
                  control={<Radio />}
                  label="Restaurant Owner"
                />
                <FormControlLabel
                  value="ROLE_CUSTOMER"
                  control={<Radio />}
                  label="Customer"
                />
              </Field>
            </Grid>
            <Grid item xs={12} className="flex justify-center items-center">
              <Button
                type="submit"
                variant="contained"
                color="primary"
                className="w-[50%]"
              >
                Register
              </Button>
            </Grid>
          </Grid>
        </Form>
      </Formik>
      <div className="flex mt-10 items-center justify-center gap-5">
        <h2>Already have an account?</h2>
        <p
          className="text-xl font-semibold text-red-500 cursor-pointer"
          onClick={() => navigate("account/login")}
        >
          Sign In
        </p>
      </div>
    </div>
  );
};

export default Register;
