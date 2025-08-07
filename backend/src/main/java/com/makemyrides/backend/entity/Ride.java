package com.makemyrides.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Component
@Table(name = "rides")
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "driver_id", nullable = false)
//    private User user;

    private String type;

    private String fromLocation;

    private String toLocation;

//    @ManyToMany
//    @JoinTable(
//            name = "ride_passengers",
//            joinColumns = @JoinColumn(name = "ride_id"),
//            inverseJoinColumns = @JoinColumn(name = "passenger_id")
//    )
//    private List<User> passengers;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "vehicle_id", nullable = false)
//    private Vehicle vehicle;

    private LocalDate date;

    private LocalTime time;

}