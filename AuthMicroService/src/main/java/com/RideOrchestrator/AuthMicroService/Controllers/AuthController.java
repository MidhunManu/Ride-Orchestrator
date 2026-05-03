package com.RideOrchestrator.AuthMicroService.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RideOrchestrator.AuthMicroService.DTO.UserDTO;
import com.RideOrchestrator.AuthMicroService.Services.AuthService;

@RestController
public class AuthController {
	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@GetMapping(value = "/user")
	public List<UserDTO> foo() {
		return this.authService.getUser();
	}
}