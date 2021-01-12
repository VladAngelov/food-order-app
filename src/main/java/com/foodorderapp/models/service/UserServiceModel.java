package com.foodorderapp.models.service;

import com.foodorderapp.constants.Errors;
import com.foodorderapp.models.entity.Role;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Min;
import java.util.Set;

public class UserServiceModel {
    private Long id;
    private String email;
    private String password;
    private Set<Role> roles;

    public UserServiceModel() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
