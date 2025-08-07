package com.makemyrides.backend.repository;

import com.makemyrides.backend.entity.Ride;
import com.makemyrides.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Ride,Long> {
    <T> User findById(Long passengerId);
}
