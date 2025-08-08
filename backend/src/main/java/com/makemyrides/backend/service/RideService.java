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
import java.util.List;
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
    ModelMapper modelMapper;

    public RideResponseDTO createRide(RideRequestDTO dto) {
        User driver = userRepository.findById(dto.getDriverId()).orElseThrow(() -> new EntityNotFoundException("Driver not found"));
        Vehicle vehicle = vehicleRepository.findById(dto.getVehicleId()).orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));
        Ride ride = modelMapper.map(dto,Ride.class);
        List<Passenger> passengerList = dto.getPassengers().stream().map(p -> {
                    User passengerUser = userRepository.findById(p.getUserId()).orElseThrow(() -> new EntityNotFoundException("Passenger user not found"));
                    Passenger passenger = new Passenger();
                    passenger.setUser(passengerUser);
                    passenger.setSeats(p.getSeats());
                    passenger.setRide(ride);
                    return passenger;
                }).collect(Collectors.toList());
        ride.setPassengers(passengerList);
        Ride savedRide = rideRepository.save(ride);
        return mapToResponse(savedRide);
    }

    public List<RideResponseDTO> getAllRides(String from, String to, LocalDate date) {
        return rideRepository.findByFromLocationAndToLocationAndDate(from, to, date).stream().map(this::mapToResponse).collect(Collectors.toList());
    }


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

    public FeedbackResponseDTO addFeedback(FeedbackRequestDTO dto) {
        Ride ride = rideRepository.findById(dto.getRideId())
                .orElseThrow(() -> new EntityNotFoundException("Ride not found"));
        User user = userRepository.findById(dto.getRideId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Feedback feedback = Feedback.builder()
                .ride(ride)
                .user(user)
                .rating(dto.getRating())
                .comment(dto.getComment())
                .build();
        feedbackRepository.save(feedback);
        return new FeedbackResponseDTO(feedback.getId(), feedback.getRating(), feedback.getComment());
    }

    private RideResponseDTO mapToResponse(Ride ride) {
        RideResponseDTO rideResponseDTO=new RideResponseDTO();
        rideResponseDTO.setDate(ride.getDate());
        rideResponseDTO.setTime(ride.getTime());
        rideResponseDTO.setFromLocation(ride.getFromLocation());
        rideResponseDTO.setToLocation(ride.getToLocation());
        rideResponseDTO.setVehicleNumber(ride.getVehicle().getLicensePlate());
        rideResponseDTO.setPassengerId((List<Long>) ride.getPassengers().stream().map(p->p.getUser().getId()));
        return rideResponseDTO;
    }
}
