package com.yasodya12.personal_tracker.dto;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TokenResponse {
    private String accessToken;
    private String refreshToken;

    public TokenResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
