package com.yasodya12.personal_tracker.jwtUtil;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
                List<String> rolesFromToken = JwtTokenUtil.getRolesFromToken(token);
                // Create an authentication object
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(username, null, getAuthorities(rolesFromToken));

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

    private Collection<? extends GrantedAuthority> getAuthorities(List<String> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }
}
