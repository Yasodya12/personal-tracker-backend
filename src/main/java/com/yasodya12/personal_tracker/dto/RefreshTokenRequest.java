package com.yasodya12.personal_tracker.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RefreshTokenRequest {
    private String refreshToken;
}
