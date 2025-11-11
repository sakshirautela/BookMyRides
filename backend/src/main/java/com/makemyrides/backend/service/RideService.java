package com.makemyrides.backend.service;

import com.makemyrides.backend.dto.*;
import com.makemyrides.backend.entity.*;
import com.makemyrides.backend.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RideService {

    private final RideRepository rideRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;
    private final PassengerRepository passengerRepository;
    private final FeedbackRepository feedbackRepository;
    private final ModelMapper modelMapper;

    public RideResponseDTO createRide(RideRequestDTO dto) {
        User driver = userRepository.findById(dto.getDriverId())
                .orElseThrow(() -> new EntityNotFoundException("Driver not found"));
        Vehicle vehicle = vehicleRepository.findById(dto.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));

        Ride ride = new Ride();
        ride.setDriver(driver);
        ride.setVehicle(vehicle);
        ride.setPassengers(new ArrayList<>());
        ride.setDate(dto.getDate());
        ride.setTime(dto.getTime());
        ride.setFromLocation(dto.getFromLocation());
        ride.setToLocation(dto.getToLocation());

        Ride savedRide = rideRepository.save(ride);
        return mapToResponse(savedRide);
    }

    @Transactional(readOnly = true)
    public RideResponseDTO getRideById(Long id) {
        Ride ride = rideRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ride not found"));
        return mapToResponse(ride);
    }

    public void deleteRide(Long id) {
        if (!rideRepository.existsById(id)) {
            throw new EntityNotFoundException("Ride not found");
        }
        rideRepository.deleteById(id);
    }

    public RideResponseDTO addPassengerToRide(Long rideId, Long passengerId, int seats) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new EntityNotFoundException("Ride not found"));
        User passengerUser = userRepository.findById(passengerId)
                .orElseThrow(() -> new EntityNotFoundException("Passenger not found"));

        if (ride.getPassengers().stream().anyMatch(p -> p.getUser().getId().equals(passengerId))) {
            throw new IllegalStateException("Passenger already added to this ride");
        }

        Passenger passenger = new Passenger();
        passenger.setUser(passengerUser);
        passenger.setSeats(seats);
        passenger.setRide(ride);

        passengerRepository.save(passenger);
        ride.getPassengers().add(passenger);
        return mapToResponse(ride);
    }

    public RideResponseDTO removePassengerFromRide(Long rideId, Long passengerId) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new EntityNotFoundException("Ride not found"));
        Passenger passenger = passengerRepository.findByRideIdAndUserId(rideId, passengerId)
                .orElseThrow(() -> new EntityNotFoundException("Passenger not found in this ride"));

        ride.getPassengers().remove(passenger);
        passengerRepository.delete(passenger);
        return mapToResponse(ride);
    }

    private RideResponseDTO mapToResponse(Ride ride) {
        RideResponseDTO rideResponseDTO = new RideResponseDTO();
        rideResponseDTO.setId(ride.getId());
        rideResponseDTO.setDriverName(ride.getDriver().getName());
        rideResponseDTO.setDate(ride.getDate());
        rideResponseDTO.setTime(ride.getTime());
        rideResponseDTO.setFromLocation(ride.getFromLocation());
        rideResponseDTO.setToLocation(ride.getToLocation());
        rideResponseDTO.setVehicleNumber(ride.getVehicle().getLicensePlate());
        rideResponseDTO.setPassengers(
                ride.getPassengers().stream()
                        .map(p -> new PassengerDTO(p.getUser().getId(), p.getSeats()))
                        .collect(Collectors.toList())
        );
        return rideResponseDTO;
    }

    @Transactional(readOnly = true)
    public List<RideResponseDTO> getAllRides(String from, String to, LocalDate date) {
        List<Ride> rides;

        if (from != null && to != null && date != null) {
            rides = rideRepository.findByFromLocationAndToLocationAndDate(from, to, date);
        } else {
            rides = rideRepository.findAll();
        }

        return rides.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }


}
