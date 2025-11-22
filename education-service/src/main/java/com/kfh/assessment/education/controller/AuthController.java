package com.kfh.assessment.education.controller;

import com.kfh.assessment.education.security.JwtUtil;
import com.kfh.assessment.education.security.SessionStore;
import com.kfh.assessment.education.service.MyUserDetailsService;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
public class AuthController {

    private final AuthenticationManager authManager;
    private final MyUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final SessionStore sessionStore;

    public AuthController(AuthenticationManager authManager,
                          MyUserDetailsService userDetailsService,
                          JwtUtil jwtUtil,
                          SessionStore sessionStore) {
        this.authManager = authManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.sessionStore = sessionStore;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email(), request.password())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.email());
        String token = jwtUtil.generateToken(userDetails.getUsername());
        sessionStore.addToken(userDetails.getUsername(), token);

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null) {
            return ResponseEntity.badRequest().body("Invalid token");
        }
        String username = jwtUtil.extractUsername(authHeader);

        sessionStore.removeToken(username);
        return ResponseEntity.ok("Logged out successfully");
    }
}

record AuthRequest(String email, String password) {}
record AuthResponse(String token) {}
