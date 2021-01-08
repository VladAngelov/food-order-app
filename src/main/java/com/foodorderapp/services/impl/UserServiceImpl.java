package com.foodorderapp.services.impl;


import com.foodorderapp.exceptions.UserAlreadyExistAuthenticationException;
import com.foodorderapp.models.binding.user.LocalUserBindingModel;
import com.foodorderapp.models.binding.auth.SignUpRequestBindingModel;
import com.foodorderapp.models.entity.Role;
import com.foodorderapp.models.entity.User;
import com.foodorderapp.models.service.UserServiceModel;
import com.foodorderapp.repositories.RoleRepository;
import com.foodorderapp.repositories.UserRepository;
import com.foodorderapp.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserServiceImpl(
            UserRepository userRepository,
            ModelMapper modelMapper,
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(value = "transactionManager")
    public UserServiceModel registerNewUser(final SignUpRequestBindingModel signUpRequest) throws UserAlreadyExistAuthenticationException {
        if (signUpRequest.getUserID() != null &&
                userRepository.existsById(signUpRequest.getUserID())) {
            throw new UserAlreadyExistAuthenticationException("User with User id " + signUpRequest.getUserID() + " already exist");
        } else if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new UserAlreadyExistAuthenticationException("User with email id " + signUpRequest.getEmail() + " already exist");
        }
        User user = buildUser(signUpRequest);
        Date now = Calendar.getInstance().getTime();
        user.setCreatedDate(now);
        user.setModifiedDate(now);
        user.setRoles(Set.of(roleRepository.findByName(Role.ROLE_ADMIN)));
        user = userRepository.save(user);
        userRepository.flush();
        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public UserServiceModel findUserByEmail(String email) {
        return this.modelMapper
                    .map(userRepository.findByEmail(email),
                            UserServiceModel.class);
    }

    @Override
    public Optional<UserServiceModel> findUserById(Long id) {
        UserServiceModel user = this.modelMapper
                .map(this.userRepository.findById(id),
                        UserServiceModel.class);

        return Optional.of(user);
    }

    @Override
    public LocalUserBindingModel processUserRegistration(
            String registrationId,
            Map<String, Object> attributes,
            OidcIdToken idToken,
            OidcUserInfo userInfo) {
        return null;
    }

    private User buildUser(final SignUpRequestBindingModel formDTO) {
        User user = new User();
        user.setDisplayName(formDTO.getDisplayName());
        user.setEmail(formDTO.getEmail());
        user.setPassword(passwordEncoder.encode(formDTO.getPassword()));
        final HashSet<Role> roles = new HashSet<Role>();
        roles.add(roleRepository.findByName(Role.ROLE_ADMIN));
        user.setRoles(roles);
        user.setProvider(formDTO.getSocialProvider().getProviderType());
        user.setEnabled(true);
        user.setProviderUserId(formDTO.getProviderUserId());
        return user;
    }

    @PostConstruct
    public void init() {
        if (this.roleRepository.count() == 0) {
            Role admin = new Role(Role.ROLE_ADMIN);
            Role user = new Role(Role.ROLE_USER);

            this.roleRepository.save(admin);
            this.roleRepository.save(user);
        }
    }
}
