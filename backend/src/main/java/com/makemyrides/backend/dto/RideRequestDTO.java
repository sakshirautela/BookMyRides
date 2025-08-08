package com.makemyrides.backend.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDTO {
        private Long driverId;
        private Long vehicleId;
        private String fromLocation;
        private String toLocation;
        private List<PassengerDTO> passengers;
        private LocalTime time;
        private LocalDate date;
    }
