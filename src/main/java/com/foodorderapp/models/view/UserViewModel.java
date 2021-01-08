package com.foodorderapp.models.view;

import java.util.List;

public class UserViewModel {
    private String id;
    private String displayName;
    private String email;
    private List<String> roles;

    public UserViewModel() {
    }

    public UserViewModel(
            String id,
            String displayName,
            String email,
            List<String> roles) {
        this.id = id;
        this.displayName = displayName;
        this.email = email;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
