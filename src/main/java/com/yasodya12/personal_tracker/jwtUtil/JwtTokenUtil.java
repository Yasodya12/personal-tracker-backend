package com.yasodya12.personal_tracker.jwtUtil;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    // Secret key for signing JWTs (use a more secure one in production)
    private static final String SECRET_KEY = "mySecretKey";

    // Token expiration time (e.g., 1 hour)
    private static final long EXPIRATION_TIME = 60 * 60 * 1000;

    // Generate a JWT token
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // Set the username as subject claim
                .setIssuedAt(new Date()) // Set issued date
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Set expiration
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // Signing with HS512 algorithm
                .compact();
    }

    // Validate the JWT token
    public static boolean validateToken(String token) {
        try {
            // Parse and check if the token is expired
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false; // Invalid or expired token
        }
    }

    // Get claims from the token (like username)
    public static Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    // Get the username from the token
    public static String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }
}
