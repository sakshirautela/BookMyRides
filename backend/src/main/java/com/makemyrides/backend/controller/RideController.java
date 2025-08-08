package com.makemyrides.backend.controller;

import com.makemyrides.backend.dto.FeedbackRequestDTO;
import com.makemyrides.backend.dto.FeedbackResponseDTO;
import com.makemyrides.backend.dto.RideRequestDTO;
import com.makemyrides.backend.dto.RideResponseDTO;
import com.makemyrides.backend.service.RideService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/rides")
@RequiredArgsConstructor
public class RideController {

    private final RideService rideService;

    @PostMapping
    public ResponseEntity<RideResponseDTO> createRide(@RequestBody RideRequestDTO dto) {
        return ResponseEntity.ok(rideService.createRide(dto));
    }

    @GetMapping
    public ResponseEntity<List<RideResponseDTO>> getAllRides(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(rideService.getAllRides(from, to, date));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RideResponseDTO> getRideById(@PathVariable Long id) {
        return ResponseEntity.ok(rideService.getRideById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRide(@PathVariable Long id) {
        rideService.deleteRide(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{rideId}/passengers/{passengerId}")
    public ResponseEntity<RideResponseDTO> addPassenger(
            @PathVariable Long rideId,
            @PathVariable Long passengerId,
            @RequestParam int seats) {
        return ResponseEntity.ok(rideService.addPassengerToRide(rideId, passengerId, seats));
    }

    @DeleteMapping("/{rideId}/passengers/{passengerId}")
    public ResponseEntity<RideResponseDTO> removePassenger(
            @PathVariable Long rideId,
            @PathVariable Long passengerId) {
        return ResponseEntity.ok(rideService.removePassengerFromRide(rideId, passengerId));
    }

    @PostMapping("/feedback")
    public ResponseEntity<FeedbackResponseDTO> addFeedback(@RequestBody FeedbackRequestDTO dto) {
        return ResponseEntity.ok(rideService.addFeedback(dto));
    }
}
