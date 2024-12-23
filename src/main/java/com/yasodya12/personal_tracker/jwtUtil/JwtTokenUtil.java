package com.yasodya12.personal_tracker.jwtUtil;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JwtTokenUtil {

    // Secret key for signing JWTs (use a more secure one in production)
    private static final String SECRET_KEY = "mySecretKey";

    // Token expiration time (e.g., 1 hour)
    private static final long EXPIRATION_TIME = 60 * 60 * 1000;

    private static final long REFRESH_TOKEN_EXPIRATION = 7 * 24 * 60 * 60 * 1000;

    // Generate a JWT token
    public static String generateToken(String username, List<String> roles) {
        return Jwts.builder()
                .setSubject(username) // Set the username as the subject
                .claim("roles", roles) // Set the roles as a claim
                .setIssuedAt(new Date()) // Set issued date
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Set expiration
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // Signing with HS256 algorithm
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

    public static List<String> getRolesFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return (List<String>) claims.get("roles"); // Extract roles from the token
    }

    public static String generateRefreshToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
