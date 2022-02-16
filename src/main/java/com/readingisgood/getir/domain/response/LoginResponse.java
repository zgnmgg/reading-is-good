package com.readingisgood.getir.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LoginResponse {
    private final String jwt;
    private final String refreshToken;
}
