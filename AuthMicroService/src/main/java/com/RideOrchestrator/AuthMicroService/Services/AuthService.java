package com.RideOrchestrator.AuthMicroService.Services;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.RideOrchestrator.AuthMicroService.DTO.UserDTO;

@Service
public class AuthService {
    private final RestClient restClient;
    public AuthService(RestClient.Builder builder) {
        this.restClient = builder.build();
    }

    public List<UserDTO> getUser() {
            return this.restClient.get()
                    .uri("http://USER-SERVICE/users")
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<UserDTO>>() {});
        }
}
