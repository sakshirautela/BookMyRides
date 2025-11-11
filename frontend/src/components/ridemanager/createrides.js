import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const CreateRide = () => {
  const [rideData, setRideData] = useState({
    driverId: "",
    vehicleId: "",
    type: "",
    fromLocation: "",
    toLocation: "",
    date: "",
    time: ""
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    setRideData({ ...rideData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const token = localStorage.getItem("token");

    const response = await fetch("http://localhost:8080/api/rides", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`
      },
      body: JSON.stringify(rideData)
    });

    if (response.ok) {
      navigate("/rides");
    } else {
      alert("Ride creation failed");
    }
  };

  return (
    <div>
      <h2>Create Ride</h2>
      <form onSubmit={handleSubmit}>
        <input name="driverId" placeholder="Driver ID" onChange={handleChange} required /><br />
        <input name="vehicleId" placeholder="Vehicle ID" onChange={handleChange} required /><br />
        <input name="type" placeholder="Ride Type" onChange={handleChange} required /><br />
        <input name="fromLocation" placeholder="From" onChange={handleChange} required /><br />
        <input name="toLocation" placeholder="To" onChange={handleChange} required /><br />
        <input name="date" type="date" onChange={handleChange} required /><br />
        <input name="time" type="time" onChange={handleChange} required /><br />
        <button type="submit">Create Ride</button>
      </form>
    </div>
  );
};

export default CreateRide;
