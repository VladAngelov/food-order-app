package com.foodorderapp.models.binding.auth;

import com.foodorderapp.models.view.UserViewModel;

public class JwtAuthenticationResponse {
    private String accessToken;
    private UserViewModel user;

    public JwtAuthenticationResponse() {
    }

    public JwtAuthenticationResponse(String accessToken, UserViewModel user) {
        this.accessToken = accessToken;
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public UserViewModel getUser() {
        return user;
    }

    public void setUser(UserViewModel user) {
        this.user = user;
    }
}
