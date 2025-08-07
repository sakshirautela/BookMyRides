package com.makemyrides.backend.controller;

import com.makemyrides.backend.dto.RideDto;
import com.makemyrides.backend.entity.Ride;
import com.makemyrides.backend.service.RideService; // Assuming you have a RideService
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rides") // Set a base path for the controller
public class RideController {

    private final RideDto rideDto;

    public RideController(RideDto rideDto) {
        this.rideDto = rideDto;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RideDto> getRide(@PathVariable Long id) {
        RideDto rides = rideDto.getRideById(id);
        return ResponseEntity.ok(rides);
    }
    @PostMapping
    public ResponseEntity<RideDto> saveRide(@RequestBody RideDto rideDto) {
        RideDto savedRide = rideDto.saveRide(rideDto);
        return new ResponseEntity<>(savedRide, HttpStatus.CREATED);
    }
}