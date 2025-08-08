// App.jsx
import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./components/login";
import Register from "./components/registration";
import RideList from "./components/Rides";
import CreateRide from "./components/createrides";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/rides" element={<RideList />} />
        <Route path="/create-ride" element={<CreateRide />} />
      </Routes>
    </Router>
  );
}

export default App;
