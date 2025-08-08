package com.makemyrides.backend.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VehicleResponseDTO {
    private Long id;
    private String model;
    private int seats;
    private int year;
    private String registrationNumber;
    private String licensePlate;
    private Long ownerId;
}
