package com.RideOrchestrator.APIGateway;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Component;

import com.RideOrchestrator.APIGateway.enums.HttpRequestMethod;

@Component
public class NetworkClient {
    private static final HttpClient client = HttpClient.newHttpClient();
    public String call(String method, String uri, String path, HttpHeaders headers, String body) throws IOException, InterruptedException {
        HttpRequest request;

        if (HttpRequestMethod.POST.equals(method)) {
            request = HttpRequest.newBuilder()
                        .uri(URI.create(uri))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(body))
                        .build();
        } else {
            request = HttpRequest.newBuilder()
                        .uri(URI.create(uri))
                        .GET()
                        .build();
        }

         HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
