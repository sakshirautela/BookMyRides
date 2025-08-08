// components/RideList.jsx
import React, { useEffect, useState } from "react";

const RideList = () => {
  const [rides, setRides] = useState([]);

  useEffect(() => {
    const fetchRides = async () => {
      const token = localStorage.getItem("token");

      const response = await fetch("http://localhost:8080/api/rides", {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });

      if (response.ok) {
        const data = await response.json();
        setRides(data);
      }
    };

    fetchRides();
  }, []);

  return (
    <div>
      <h2>Available Rides</h2>
      <ul>
        {rides.map((ride) => (
          <li key={ride.id}>
            {ride.fromLocation} → {ride.toLocation} on {ride.date} at {ride.time}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default RideList;
