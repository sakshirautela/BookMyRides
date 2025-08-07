package com.makemyrides.backend.repository;

import com.makemyrides.backend.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride, String> {
    Ride findById(Long id);
}