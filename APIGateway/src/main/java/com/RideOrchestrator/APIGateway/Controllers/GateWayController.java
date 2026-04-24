package com.RideOrchestrator.APIGateway.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RideOrchestrator.APIGateway.Services.GateWayService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class GateWayController {
    private final GateWayService gateWayService;
    
    public GateWayController(GateWayService gateWayService) {
        this.gateWayService = gateWayService;
    }

    @RequestMapping("/**")
    public ResponseEntity<String> handle (HttpServletRequest request) throws Exception {
        return gateWayService.forward(request);
    }
}
