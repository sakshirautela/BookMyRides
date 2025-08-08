package com.makemyrides.backend.service;

import com.makemyrides.backend.dto.VehicleRequestDTO;
import com.makemyrides.backend.dto.VehicleResponseDTO;
import com.makemyrides.backend.entity.User;
import com.makemyrides.backend.entity.Vehicle;
import com.makemyrides.backend.repository.UserRepository;
import com.makemyrides.backend.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;

    @Transactional
    public VehicleResponseDTO createVehicle(VehicleRequestDTO dto) {
        User owner = userRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        Vehicle vehicle = Vehicle.builder()
                .model(dto.getModel())
                .seats(dto.getSeats())
                .year(dto.getYear())
                .registrationNumber(dto.getRegistrationNumber())
                .licensePlate(dto.getLicensePlate())
                .owner(owner)
                .build();

        return mapToResponseDTO(vehicleRepository.save(vehicle));
    }

    public List<VehicleResponseDTO> getAllVehicles(Long ownerId) {
        List<Vehicle> vehicles = (ownerId != null)
                ? vehicleRepository.findByOwnerId(ownerId)
                : vehicleRepository.findAll();

        return vehicles.stream().map(this::mapToResponseDTO).toList();
    }

    @Transactional
    public void removeVehicle(Long vehicleId, Long ownerId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        if (!vehicle.getOwner().getId().equals(ownerId)) {
            throw new RuntimeException("Not authorized to delete this vehicle");
        }
        vehicleRepository.delete(vehicle);
    }

    private VehicleResponseDTO mapToResponseDTO(Vehicle vehicle) {
        return VehicleResponseDTO.builder()
                .id(vehicle.getId())
                .model(vehicle.getModel())
                .seats(vehicle.getSeats())
                .year(vehicle.getYear())
                .registrationNumber(vehicle.getRegistrationNumber())
                .licensePlate(vehicle.getLicensePlate())
                .ownerId(vehicle.getOwner() != null ? vehicle.getOwner().getId() : null)
                .build();
    }
}
