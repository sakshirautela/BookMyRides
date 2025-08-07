package com.makemyrides.backend.service;

import com.makemyrides.backend.dto.RideDto;
import com.makemyrides.backend.entity.Ride;
import com.makemyrides.backend.repository.RideRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RideService {

    private final RideRepository rideRepository;
    private final ModelMapper modelMapper;

    public RideService(RideRepository rideRepository, ModelMapper modelMapper) {
        this.rideRepository = rideRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public RideDto getRideById(Long id) {
        Ride ride = rideRepository.findById(id);//.orElseThrow(() -> new EntityNotFoundException("Ride not found with ID: " + id));
        return modelMapper.map(ride, RideDto.class);
    }

    @Transactional
    public RideDto saveRide(RideService rideService) {
        Ride ride = modelMapper.map(rideService, Ride.class);
        Ride savedRide = (Ride) rideRepository.saveAndFlush(ride);
        return modelMapper.map(savedRide, RideDto.class);
    }
}