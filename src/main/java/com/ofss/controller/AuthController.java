package com.ofss.controller;

import com.ofss.model.Customer;
import com.ofss.service.CustomerService;
import com.ofss.util.JwtUtil;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final CustomerService customerService;
    private final JwtUtil jwtUtil;

    // inject JwtUtil can generate token after login
    public AuthController(CustomerService customerService, JwtUtil jwtUtil) {
        this.customerService = customerService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Customer customer) {
        try {
            customerService.registerCustomer(customer);
            return ResponseEntity.ok(Map.of("message", "Registration successful!"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String emailId = loginRequest.get("emailId");
        String password = loginRequest.get("password");

        Customer customer = customerService.login(emailId, password);
        if (customer == null) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid email or password"));
         }

        String token = jwtUtil.generateToken(customer.getEmailId());
            return ResponseEntity.ok(Map.of(
                "message", "Login successful!",
                "token", token));
    }
}
