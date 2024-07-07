import React, { useState } from "react";
import { Formik, Field, Form, ErrorMessage } from "formik";
import * as Yup from "yup";
import { Button, Grid, TextField } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { Visibility, VisibilityOff } from "@mui/icons-material";

const initialValues = {
  emailId: "",
  password: "",
};

const loginValidationSchema = Yup.object().shape({
  emailId: Yup.string().required("Email Address is required"),
  password: Yup.string().required("Password is required"),
});

const Login = () => {
  const navigate = useNavigate();
  const [showPassword, setShowPassword] = useState(true);

  const handleLogin = () => {};

  return (
    <div>
      <Formik
        initialValues={initialValues}
        validationSchema={loginValidationSchema}
        onSubmit={handleLogin}
      >
        <Form>
          <h1 className="text-center text-3xl mb-10">LOGIN</h1>
          <Grid container spacing={2}>
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
            <Grid
              item
              xs={12}
              className="flex justify-center items-center gap-8"
            >
              <Button
                type="submit"
                variant="contained"
                color="primary"
                className="w-[50%]"
              >
                Login
              </Button>
              <p className="cursor-pointer hover:text-red-600 transition-all">
                Forgot Password?
              </p>
            </Grid>
          </Grid>
        </Form>
      </Formik>
      <div className="flex mt-10 items-center justify-center gap-5">
        <h2>Don't have an account yet?</h2>
        <p
          className="text-xl font-semibold text-red-500 cursor-pointer"
          onClick={() => navigate("account/register")}
        >
          Sign Up
        </p>
      </div>
    </div>
  );
};

export default Login;
