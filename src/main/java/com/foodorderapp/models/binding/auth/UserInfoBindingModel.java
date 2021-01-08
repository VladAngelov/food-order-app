package com.foodorderapp.models.binding.auth;

import lombok.Value;
import java.util.List;

@Value
public class UserInfoBindingModel {
    private String id, displayName, email;
    private List<String> roles;
}
