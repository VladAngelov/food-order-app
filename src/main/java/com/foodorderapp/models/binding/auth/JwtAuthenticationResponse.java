package com.foodorderapp.models.binding.auth;

import lombok.Value;

@Value
public class JwtAuthenticationResponse {
    private String accessToken;
    private UserInfoBindingModel user;
}
