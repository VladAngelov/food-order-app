package com.foodorderapp.services.impl;

import com.foodorderapp.exceptions.ResourceNotFoundException;
import com.foodorderapp.models.binding.user.LocalUserBindingModel;
import com.foodorderapp.models.entity.User;
import com.foodorderapp.models.service.UserServiceModel;
import com.foodorderapp.services.interfaces.UserService;
import com.foodorderapp.util.GeneralUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocalUserDetailServiceImpl implements UserDetailsService {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public LocalUserDetailServiceImpl(
            UserService userService,
            ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public LocalUserBindingModel loadUserByUsername(final String email)
            throws UsernameNotFoundException {
        UserServiceModel user = userService.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " was not found in the database");
        }
        return createLocalUser(this.modelMapper.map(user, User.class));
    }

    @Transactional
    public LocalUserBindingModel loadUserById(Long id) {
        var user = userService
                .findUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        return createLocalUser(this.modelMapper.map(user, User.class));
    }

    private LocalUserBindingModel createLocalUser(User user) {
        return new LocalUserBindingModel(
                user.getEmail(),
                user.getPassword(),
                user.isEnabled(),
                true,
                true,
                true,
                GeneralUtils.buildSimpleGrantedAuthorities(user.getRoles()), user);
    }
}