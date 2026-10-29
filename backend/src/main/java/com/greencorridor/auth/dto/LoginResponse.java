package com.greencorridor.auth.dto;

public record LoginResponse(
        String accessToken,
        String tokenType,
        long expiresInMinutes,
        UserResponse user
) {
}
