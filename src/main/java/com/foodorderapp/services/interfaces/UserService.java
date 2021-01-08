package com.foodorderapp.services.interfaces;

import com.foodorderapp.exceptions.UserAlreadyExistAuthenticationException;
import com.foodorderapp.models.binding.user.LocalUserBindingModel;
import com.foodorderapp.models.binding.auth.SignUpRequestBindingModel;
import com.foodorderapp.models.service.UserServiceModel;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

import java.util.Map;
import java.util.Optional;

public interface UserService {

    public UserServiceModel registerNewUser(SignUpRequestBindingModel signUpRequest)
            throws UserAlreadyExistAuthenticationException;

    UserServiceModel findUserByEmail(String email);

    Optional<UserServiceModel> findUserById(Long id);

    LocalUserBindingModel processUserRegistration(
            String registrationId,
            Map<String,
            Object> attributes,
            OidcIdToken idToken,
            OidcUserInfo userInfo);
}
