package com.makemyrides.backend.repository;


import com.makemyrides.backend.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger,Long> {
    Optional<Passenger> findByRideIdAndUserId(Long rideId, Long userId);
}
