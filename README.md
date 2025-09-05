MakeMyRides

MakeMyRides is a ride-sharing API that allows users to create rides, add passengers, and manage vehicle details. The project provides endpoints to manage rides, passengers, and vehicles efficiently.

Features

Create Vehicles: Register vehicles with details like model, seats, registration number, license plate, and owner.

Create Rides: Schedule rides with driver details, locations, time, and date.

Add Passengers: Add passengers to rides with seat allocations.

Prevent Duplicate Passengers: Avoid adding the same passenger multiple times to a ride.

API Endpoints
1. Vehicle Management

POST /api/vehicles

Registers a new vehicle.

Request Body:

{
  "model": "Honda Amaze",
  "seats": 4,
  "year": 2023,
  "registrationNumber": "ighgdihhh",
  "licensePlate": "MH1klhafffdlk2AB9999",
  "ownerId": 1
}


Response:

{
  "id": 4,
  "model": "Honda Amaze",
  "seats": 4,
  "year": 2023,
  "registrationNumber": "ighgdihhh",
  "licensePlate": "MH1klhafffdlk2AB9999",
  "ownerId": 1
}

2. Ride Management

POST /api/rides/{rideId}/passengers/{userId}?seats={seatCount}

Adds a passenger to a ride.

Response (Successful Addition):

{
  "id": 5,
  "driverName": "Sakshi",
  "vehicleNumber": "MH1kljlk2AB9999",
  "fromLocation": "New York, NY",
  "toLocation": "Boston, MA",
  "passengers": [
    {
      "userId": 2,
      "seats": 2
    }
  ],
  "time": "09:30:00",
  "date": "2025-08-08"
}


Response (Duplicate Passenger Error):

{
  "message": "Passenger already added to this ride",
  "path": "/api/rides/1/passengers/2"
}

Error Handling

500 Internal Server Error: Occurs when trying to add the same passenger multiple times.

Validation errors are returned as JSON with meaningful messages.

Tech Stack

Backend: Java (Spring Boot)

Database: (Not provided in screenshots, likely MySQL/PostgreSQL)

Server: Apache Tomcat

How to Run

Clone the repository.

Set up the database and configure connection properties in application.properties.

Build and run the Spring Boot application:

mvn spring-boot:run


Test endpoints using Postman or any API client.
