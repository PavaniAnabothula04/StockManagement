package com.ofss.controller;

import com.ofss.dto.DashboardResponse;
import com.ofss.service.DashboardService;
import com.ofss.util.JwtUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;
    private final JwtUtil jwtUtil;

    public DashboardController(DashboardService dashboardService, JwtUtil jwtUtil) {
        this.dashboardService = dashboardService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/details")
    public ResponseEntity<DashboardResponse> getDashboard(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String email = jwtUtil.extractEmail(token);
        return ResponseEntity.ok(dashboardService.getDashboard(email));
    }
}