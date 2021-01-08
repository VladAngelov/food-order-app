package com.foodorderapp.validators.password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.foodorderapp.models.binding.auth.SignUpRequestBindingModel;

public class PasswordMatchesValidator implements
        ConstraintValidator<PasswordMatches, SignUpRequestBindingModel> {

    @Override
    public boolean isValid(
            final SignUpRequestBindingModel user,
            final ConstraintValidatorContext context) {
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
