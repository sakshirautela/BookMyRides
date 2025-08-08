package com.makemyrides.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Setter
@Getter
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Vehicle model is required")
    private String model;

    @Min(value = 1, message = "Vehicle must have at least 1 seat")
    private int seats;

    @Min(value = 1886, message = "Invalid year for a vehicle")
    private int year;

    @NotBlank(message = "Registration number is required")
    private String registrationNumber;

    @Column(name = "license_plate", nullable = false, unique = true)
    @NotBlank(message = "License plate is required")
    private String licensePlate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
}
