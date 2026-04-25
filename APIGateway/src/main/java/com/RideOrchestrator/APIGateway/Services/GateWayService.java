package com.RideOrchestrator.APIGateway.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.RideOrchestrator.APIGateway.NetworkClient;

import jakarta.servlet.http.HttpServletRequest;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

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
        String microService = this.mapPathToMicroService(path);

        String serviceDiscoveryPathURL = discoveryBaseURL + "/service-lookup/" + microService;

        String response = networkClient.call("POST", serviceDiscoveryPathURL, serviceDiscoveryPathURL, null, body);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(response);

        System.out.println("sout: " + response);
        String host = node.get("host").asString();
        String port = node.get("port").asString();

        String url = "http://" + host + ":" + port + path;

        String serviceResponse = networkClient.call(method, url, serviceDiscoveryPathURL, null, body);

        return ResponseEntity.ok()
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .body(serviceResponse);
    }

    private String mapPathToMicroService(String path) {
        if (path.startsWith("/user")) {
            return "user-service";
        } else if (path.startsWith("/ride")) {
            return "ride-service";
        }

        throw new IllegalArgumentException("Unkown service for path, "+ path);
    }
}
