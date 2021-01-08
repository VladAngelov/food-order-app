package com.foodorderapp.models.service;

import com.foodorderapp.constants.Errors;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Min;

public class UserServiceModel {
    private String email;
    private String password;
    private Integer count;

    public UserServiceModel() {
    }
    public UserServiceModel(
            String email,
            String password,
            Integer count) {
        this.email = email;
        this.password = password;
        this.count = count;
    }

    @Length(min = 3, message = Errors.NAME_ERROR)
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Length(min = 4, message = Errors.PASSWORD_ERROR)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Min(value = 1, message = Errors.VOLUME_ERROR)
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
}
