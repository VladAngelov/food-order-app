package com.foodorderapp.models.binding.auth;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginRequestBindingModel {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}