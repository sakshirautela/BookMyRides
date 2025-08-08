package com.makemyrides.backend.controller;

import com.makemyrides.backend.dto.VehicleRequestDTO;
import com.makemyrides.backend.dto.VehicleResponseDTO;
import com.makemyrides.backend.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;
    @PostMapping
    public ResponseEntity<VehicleResponseDTO> createVehicle(@RequestBody VehicleRequestDTO dto) {
        VehicleResponseDTO created = vehicleService.createVehicle(dto);
        return ResponseEntity
                .created(URI.create("/api/vehicles/" + created.getId()))
                .body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<VehicleResponseDTO>> getAllVehicles(
            @RequestParam Long id
    ) {
        return ResponseEntity.ok(vehicleService.getAllVehicles(id));
    }

    @DeleteMapping("/{id}/{ownerId}")
    public ResponseEntity<Void> deleteVehicle(
            @PathVariable("id") Long vehicleId,
            @RequestParam("ownerId") Long ownerId
    ) {
        vehicleService.removeVehicle(vehicleId, ownerId);
        return ResponseEntity.noContent().build();
    }
}
