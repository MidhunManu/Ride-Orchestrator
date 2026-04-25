package com.RideOrchestrator.UserMicroService.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RideOrchestrator.UserMicroService.Models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
