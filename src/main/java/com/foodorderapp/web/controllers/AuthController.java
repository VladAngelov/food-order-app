package com.foodorderapp.web.controllers;

import com.foodorderapp.constants.Links;
import com.foodorderapp.exceptions.UserAlreadyExistAuthenticationException;
import com.foodorderapp.models.binding.auth.ApiResponseBindingModel;
import com.foodorderapp.models.binding.auth.JwtAuthenticationResponse;
import com.foodorderapp.models.binding.auth.LoginRequestBindingModel;
import com.foodorderapp.models.binding.auth.SignUpRequestBindingModel;
import com.foodorderapp.models.binding.user.LocalUserBindingModel;
import com.foodorderapp.security.jwt.TokenProvider;
import com.foodorderapp.services.interfaces.UserService;
import com.foodorderapp.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = Links.API_AUTH)
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    TokenProvider tokenProvider;

    @PostMapping(path = Links.WORKERS_LOG_IN)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestBindingModel loginRequest) {
        Authentication authentication =
                authenticationManager
                        .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                loginRequest.getEmail(),
                                loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication);
        LocalUserBindingModel localUser = (LocalUserBindingModel) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, GeneralUtils.buildUserInfo(localUser)));
    }

    @PostMapping(path = Links.WORKERS_REGISTER)
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequestBindingModel signUpRequest) {
        try {
            userService.registerNewUser(signUpRequest);
        } catch (UserAlreadyExistAuthenticationException e) {
            log.error("Exception Ocurred", e);
            return new ResponseEntity<>(
                    new ApiResponseBindingModel(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(
                new ApiResponseBindingModel(true, "User registered successfully"));
    }
}
