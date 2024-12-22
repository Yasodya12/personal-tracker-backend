package com.yasodya12.personal_tracker.jwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {

            token = token.substring(7); // Remove the "Bearer " prefix

            if (JwtTokenUtil.validateToken(token)) {

                String username = JwtTokenUtil.getUsernameFromToken(token);

                // Create an authentication object
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(username, null, null);

                // Set the authentication details
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));


                // Set the authentication context for the current request
                SecurityContextHolder.getContext().setAuthentication(authentication);
                // Debugging: Log the authentication
                System.out.println("Authenticated user: " + username);
            }
        }


        filterChain.doFilter(request, response); // Continue the filter chain
    }
}