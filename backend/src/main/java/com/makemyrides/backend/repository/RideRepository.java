package com.makemyrides.backend.repository;

import com.makemyrides.backend.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findByDate(LocalDate date);

    @Query("""
                SELECT r FROM Ride r
                WHERE (:from IS NULL OR LOWER(r.fromLocation) = LOWER(:from))
                  AND (:to IS NULL OR LOWER(r.toLocation) = LOWER(:to))
                  AND (:date IS NULL OR r.date = :date)
            """)
    List<Ride> searchRides(@Param("from") String from,
                           @Param("to") String to,
                           @Param("date") LocalDate date);

    List<Ride> findByFromLocationAndToLocationAndDate(String from, String to, LocalDate date);
}
