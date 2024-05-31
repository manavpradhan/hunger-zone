import { useState } from "react";
import "./App.css";
import { CssBaseline, ThemeProvider } from "@mui/material";
import { darkTheme } from "./theme/darkTheme.js";
import MainRouter from "./routes/MainRouter.jsx";
import Navbar from "./customer/components/navbar/Navbar.jsx";

function App() {
  const [count, setCount] = useState(0);

  return (
    <ThemeProvider theme={darkTheme}>
      <CssBaseline />
      {/* header */}
      <Navbar />
      <MainRouter />
    </ThemeProvider>
  );
}

export default App;
