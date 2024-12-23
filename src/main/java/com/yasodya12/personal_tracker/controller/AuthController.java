package com.yasodya12.personal_tracker.controller;

import com.yasodya12.personal_tracker.dto.LoginRequest;
import com.yasodya12.personal_tracker.dto.RefreshTokenRequest;
import com.yasodya12.personal_tracker.dto.TokenResponse;
import com.yasodya12.personal_tracker.jwtUtil.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, @RequestParam String role) {
        List<String> roles=new ArrayList();
        roles.add(role);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        String accessToken = JwtTokenUtil.generateToken(loginRequest.getUsername(), roles);
        String refreshToken = JwtTokenUtil.generateRefreshToken(loginRequest.getUsername());

        return ResponseEntity.ok(new TokenResponse(accessToken, refreshToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest, @RequestParam String role) {
        String refreshToken = refreshTokenRequest.getRefreshToken();
        List<String> roles=new ArrayList();
        roles.add(role);
        if (JwtTokenUtil.validateToken(refreshToken)) {
            String username = JwtTokenUtil.getUsernameFromToken(refreshToken);
            String newAccessToken = JwtTokenUtil.generateToken(username,roles);

            return ResponseEntity.ok(new TokenResponse(newAccessToken, refreshToken));
        } else {
            return ResponseEntity.status(401).body("Invalid refresh token");
        }
    }
}