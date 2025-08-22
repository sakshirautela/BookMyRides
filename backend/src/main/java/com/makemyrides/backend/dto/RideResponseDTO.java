package com.makemyrides.backend.dto;

import com.makemyrides.backend.entity.Passenger;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RideResponseDTO {
    private Long id;
    private String driverName;
    private String vehicleNumber;
    private String fromLocation;
    private String toLocation;
    private List<PassengerDTO> passengers;
    private LocalTime time;
    private LocalDate date;
}
