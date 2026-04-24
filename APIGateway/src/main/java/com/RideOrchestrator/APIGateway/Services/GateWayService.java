package com.RideOrchestrator.APIGateway.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.RideOrchestrator.APIGateway.NetworkClient;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class GateWayService {
    @Value("${discovery.base-url}")
    private String discoveryBaseURL;
    private final NetworkClient networkClient;

    public GateWayService(NetworkClient networkClient) {
        this.networkClient = networkClient;
    }

    public ResponseEntity<String> forward(HttpServletRequest request) throws Exception {
        String path = request.getRequestURI();
        String method = request.getMethod();
        String body = request.getReader().lines().reduce("", (a, b) -> a + b);
        String serviceDiscoveryPathURL = discoveryBaseURL + "/service-lookup" + path;
        System.out.println("sout " + serviceDiscoveryPathURL);

        String response = networkClient.call(method, serviceDiscoveryPathURL, serviceDiscoveryPathURL, null, body);

        return ResponseEntity.ok(response);
    }
}
