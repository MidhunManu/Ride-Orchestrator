package com.RideOrchestrator.UserMicroService.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.RideOrchestrator.UserMicroService.Models.User;
import com.RideOrchestrator.UserMicroService.Services.UserService;

@RestController
@CrossOrigin("*")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "users")
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping(value = "user/{id}")
    public ResponseEntity<User> findUser(@PathVariable int id) {
        return this.userService.findUser(id);
    }

    @PostMapping(value = "user")
    public ResponseEntity<?> store(@RequestBody User user) {
        try {
            return this.userService.storeUser(user);
        } catch(IllegalArgumentException err) {
            return ResponseEntity
                .badRequest()
                .body("user must not be null");
        }
    }
}
