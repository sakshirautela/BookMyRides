package com.makemyrides.backend.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRequestDTO {
    private String model;
    private int seats;
    private int year;
    private String registrationNumber;
    private String licensePlate;
    private Long ownerId;
}
