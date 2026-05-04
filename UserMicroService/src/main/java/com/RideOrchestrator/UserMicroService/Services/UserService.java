package com.RideOrchestrator.UserMicroService.Services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.RideOrchestrator.UserMicroService.DAO.UserRepository;
import com.RideOrchestrator.UserMicroService.Models.User;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public ResponseEntity<User> storeUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User must not be null");
        }
        return ResponseEntity.ok(this.userRepository.save(user));
    }

    public ResponseEntity<User> findUser(int id) {
        User user = this.userRepository.findById((long) id)
                    .orElseThrow(() -> new RuntimeException("User not found"));
                    
        return ResponseEntity.ok(user);
    }
}
