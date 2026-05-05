package com.RideOrchestrator.AuthMicroService.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.RideOrchestrator.AuthMicroService.DTO.UserDTO;
import com.RideOrchestrator.AuthMicroService.Security.JwtProvider;
import com.RideOrchestrator.AuthMicroService.Services.AuthService;

@RestController
public class AuthController {
	private final AuthService authService;
	private final JwtProvider jwtProvider;

	public AuthController(AuthService authService, JwtProvider jwtProvider) {
		this.authService = authService;
		this.jwtProvider = jwtProvider;
	}

	@GetMapping(value = "/user")
	public List<UserDTO> foo() {
		return this.authService.getUser();
	}

	@PostMapping("/auth/login")
	public Map<String, String> login(@RequestBody Map<String, String> req) {
		if ("marko".equals(req.get("username"))) {
			String token = jwtProvider.generateToken("1", "RIDER");
			return Map.of("token", token);
		}
		throw new RuntimeException("Invalid Credentials");
	}
}